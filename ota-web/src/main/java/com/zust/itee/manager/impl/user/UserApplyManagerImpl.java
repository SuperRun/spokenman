package com.zust.itee.manager.impl.user;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.dto.request.userapply.OrgUserApply;
import com.zust.itee.dto.request.userapply.PersonalApproveDto;
import com.zust.itee.dto.request.userapply.PersonalUserApplyDto;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.user.User;
import com.zust.itee.entity.user.UserApply;
import com.zust.itee.enums.user.UserApplyStatusType;
import com.zust.itee.enums.user.UserStatusType;
import com.zust.itee.enums.user.UserType;
import com.zust.itee.exam.service.DriverService;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exam.service.OrganizationService;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.org.OrganizationManager;
import com.zust.itee.manager.user.UserApplyManager;
import com.zust.itee.manager.user.UserManager;
import com.zust.itee.service.user.UserApplyService;
import com.zust.itee.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户申请 manager 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class UserApplyManagerImpl implements UserApplyManager {

    @Resource
    private UserApplyService userApplyService;

    @Resource
    private UserService userService;

    @Resource
    private OrganizationManager organizationManager;

    @Resource
    private UserManager userManager;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private DriverService driverService;

    @Resource
    private MemberService memberService;

    @Resource
    private com.zust.itee.service.org.OrganizationService otaOrgService;

    @Override
    @Transactional
    public void personalUserApply(PersonalUserApplyDto personalUserApplyDto)
            throws BusinessException {
        log.info("==发起个人用户注册申请== apply:{}", personalUserApplyDto);
        // 创建用户
        User user = new User();
        BeanUtils.copyProperties(personalUserApplyDto, user);
        user.setStatus(UserStatusType.REGISTER.getStatus());
        user.setType(UserType.PERSONAL.getValue());
        Organization root = organizationManager.getRootOrg();
        user.setOrgId(root.getId());
        log.info("==发起个人用户注册申请，创建用户== user:{}", user);
        userService.upsert(user);

        // 创建考试系统驾驶员
        driverService.saveByOtaUser(user);
    }

    @Override
    @Transactional
    public void personalApprove(Integer userId, PersonalApproveDto personalApproveDto)
            throws BusinessException {
        log.info("==个人用户资料审核申请== userId:{},approve:{}", userId, personalApproveDto);
        // 更新用户资料,状态改为申请中
        User user = userManager.validateNotNull(userId);
        user = new User();
        BeanUtils.copyProperties(personalApproveDto, user);
        user.setId(userId);
        user.setStatus(UserStatusType.APPLYING.getStatus());
        log.info("==个人用户资料审核申请，更新用户信息== user:{}", user);
        userService.upsert(user);

        // 更新考试系统驾驶员信息
        driverService.updateWhenOtaUserApproval(user);

        // 创建用户申请
        Organization root = organizationManager.getRootOrg();
        saveUserApply(userId, root.getId());
    }

    @Override
    @Transactional
    public void orgUserApply(OrgUserApply orgUserApply) throws BusinessException {
        log.info("==发起单位用户注册申请== apply:{}", orgUserApply);

        // 创建用户
        User user = new User();
        BeanUtils.copyProperties(orgUserApply, user);
        user.setStatus(UserStatusType.APPLYING.getStatus());
        user.setType(UserType.COMPANY.getValue());
        log.info("==发起单位用户注册申请,创建用户== user:{}", user);
        userService.upsert(user);

        // 创建考试系统工作人员
        memberService.saveByOtaUser(user);

        // 创建单位
        Integer orgId = organizationManager.saveOrg(user, orgUserApply);
        log.info("==发起用户申请，用户为单位用户，创建单位结果== orgId:{}", orgId);

        // 更新用户 orgId
        log.info("==发起用户申请，用户为单位用户，更新用户 orgId== orgId:{}", orgId);
        user.setOrgId(orgId);
        userService.upsert(user);

        // 更新考试系统工作人员组织信息
        memberService.updateMemberOrgByOta(user);

        // 创建用户申请
        saveUserApply(user.getId(), orgId);
    }

    @Override
    @Transactional
    public void updateUserApplyStatus(Integer id, Short status) throws BusinessException {
        log.info("==更新用户申请状态== id:{},status:{}", id, status);
        UserApply userApply = userApplyService.getById(id);
        if (userApply == null) {
            throw new BusinessException("用户申请不存在");
        }
        UserApplyStatusType statusType = UserApplyStatusType.getByStatus(status);
        if (statusType == null) {
            throw new BusinessException("用户申请状态不合法");
        }
        log.info("==更新用户申请状态，更新申请状态== id:{},statusType:{}", id, statusType);
        userApplyService.updateUserApplyStatus(id, statusType);

        // 修改用户状态
        UserStatusType userStatusType = UserStatusType.APPLYING;
        if (UserApplyStatusType.APPROVE == statusType) {
            // 申请通过，用户状态改为正常
            userStatusType = UserStatusType.NORMAL;
        } else if (UserApplyStatusType.REJECT == statusType) {
            // 申请拒接，用户状态改为审核拒绝
            userStatusType = UserStatusType.REJECTED;
        }
        log.info("==更新用户申请状态，更新用户状态== userId:{},statusType:{}", userApply.getUserId(),
                userStatusType);
        userService.updateUserStatus(userApply.getUserId(), userStatusType);
    }

    /**
     * 创建用户申请
     */
    private void saveUserApply(Integer userId, Integer orgId) {
        UserApply userApply = UserApply.builder()
                .userId(userId)
                .orgId(orgId)
                .status(UserApplyStatusType.APPLYING.getStatus())
                .build();
        log.info("==发起用户申请，创建用户申请== userApply:{}", userApply);
        userApplyService.upsert(userApply);
    }
}


