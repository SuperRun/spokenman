package com.zust.itee.exam.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zust.itee.entity.user.User;
import com.zust.itee.exam.dto.MemberCreateDto;
import com.zust.itee.exam.dto.MemberDto;
import com.zust.itee.exam.dto.MemberListDto;
import com.zust.itee.exam.dto.MemberModifyDto;
import com.zust.itee.exam.dto.OrganizationExcelDto;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.enums.LoginStateEnum;

public interface MemberService extends BaseService<Tmember> {

    /**
     * 根据新闻发言人用户 id 获取工作人员
     */
    Tmember getByOtaUserId(Integer otaUserId);

    /**
     * 根据新闻发言人单位/平台用户创建工作人员
     */
    void saveByOtaUser(User user);

    /**
     * 根据新闻发言人平台用户修改组织
     */
    void updateMemberOrgByOta(User user);

    /**
     * 根据新闻发言人平台用户更新工作人员基本信息
     */
    void updateMemberBaseInfoByOta(User user);

    /**
     * 验证账户
     *
     * @author dxb
     * @why 水泥办 车企登录时验证账户
     */
    LoginStateEnum verifyAccount(String loginName, String password,
            HttpSession session,
            HttpServletRequest request);

    /**
     * 根据登录名获取用户
     *
     * @author dxb
     * @why 创建组织时 需要输入负责人的登录名 故需要根据登录名获取用户
     */
    //	Tmember getByLoginName(String loginName);
    Integer getIdByLoginName(String loginName);

    /**
     * 获取一个部门的员工列表
     *
     * @author dxb
     * @why
     */
    //	List<Tmember> findTmembersInDepartment(Integer depId);
    List<MemberDto> findMemberDtosInDepartment(Integer depId);

    /**
     * 移动一个员工<br>
     * 当 orgId 和 depId只有一项时，直接移动到该部门
     *
     * @author dxb
     * @why
     */
    boolean moveMember(Integer orgId, Integer depId, Integer memberId) throws RuntimeException;

    /**
     * 创建一个员工
     *
     * @param orgId 员工机构id
     * @author dxb
     * @why 创建员工
     */
    //	Tmember create(Tmember tmember, Integer orgId, Integer depId, Integer createrId);
    Integer createDto(MemberCreateDto memberCreateDto, Integer createrId, Integer orgId) throws
            RuntimeException;

    boolean deleteMember(Integer memberId) throws RuntimeException;

    /**
     * 转化成数据对象
     */
    MemberDto transToMemberDto(Tmember tmember);

    MemberDto getModel(Integer memberId);

    List<MemberDto> transToMemberDto(List<Tmember> tmembers);

    boolean verifyPwd(Integer memberId, String password);

    boolean verifyPwd(String loginName, String password);

    boolean verifyPwd(Tmember tmember, String password);

    boolean setPwd(Integer userId, String oldPw, String newpw1) throws RuntimeException;

    /**
     * 修改员工
     *
     * @param memberId 被修改员工id
     * @param memberModifyDto 员工新资料
     * @return 成功与否
     * @author dxb
     * @why
     */
    boolean modify(Integer memberId, MemberModifyDto memberModifyDto) throws RuntimeException;

    /**
     * 将员工从删除状态恢复
     */
    boolean undoDeleteMember(Integer memberId);

    /**
     * 找到一个机构中的所有员工
     * -dxb
     */
    //	List<Tmember> findTmembersInOrganization(Integer orgId, PageBaseDto pageBaseDto);
    List<MemberDto> findMemberDtosInOrganization(Integer orgId, PageBaseDto pageBaseDto);

    PageBaseDto updateFindMembersInOrganization(Integer orgId, PageBaseDto pageBaseDto);

    /**
     * 分页的时候需要显示记录条数
     */
    Long countTmembersInOrganization(Integer orgId);

    List<MemberListDto> findMemberListDtos(Integer orgId);

    Integer getManagerIdByOrgId(Integer organizationId);

    boolean removeMemberFromOrg(Integer mId) throws RuntimeException;

    /**
     * 给车队长管理车企的帐号
     */
    List<Integer> addMasterDriverAccountToMember(List<OrganizationExcelDto> organizationExcelDtos,
            Integer createrId) throws RuntimeException;

    /**
     * 设置车队长的时候 增加一个帐号
     *
     * @param orgId
     * @param driverId
     * @return
     */
    //    Integer addMasterDriverAccountToMember(Integer orgId, Integer driverId, Integer
    // createrId);
}
