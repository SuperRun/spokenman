package com.zust.itee.manager.impl.user;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.dto.UserDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.user.OrgUserSaveDto;
import com.zust.itee.dto.request.user.PersonalUserSaveDto;
import com.zust.itee.dto.request.user.UserSearchDto;
import com.zust.itee.dto.request.user.UserUpdateDto;
import com.zust.itee.entity.datadictionary.DataDictionary;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.user.User;
import com.zust.itee.entity.user.UserApply;
import com.zust.itee.entity.user.UserResource;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.org.OrgLevelType;
import com.zust.itee.enums.user.UserResourceStatus;
import com.zust.itee.enums.user.UserStatusType;
import com.zust.itee.enums.user.UserType;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.service.DriverService;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.org.OrganizationManager;
import com.zust.itee.manager.user.UserManager;
import com.zust.itee.service.datadictionary.DataDictionaryService;
import com.zust.itee.service.org.OrganizationService;
import com.zust.itee.service.resource.LearningResourceService;
import com.zust.itee.service.user.UserApplyService;
import com.zust.itee.service.user.UserResourceService;
import com.zust.itee.service.user.UserService;
import com.zust.itee.util.AreaUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户 manager 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class UserManagerImpl implements UserManager {

    private static final String SESSIN_INFO = "sessionInfo";

    @Resource
    private UserService userService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Resource
    private UserApplyService userApplyService;

    @Resource
    private OrganizationManager organizationManager;

    @Resource
    private AreaUtil areaUtil;

    @Resource
    private MemberService memberService;

    @Resource
    private DriverService driverService;

    @Resource
    private com.zust.itee.exam.service.OrganizationService examOrgService;

    @Resource
    private UserResourceService userResourceService;

    @Resource
    private LearningResourceService learningResourceService;

    @Override
    public void userLogin(UserDto userDto, HttpSession session) {
        log.info("==用户登录== user:{}", userDto);
        User user = userService.login(userDto.getName(), userDto.getPassword());
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        UserDto res = convertToDto(user);
        // 考试系统登录
        SessionInfo sessionInfo = examLogin(res);
        // 新闻发言人系统登录
        sessionInfo.setName(res.getName());
        sessionInfo.setOtaUserId(res.getId());
        sessionInfo.setOrgId(res.getOrgId());
        sessionInfo.setOrgName(res.getOrgName());
        sessionInfo.setType(res.getType());
        sessionInfo.setLevel(res.getLevel());
        sessionInfo.setStatus(res.getStatus());
        session.setAttribute(SESSIN_INFO, sessionInfo);
    }

    /**
     * 考试系统登录
     */
    private SessionInfo examLogin(UserDto userDto) {
        log.info("==用户考试系统登录== user:{}", userDto);
        if (UserType.ROOT.getValue() == userDto.getType()
                || UserType.COMPANY.getValue() == userDto.getType()) {
            log.info("==用户考试系统登录，为平台/单位用户，映射工作人员==");
            Tmember member = memberService.getByOtaUserId(userDto.getId());
            if (member != null) {
                return new SessionInfo(member);
            }
        } else {
            log.info("==用户考试系统登录，为个人用户，映射驾驶员==");
            Tdriver driver = driverService.getByOtaUserId(userDto.getId());
            if (driver != null) {
                return new SessionInfo(driver);
            }
        }
        return new SessionInfo();
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<UserDto> listUserBySearch(Short type, UserSearchDto searchDto,
            PageBaseDto pageBaseDto) throws RuntimeException {
        log.info("==搜索用户== user:{},page:{}", searchDto, pageBaseDto);
        User user = new User();
        BeanUtils.copyProperties(searchDto, user);
        user.setType(type);
        log.info("==搜索用户，转换后参数== user:{}", user);
        List<User> res = userService.listBySearch(user, pageBaseDto.getPage(),
                pageBaseDto.getRows());
        log.info("==搜索用户，数据库查询结果== res:{}", res);
        Long sum = userService.countBySearch(user);
        log.info("==搜索用户，数据库查询总数== sum:{}", sum);
        pageBaseDto.setSumAndPageNum(sum);
        List<UserDto> userDtos = convertToDtoList(res);
        pageBaseDto.setData(userDtos);
        log.info("==搜索用户，转换后结果== pageBaseDto:{}", pageBaseDto);
        return pageBaseDto;
    }

    @Override
    public UserDto getById(Integer id) throws RuntimeException {
        log.info("==获取用户详情== id:{}", id);
        User user = userService.getById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return convertToDto(user);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, UserUpdateDto updateDto) throws BusinessException {
        log.info("==更新用户信息== id:{},user:{}", id, updateDto);
        Organization root = organizationManager.getRootOrg();
        User existUser = validateNotNull(id);
        updateDto.setId(id);
        User user = convertToEntity(updateDto);
        if (UserType.PERSONAL.getValue() == existUser.getType()
                && !existUser.getOrgId().equals(root.getId())) {
            log.info("==更新用户信息，单位中个人用户不允许更新组织相关信息==");
            user.setOrgName(null);
            user.setLevel(null);
            user.setTypeId(null);
            user.setAreaId(null);
        }
        log.info("==更新用户信息，转换后信息== user:{}", user);
        userService.upsert(user);
        // 更新考试系统用户信息
        updateExamUser(user);
        if (UserType.COMPANY.getValue() == existUser.getType()) {
            log.info("==更新用户信息，单位用户需联动更新组织信息==");
            Organization organization = convertToOrg(updateDto);
            organization.setId(existUser.getOrgId());
            log.info("==更新用户信息，单位用户联动更新组织信息== org:{}", organization);
            organizationService.upsert(organization);
            // 更新考试系统组织信息
            examOrgService.updateBaseInfoByOtaOrg(organization);
        }
    }

    /**
     * 更新考试系统用户信息
     */
    private void updateExamUser(User user) {
        log.info("==更新考试系统用户信息== user:{}", user);
        user = userService.getById(user.getId());
        if (UserType.ROOT.getValue() == user.getType()
                || UserType.COMPANY.getValue() == user.getType()) {
            memberService.updateMemberBaseInfoByOta(user);
        } else {
            driverService.updateDriverBaseInfoByOta(user);
        }
    }

    @Override
    public Integer updateUserStatus(Integer id, Short status) throws BusinessException {
        log.info("==更新用户状态== id:{},status:{}", id, status);
        UserStatusType userStatusType = UserStatusType.getByStatus(status);
        if (userStatusType == null) {
            throw new BusinessException("用户修改状态不合法");
        }
        return userService.updateUserStatus(id, userStatusType);
    }

    @Override
    public void savePersonalUser(Integer userId, PersonalUserSaveDto personalUserSaveDto)
            throws BusinessException {
        log.info("==平台用户新增个人/讲师用户== user:{}", personalUserSaveDto);
        User user = new User();
        BeanUtils.copyProperties(personalUserSaveDto, user);
        user.setStatus(UserStatusType.NORMAL.getStatus());
        Organization root = organizationManager.getRootOrg();
        user.setOrgId(root.getId());
        user.setOrgName(root.getName());
        user.setCreatorId(userId);
        user.setPassword("123456");
        log.info("==平台用户新增个人/讲师用户，创建用户== user:{}", user);
        userService.upsert(user);
        // 增加考试系统驾驶员
        driverService.saveByOtaUser(user);
    }

    @Override
    public void saveOrgUser(Integer userId, OrgUserSaveDto orgUserSaveDto)
            throws BusinessException {
        log.info("==平台用户新增单位用户== user:{}", orgUserSaveDto);
        // 新增用户
        User user = new User();
        BeanUtils.copyProperties(orgUserSaveDto, user);
        user.setType(UserType.COMPANY.getValue());
        user.setStatus(UserStatusType.NORMAL.getStatus());
        user.setCreatorId(userId);
        user.setPassword("123456");
        log.info("==平台用户新增单位用户，创建用户== user:{}", user);
        userService.upsert(user);
        // 创建考试系统工作人员
        memberService.saveByOtaUser(user);

        // 创建单位
        Organization organization = Organization.builder()
                .name(orgUserSaveDto.getOrgName())
                .shortName(orgUserSaveDto.getOrgShortName())
                .areaId(orgUserSaveDto.getAreaId())
                .typeId(orgUserSaveDto.getTypeId())
                .level(orgUserSaveDto.getLevel())
                .email(orgUserSaveDto.getOrgEmail())
                .phone(orgUserSaveDto.getOrgPhone())
                .status(NormalStatusType.NORMAL.getStatus())
                .creatorId(userId)
                .userId(user.getId())
                .build();
        log.info("==平台用户新增平台用户，创建单位== org:{}", organization);
        organizationService.upsert(organization);
        // 创建考试系统组织
        examOrgService.saveByOtaOrg(organization);
        Integer orgId = organization.getId();
        log.info("==平台用户新增平台用户，创建单位结果== orgId:{}", orgId);

        // 更新用户 orgId
        user.setOrgId(orgId);
        userService.upsert(user);
        // 更新考试系统工作人员组织
        memberService.updateMemberOrgByOta(user);
    }

    @Override
    public void saveOrgPersonalUser(Integer userId, Integer orgId,
            PersonalUserSaveDto personalUserSaveDto) {
        log.info("==新增单位个人用户== userId:{},orgId:{},user:{}", userId, orgId, personalUserSaveDto);
        User user = new User();
        BeanUtils.copyProperties(personalUserSaveDto, user);
        user.setType(UserType.PERSONAL.getValue());
        user.setCreatorId(userId);
        user.setOrgId(orgId);
        user.setStatus(UserStatusType.NORMAL.getStatus());
        user.setPassword("123456");
        // 填充单位信息
        Organization organization = organizationService.getById(orgId);
        if (organization != null) {
            user.setAreaId(organization.getAreaId());
            user.setLevel(organization.getLevel());
            user.setTypeId(organization.getTypeId());
            user.setOrgName(organization.getName());
        }
        log.info("==新增单位个人用户，创建用户== user:{}", user);
        userService.upsert(user);
        // 创建考试系统驾驶员
        driverService.saveByOtaUser(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<UserDto> listSubUsers(Integer orgId, UserDto userDto,
            PageBaseDto pageBaseDto) throws BusinessException {
        log.info("==单位用户获取单位中用户== orgId:{},user:{},page:{}", orgId, userDto, pageBaseDto);
        userDto.setOrgId(orgId);
        User query = convertToEntity(userDto);
        query.setType(UserType.PERSONAL.getValue());
        log.info("==单位用户获取单位中用户，搜索用户== query:{},page:{}", query, pageBaseDto);
        List<User> users = userService.listBySearch(query, pageBaseDto.getPage(),
                pageBaseDto.getRows());
        log.info("==单位用户获取单位中用户，搜索结果== res:{}", users);
        Long sum = userService.countBySearch(query);
        log.info("==单位用户获取单位中用户，搜索记录总数== sum:{}", sum);
        pageBaseDto.setSumAndPageNum(sum);

        List<UserDto> userDtos = convertToDtoList(users);
        pageBaseDto.setData(userDtos);
        log.info("==单位用户获取单位中用户，转换后结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    @Override
    public User validateNotNull(Integer id) throws BusinessException {
        User user = userService.getById(id);
        if (user == null) {
            throw new BusinessException(String.format("用户不存在，id：%s", id));
        }
        return user;
    }

    /**
     * 将 User 转成 UserDto
     */
    @Override
    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        // 填充组织信息
        Organization org = organizationService.getById(user.getOrgId());
        if (org != null) {
            userDto.setOrgName(org.getName());
            userDto.setOrgShortName(org.getShortName());
        }
        // 组织等级名称
        userDto.setLevelName(OrgLevelType.getByLevel(user.getLevel()) == null ? null
                : OrgLevelType.getByLevel(user.getLevel()).getName());
        // 条线名称
        DataDictionary type = dataDictionaryService.getById(user.getTypeId());
        if (type != null) {
            userDto.setTypeName(type.getValueStr());
        }
        // 状态描述
        userDto.setStatusName(UserStatusType
                .getByStatus(user.getStatus()).getDescription());

        // 用户申请
        List<UserApply> userApplies = userApplyService.listByUserId(user.getId(), 1, 1);
        if (userApplies != null && !userApplies.isEmpty()) {
            userDto.setUserApplyId(userApplies.get(0).getId());
        }

        // 创建人
        User creator = userService.getById(user.getCreatorId());
        if (creator != null) {
            userDto.setCreatorName(creator.getName());
        }

        // 区域名称
        if (user.getAreaId() != null) {
            userDto.setAreaName(areaUtil.getAreaName(String.valueOf(user.getAreaId())));
        }

        // 总学分,时长
        List<UserResource> userResources = userResourceService.listByFilter(UserResource.builder()
                .userId(user.getId())
                .status(UserResourceStatus.FINISHED.getStatus())
                .build(), 1, Integer.MAX_VALUE);
        Integer score = 0;
        Integer learnedTime = 0;
        if (userResources != null && !userResources.isEmpty()) {
            for (UserResource userResource : userResources) {
                score += userResource.getScore();
                String time = userResource.getTime();
                try {
                    if (StringUtils.isNotBlank(time)) {
                        String[] times = time.split(":");
                        learnedTime += new BigDecimal(times[0]).intValue() * 3600;
                        learnedTime += new BigDecimal(times[1]).intValue() * 60;
                        learnedTime += new BigDecimal(times[2]).intValue();
                    }
                } catch (Exception e) {
                    log.warn("计算用户资源时长出错,userResource:{}", userResource);
                }
            }
        }
        Integer learnedHour = 0;
        Integer learnedMinute = 0;
        Integer learnedSecond = 0;
        if (learnedTime != 0) {
            learnedHour = learnedTime / 3600;
            learnedTime -= learnedHour * 3600;
            learnedMinute = learnedTime / 60;
            learnedTime -= learnedMinute * 60;
            learnedSecond = learnedTime;
        }
        userDto.setLearnedHour(learnedHour);
        userDto.setLearnedMinute(learnedMinute);
        userDto.setLearnedSecond(learnedSecond);
        userDto.setScore(score.shortValue());

        return userDto;
    }

    @Override
    public List<UserDto> convertToDtoList(List<User> users) {
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    private User convertToEntity(UserUpdateDto updateDto) {
        User user = new User();
        BeanUtils.copyProperties(updateDto, user);
        return user;
    }

    private Organization convertToOrg(UserUpdateDto updateDto) {
        Organization organization = new Organization().builder()
                .name(updateDto.getOrgName())
                .shortName(updateDto.getOrgShortName())
                .level(updateDto.getLevel())
                .typeId(updateDto.getTypeId())
                .areaId(updateDto.getAreaId())
                .build();
        return organization;
    }
}
