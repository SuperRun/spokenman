package com.zust.itee.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.entity.user.User;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.MemberCreateDto;
import com.zust.itee.exam.dto.MemberDto;
import com.zust.itee.exam.dto.MemberListDto;
import com.zust.itee.exam.dto.MemberModifyDto;
import com.zust.itee.exam.dto.OrganizationExcelDto;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.LoginStateEnum;
import com.zust.itee.exam.enums.MemberStatusEnum;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exam.service.OrganizationService;
import com.zust.itee.exam.util.MyEncryptUtil;
import com.zust.itee.exam.util.MyStrUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MemberServiceImpl extends BaseServiceImpl<Tmember> implements
        MemberService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseDao<Tmember> memberDao;

    @Autowired
    private BaseDao<Torganization> organizationDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Resource
    private OrganizationService organizationService;

    @Override
    public Tmember getByOtaUserId(Integer otaUserId) {
        String hql = "from Tmember where otaUserId=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", otaUserId);
        return memberDao.get(hql, params);
    }

    @Override
    public void saveByOtaUser(User user) {
        log.info("==创建考试系统工作人员== user:{}", user);
        Tmember member = new Tmember();
        member.setOtaUserId(user.getId());
        member.setName(user.getName());
        member.setLoginName(user.getName());
        member.setPassword(user.getPassword());
        member.setPhone(user.getPhone());
        member.setTorganization(organizationService.getByOtaOrgId(user.getOrgId()));
        member.setStatus(NormalStatusType.NORMAL.getStatus());
        member.setCreateTime(new Date());
        log.info("==创建考试系统工作人员，对应工作人员== member:{}", JSONObject.toJSONString(member));
        save(member);
    }

    @Override
    public void updateMemberOrgByOta(User user) {
        Tmember member = getByOtaUserId(user.getId());
        member.setTorganization(organizationService.getByOtaOrgId(user.getOrgId()));
        saveOrupdate(member);
    }

    @Override
    public void updateMemberBaseInfoByOta(User user) {
        log.info("==更新考试系统用户信息，对应工作人员== user:{}", user);
        Tmember member = getByOtaUserId(user.getId());
        if (member != null) {
            member.setName(user.getName());
            member.setLoginName(user.getName());
            member.setPhone(user.getPhone());
            log.info("==更新考试系统用户信息，工作人员信息更改== member:{}", JSONObject.toJSONString(member));
            saveOrupdate(member);
        }
    }

    @Override
    public LoginStateEnum verifyAccount(String loginName, String password,
            HttpSession session, HttpServletRequest request) {
        // 查找用户
        String hql = "from Tmember d where lower(d.loginName) = :l";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("l", loginName.toLowerCase());

        Tmember tmember = memberDao.get(hql, params);
        if (tmember == null) {
            // 用户不存在
            return LoginStateEnum.ACCOUNT_NOT_EXSIT;
        } else {
            if (DriverServiceImpl.encodePasswd) {
                password = MyEncryptUtil.encodePasswd(password);
            }
            if (tmember.getPassword() == null) {
                // 未设密码 用身份证后六位做密码
                if (tmember.getSfzNo() != null) {
                    String defaulPasswd = tmember.getSfzNo().substring(
                            tmember.getSfzNo().length() - 6,
                            tmember.getSfzNo().length());
                    if (DriverServiceImpl.encodePasswd) {
                        defaulPasswd = MyEncryptUtil.encodePasswd(defaulPasswd);
                    }
                    if (defaulPasswd.equals(password)) {
                        // 密码正确
                        addToSession(tmember, session, request);
                        return LoginStateEnum.SUCCESS;
                    } else {
                        return LoginStateEnum.ERROR_PASSWORD;
                    }
                } else {
                    return LoginStateEnum.UNKONW;
                }
            } else {
                String p = tmember.getPassword();
                if (DriverServiceImpl.encodePasswd) {
                    p = MyEncryptUtil.encodePasswd(p);
                }
                if (p.equals(password)) {
                    // 密码正确
                    addToSession(tmember, session, request);
                    return LoginStateEnum.SUCCESS;
                } else {
                    return LoginStateEnum.ERROR_PASSWORD;
                }
            }
        }
    }

    /**
     * 把登录信息放到session中
     *
     * @why
     */
    private void addToSession(Tmember tmember, HttpSession session,
            HttpServletRequest request) {
        SessionInfo sessionInfo = new SessionInfo(tmember,
                request.getRemoteAddr());
        session.setAttribute("sessionInfo", sessionInfo);
    }

    //    @Override
    private Tmember getByLoginName(String loginName) {
        if (loginName != null) {
            String hql = "from Tmember d where lower(d.loginName) = :l";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("l", loginName.toLowerCase());
            return memberDao.get(hql, params);
        }
        return null;
    }

    @Override
    public Integer getIdByLoginName(String loginName) {
        Tmember mem = getByLoginName(loginName);
        return mem != null ? mem.getId() : null;
    }

    //    @Override
    private List<Tmember> findTmembersInDepartment(Integer depId) {
        return null;
    }

    @Override
    public List<MemberDto> findMemberDtosInDepartment(Integer depId) {
        return transToMemberDto(findTmembersInDepartment(depId));
    }

    @Override
    public boolean moveMember(Integer orgId, Integer depId, Integer memberId)
            throws RuntimeException {
        try {
            return true;
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private Tmember create(Tmember tmember, Integer orgId, Integer depId, Integer createrId) {

        return tmember;
    }

    @Override
    public Integer createDto(MemberCreateDto memberCreateDto, Integer createrId, Integer orgId)
            throws RuntimeException {
        //		无部门的标识符
        //        Integer depId = memberCreateDto.getDepId();
        //        if (depId != null && depId == -1) {
        //            depId = null;
        //        }
        //
        //        short orgType = memberCreateDto.getOrgType();
        //
        //        Integer provinceOrg = memberCreateDto.getProvinceOrg();
        //        Integer cityOrg = memberCreateDto.getCityOrg();
        //        Integer districtOrg = memberCreateDto.getDistrictOrg();
        //        Integer companyOrg = memberCreateDto.getCompanyOrg();

        //        Integer orgId = null;
        //        if (OrganizationTypeEnum.PROVINCE.getType() == orgType) {
        //            orgId = provinceOrg;
        //        } else if (OrganizationTypeEnum.CITY.getType() == orgType) {
        //            orgId = cityOrg;
        //        } else if (OrganizationTypeEnum.DISTRICT.getType() == orgType) {
        //            orgId = districtOrg;
        //        } else if (OrganizationTypeEnum.COMPANY.getType() == orgType) {
        //            orgId = companyOrg;
        //        }
        //        Tmember tmember = new Tmember();
        //        BeanUtils.copyProperties(memberCreateDto, tmember);
        //        return create(tmember, orgId, depId, createrId).getId();

        Tmember byLoginName = getByLoginName(memberCreateDto.getLoginName());
        //        System.out.println(byLoginName.getLoginName());
        if (byLoginName != null) {
            throw new RuntimeException("用户名重复，请重新输入 " + byLoginName.getLoginName());
        }

        Tmember tmember = new Tmember();
        BeanUtils.copyProperties(memberCreateDto, tmember);
        tmember = create(tmember, orgId, null, createrId);
        return tmember != null ? tmember.getId() : null;
    }

    @Override
    public boolean deleteMember(Integer memberId) throws RuntimeException {
        try {
            Tmember tmember = memberDao.get(Tmember.class, memberId);
            if (tmember == null) {
                throw new RuntimeException("所要删除的用户不存在");
            }
            tmember.setStatus(MemberStatusEnum.DELETE.getState());
            memberDao.saveOrUpdate(tmember);
            return true;
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public MemberDto transToMemberDto(Tmember tmember) {
        if (tmember == null) {
            return null;
        }
        try {

            tmember = memberDao.get(Tmember.class, tmember.getId());
            MemberDto memberDto = new MemberDto();
            BeanUtils.copyProperties(tmember, memberDto);

            memberDto.setStatus(MemberStatusEnum.stateOf(tmember.getStatus()));

            if (tmember.getTorganization() != null) {
                memberDto.setOrganizationId(tmember.getTorganization().getId());
                memberDto.setOrganizationName(
                        organizationDao.get(Torganization.class, tmember.getTorganization().getId())
                                .getName());
            }

            if (tmember.getTmember() != null) {
                memberDto.setCreaterId(tmember.getTmember().getId());
                memberDto.setCreaterName(
                        memberDao.get(Tmember.class, tmember.getTmember().getId()).getName());
            }
            return memberDto;
        } catch (LazyInitializationException e) {
            e.printStackTrace();
            return getModel(tmember.getId());
        }
    }

    @Override
    public MemberDto getModel(Integer memberId) {
        return transToMemberDto(memberDao.get(Tmember.class, memberId));
    }

    @Override
    public List<MemberDto> transToMemberDto(List<Tmember> tmembers) {

        List<MemberDto> list = new ArrayList<>();
        for (Tmember t : tmembers) {
            list.add(transToMemberDto(t));
        }

        return list;
    }

    @Override
    public boolean verifyPwd(Integer memberId, String password) {
        return verifyPwd(memberDao.get(Tmember.class, memberId), password);
    }

    @Override
    public boolean verifyPwd(String loginName, String password) {
        String hql = "from Tmember d where lower(d.loginName) = '" + loginName.toLowerCase() + "'";
        return verifyPwd(memberDao.get(hql), password);
    }

    @Override
    public boolean verifyPwd(Tmember tmember, String password) {
        if (tmember != null) {

            if (DriverServiceImpl.encodePasswd) {
                password = MyEncryptUtil.encodePasswd(password);
            }

            if (tmember.getPassword() == null) {
                // 未设密码 用身份证后六位做密码
                if (tmember.getSfzNo() != null) {
                    String defaulPasswd = tmember.getSfzNo().substring(
                            tmember.getSfzNo().length() - 6,
                            tmember.getSfzNo().length());
                    if (DriverServiceImpl.encodePasswd) {
                        defaulPasswd = MyEncryptUtil.encodePasswd(defaulPasswd);
                    }
                    if (defaulPasswd.equals(password)) {
                        // 密码正确
                        return true;
                    }
                }
            } else {
                String p = tmember.getPassword();
                if (DriverServiceImpl.encodePasswd) {
                    p = MyEncryptUtil.encodePasswd(p);
                }
                if (p.equals(password)) {
                    // 密码正确
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean setPwd(Integer userId, String oldPw, String newpw1) throws RuntimeException {
        if (verifyPwd(userId, oldPw)) {
            logger.debug("newpw1 - 1" + newpw1);
            if (DriverServiceImpl.encodePasswd) {
                newpw1 = MyEncryptUtil.encodePasswd(newpw1);
            }
            Tmember tmember = memberDao.get(Tmember.class, userId);
            logger.debug("newpw1 - 2" + newpw1);

            tmember.setPassword(newpw1);
            memberDao.saveOrUpdate(tmember);
            return true;
        } else {
            throw new RuntimeException("旧密码错误");
        }
    }

    @Override
    public boolean modify(Integer memberId, MemberModifyDto memberModifyDto)
            throws RuntimeException {
        try {

            if (MyStrUtil.hasEmpty(memberModifyDto.getName())) {
                throw new RuntimeException("姓名必须输入");
            }
            if (MyStrUtil.hasEmpty(memberModifyDto.getPhone())) {
                throw new RuntimeException("联系方式必须输入");
            }

            Tmember oldTmember = memberDao.get(Tmember.class, memberId);
            BeanUtils.copyProperties(memberModifyDto, oldTmember);
            memberDao.saveOrUpdate(oldTmember);
            return true;
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean undoDeleteMember(Integer memberId) {
        try {
            Tmember tmember = memberDao.get(Tmember.class, memberId);
            if (tmember == null) {
                throw new RuntimeException("所要恢复的用户不存在");
            }
            memberDao.saveOrUpdate(tmember);
            return true;
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * 保证只有应该被修改的属性被修改了
     *
     * @author dxb
     */
    private Tmember copyToOldTmember(Tmember old, MemberModifyDto newM) {
        BeanUtils.copyProperties(newM, old);
        return old;
    }

    //    @Override
    private List<Tmember> findTmembersInOrganization(Integer orgId, PageBaseDto pageBaseDto) {
        String hql = "from Tmember d where d.torganization.id = '" + orgId + "'";
        if (pageBaseDto.getSearchKey() != null) {
            hql += " and (";
            hql += "d.name like '%" + pageBaseDto.getSearchKey() + "%'";
            hql += " or d.sfzNo like '%" + pageBaseDto.getSearchKey() + "%'";
            hql += " or lower(d.loginName) like '%"
                    + pageBaseDto.getSearchKey().toLowerCase()
                    + "%'";
            hql += " or d.phone like '%" + pageBaseDto.getSearchKey() + "%'";
            hql += ")";
        }

        if (pageBaseDto.getPage() == null || pageBaseDto.getRows() == null) {
            pageBaseDto.setPage(1);
            pageBaseDto.setRows(PageBaseDto.ROWS_PER_PAGE);
        }
        return memberDao.find(hql, pageBaseDto.getPage(), pageBaseDto.getRows());
    }

    @Override
    public List<MemberDto> findMemberDtosInOrganization(Integer orgId, PageBaseDto pageBaseDto) {
        return transToMemberDto(findTmembersInOrganization(orgId, pageBaseDto));
    }

    @Override
    public PageBaseDto updateFindMembersInOrganization(Integer orgId, PageBaseDto pageBaseDto) {
        pageBaseDto.setSum(countTmembersInOrganization(orgId, pageBaseDto));
        if (pageBaseDto.getPage() == null || pageBaseDto.getRows() == null) {
            pageBaseDto.setPage(1);
            pageBaseDto.setRows(PageBaseDto.ROWS_PER_PAGE);
        }

        Long t1 = (pageBaseDto.getSum() / pageBaseDto.getRows());
        pageBaseDto.setPageNum(pageBaseDto.getRows(), pageBaseDto.getSum());
        return pageBaseDto;
    }

    private Long countTmembersInOrganization(Integer orgId, PageBaseDto pageBaseDto) {
        String hql = "select count(*) from Tmember d where d.torganization.id = '" + orgId + "'";
        if (pageBaseDto.getSearchKey() != null) {
            hql += " and (";
            hql += "d.name like '%" + pageBaseDto.getSearchKey() + "%'";
            hql += " or d.sfzNo like '%" + pageBaseDto.getSearchKey() + "%'";
            hql += " or lower(d.loginName) like '%"
                    + pageBaseDto.getSearchKey().toLowerCase()
                    + "%'";
            hql += " or d.phone like '%" + pageBaseDto.getSearchKey() + "%'";
            hql += ")";
        }
        return memberDao.count(hql);
    }

    @Override
    public Long countTmembersInOrganization(Integer orgId) {
        String hql = "select count(*) from Tmember d where d.torganization.id = '" + orgId + "'";
        return memberDao.count(hql);
    }

    private MemberListDto transToMemberListDto(Tmember tmember) {
        if (tmember == null) {
            return null;
        }
        MemberListDto memberListDto = new MemberListDto();
        BeanUtils.copyProperties(tmember, memberListDto);
        return memberListDto;
    }

    private List<MemberListDto> transToMemberListDto(List<Tmember> tmembers) {
        List<MemberListDto> memberListDtos = new ArrayList<>();
        for (Tmember tmember : tmembers
                ) {
            MemberListDto memberListDto = transToMemberListDto(tmember);
            if (memberListDto != null) {
                memberListDtos.add(memberListDto);
            }
        }

        return memberListDtos;
    }

    @Override
    public List<MemberListDto> findMemberListDtos(Integer orgId) {
        String hql = "from  Tmember d where d.torganization.id = '" + orgId + "'";
        List<Tmember> tmembers = memberDao.find(hql);
        return transToMemberListDto(tmembers);
    }

    @Override
    public Integer getManagerIdByOrgId(Integer organizationId) {
        if (organizationId != null) {
            Torganization torganization = organizationDao.get(Torganization.class, organizationId);
            if (torganization != null && torganization.getTmember() != null) {
                return torganization.getTmember().getId();
            }
        }
        return null;
    }

    @Override
    public boolean removeMemberFromOrg(Integer mId) throws RuntimeException {
        Tmember tmember = getById(mId);

        if (tmember == null) {
            throw new RuntimeException("用户不存在");
        }
        if (tmember.getTorganization() != null
                && tmember.getTorganization().getTmember() != null
                && tmember.getTorganization().getTmember().getId().equals(mId)) {
            throw new RuntimeException("机构主管不能被移除");
        }

        tmember.setTorganization(null);
        memberDao.saveOrUpdate(tmember);

        return true;
    }

    @Override
    public List<Integer> addMasterDriverAccountToMember(
            List<OrganizationExcelDto> organizationExcelDtos, Integer createrId)
            throws RuntimeException {
        List<Integer> driverAccountIds = new ArrayList<>();
        for (OrganizationExcelDto organizationExcelDto : organizationExcelDtos
                ) {

            String hql = "from Torganization d where d.name = '"
                    + organizationExcelDto.getCompanyName()
                    + "'";
            Torganization torganization = organizationDao.get(hql);

            String hql2 = "from Tdriver d where d.name= '"
                    + organizationExcelDto.getDriverName()
                    + "' and d.mobile = '"
                    + organizationExcelDto.getDriverTel()
                    + "'";
            Tdriver tdriver = driverDao.get(hql2);
            if (tdriver == null) {
                tdriver = new Tdriver();
                tdriver.setMobile(organizationExcelDto.getDriverTel());
                tdriver.setName(organizationExcelDto.getDriverName());
                tdriver.setEmail(organizationExcelDto.getDriverEmail());
                tdriver.setSfzNo(organizationExcelDto.getDriverSfzNo());
            }

            Integer driverAccountId = addMasterDriverAccountToMember(torganization.getId(), tdriver,
                    createrId);
            if (driverAccountId != null) {
                driverAccountIds.add(driverAccountId);
            }
        }
        return driverAccountIds;
    }

    //    @Override
    //    public Integer addMasterDriverAccountToMember(Integer orgId, Integer driverId, Integer
    // createrId) {
    //        if (orgId != null && driverId != null) {
    //            Tdriver tdriver = driverDao.get(Tdriver.class, driverId);
    //            if (tdriver != null) {
    //                return addMasterDriverAccountToMember(orgId, tdriver, createrId);
    //            }
    //        }
    //        return null;
    //    }

    private Integer addMasterDriverAccountToMember(Integer orgId, Tdriver tdriver,
            Integer createrId) throws RuntimeException {
        MemberCreateDto memberCreateDto = new MemberCreateDto();
        memberCreateDto.setLoginName(tdriver.getMobile());
        memberCreateDto.setName(tdriver.getName());
        memberCreateDto.setSfzNo(tdriver.getSfzNo());
        memberCreateDto.setPhone(tdriver.getMobile());
        return createDto(memberCreateDto, createrId, orgId);
    }
}
