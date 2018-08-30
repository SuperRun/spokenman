package com.zust.itee.exam.service;

import java.util.List;

import com.zust.itee.entity.org.Organization;
import com.zust.itee.exam.dto.LinkPersonCreateDto;
import com.zust.itee.exam.dto.OrgBasicInfoCreateDto;
import com.zust.itee.exam.dto.OrgBasicInfoModifyDto;
import com.zust.itee.exam.dto.OrganizationCreateDto;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.OrganizationExcelDto;
import com.zust.itee.exam.dto.OrganizationListItemDto;
import com.zust.itee.exam.dto.OrganizationModifyDto;
import com.zust.itee.exam.dto.OrganizationTree;
import com.zust.itee.exam.dto.OrganizationUnverifyTree;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.entity.Torganization;

public interface OrganizationService extends BaseService<Torganization> {

    /**
     * 根据新闻发言人平台组织 id 获取组织
     */
    Torganization getByOtaOrgId(Integer otaOrgId);

    /**
     * 根据新闻发言人平台组织创建组织
     */
    void saveByOtaOrg(Organization organization);

    /**
     * 根据新闻发言人平台更新组织基本信息
     */
    void updateBaseInfoByOtaOrg(Organization organization);

    /**
     * 根据机构类型获取机构集合
     *
     * @author dxb
     * @why 创建机构时要父级机构集合，查看机构时，需要子集机构集合等等。。。
     */
    List<Torganization> findOrganizationsByType(short typeCode);

    List<OrganizationDto> findOrganizationDtosByType(short typeCode);

    /**
     * 获取父级机构类型的机构结合
     *
     * @author dxb
     * @why 创建 机构的时候需要选取父级机构
     */
    List<OrganizationDto> findParentOrganizationDtosByType(short typeCode);

    /**
     * 获取一个机构的子级机构集合
     *
     * @author dxb
     * @why 树状查看机构集合的时候用到
     */
    List<OrganizationDto> findOrgDtosByParentId(Integer parentOrgId);

    /**
     * 根据父级orgId获取所有的子级机构 以及 子级机构 的子级 等等等等
     * -dxb
     *
     * @param parentOrgId 父级机构的id
     * @return list
     */
    PageBaseDto findOrgsByParentIdAll(Integer parentOrgId, PageBaseDto pageBaseDto);

    /**
     * 获取一个机构的最顶级机构
     *
     * @author dxb
     * @why 树状查看机构信息的时候，需要获取到顶级的机构，这样可以树状查看自己机构所在的位置
     */
    OrganizationDto getTopTorganizationDto(Integer orgId);

    /**
     * 创建一个水泥办机构
     *
     * @param torganization
     * @param parentId
     * @param linkPersonLoginName
     * @return
     * @author dxb
     * @why
     */
    //    Integer createOrg(Torganization torganization, Integer parentId,
    //                      String linkPersonLoginName) throws RuntimeException;

    /**
     * 创建一个机构
     *
     * @param organizationCreateDto 机构信息，需要包含上级机构和主管的登录名
     * @param orgBasicInfoCreateDto 如果是车企 这项不能为空
     * @return
     */
    //    Integer createOrg(OrganizationCreateDto organizationCreateDto, OrgBasicInfoCreateDto
    // orgBasicInfoCreateDto);
    //
    //    Integer createOrg(OrganizationCreateDto organizationCreateDto, OrgBasicInfoCreateDto
    // orgBasicInfoCreateDto, LinkPersonCreateDto linkPersonCreateDto,
    //
    //                      Integer createrId
    //    );

    /**
     * 禁用一个组织结构
     *
     * @author dxb
     * @why
     */
    boolean disable(Integer orgId);

    /**
     * 获取一个机构的递归子级未审核机构
     *
     * @param orgId 顶级机构id
     * @param cq 1 是车企
     * @param p 1 是 省机构
     * @param c 1 是 市机构
     * @param d 1 是 县区机构<br> 如果上述四个值都为null，将会把cq、c、d赋值为1
     * @author dxb
     * @why 一个机构只能审核自己的子级机构
     */
    List<OrganizationDto> getUnverifyDtoList(Integer orgId, Integer cq, Integer p,
            Integer c, Integer d);

    /**
     * 将org的状态设为可用
     *
     * @author dxb
     * @why 审核org通过、禁用后启用 需要用到
     */
    boolean enable(Integer orgId);

    /**
     * 审核一个org失败，附带失败原因
     *
     * @author dxb
     * @why
     */
    boolean verifyFail(Integer orgId, String reason);

    /**
     * 查找linkedPerson是userId对应member的org
     *
     * @author dxb
     * @why 查看自己的org列表
     */
    List<OrganizationDto> findOrgDtoByLinkedPerson(Integer userId);
    //
    //    /**
    //     * 修改一个机构
    //     * -dxb
    //     *
    //     * @param orgId                 被修改的机构
    //     * @param organizationCreateDto 新资料
    //     * @param orgBasicInfoCreateDto 如果是车企，车企新资料
    //     * @param orgbiId               车企资料id
    //     * @return 修改成功 -true
    //     */
    //    boolean modifyOrg(Integer orgId, OrganizationCreateDto organizationCreateDto,
    //                      OrgBasicInfoCreateDto orgBasicInfoCreateDto, Integer orgbiId);

    /**
     * 判断一个用户是否由编辑这个org（只有部门负责人才能编辑）
     *
     * @param orgId 被编辑orgid
     * @param userId 尝试编辑的用户id
     * @return 是否可编辑
     * @author dxb
     * @why 在org详情需要通过这个判断来决定是否显示编辑按钮<br> 在编辑org的时候 做权限判断
     */
    boolean modifyable(Integer orgId, Integer userId);

    /**
     * 获取和传入机构有所属同一个机构的朋友机构集合
     *
     * @param orgId 需要查找的机构
     * @return 传入机构有所属同一个机构的朋友机构集合
     * @author dxb
     * @why 在修改一个机构的时候，需要显示这个机构所在的树状位置<br>
     */
    List<OrganizationDto> getFriendsOrganizationDtoList(Integer orgId);

    List<OrganizationDto> getFriendsOfParentOrganizationDtoListByOrgId(Integer orgId);

    /**
     * @return 返回员工所在的机构的部门列表中的随机一个的id 如果为空 返回-1
     * @author dxb
     */
    Integer getRandomDepId(Integer memberId);

    /**
     * @param orgId 需要查找的机构
     * @return 如果这个车企是市级机构 那么返回他的市本级机构，否则返回null
     * @author dxb
     * @why 市级车企需要查看市本级车企
     */
    OrganizationDto getShibenjiOrgDto(Integer orgId);

    /**
     * 给部门设置主管
     * -dxb
     */
    Integer setLinkedPerson(Integer orgId, String loginName) throws RuntimeException;

    OrganizationDto getModel(Integer orgId);

    /**
     * 获取父级机构的id
     */
    Integer getParentOrgId(Integer orgId);

    /**
     * 根据根节点id获取结构树
     *
     * @param rootId 根节点id
     */
    OrganizationTree getOrganizationTreeWithCompany(Integer rootId);

    OrganizationTree getOrganizationTreeWithoutCompany(Integer rootId);

    List<OrganizationUnverifyTree> getOrganizationUnverifyTree(Integer orgId);

    Integer create(LinkPersonCreateDto linkPersonCreateDto,
            OrganizationCreateDto organizationCreateDto,
            OrgBasicInfoCreateDto orgBasicInfoCreateDto) throws RuntimeException;

    List<OrganizationExcelDto> excelImportDataRead(String path) throws RuntimeException;

    List<Integer> excelImport(List<OrganizationExcelDto> organizationExcelDtos)
            throws RuntimeException;

    Integer modify(Integer orgId, OrganizationModifyDto organizationModifyDto,
            Integer orgBiId, OrgBasicInfoModifyDto orgBasicInfoModifyDto) throws RuntimeException;

    /**
     * 查找车企
     *
     * @param ctrlOrgId 顶层orgId
     * @return 所有车企
     */
    List<OrganizationListItemDto> findOrganizationListItemDtos(Integer ctrlOrgId);

    Long getRegionIdByRegion(String province, String city, String district);

    /**
     * 找出散装办工作人员的一个下级车企id（用于组织树初始化）
     *
     * @param memberOrgId 当前工作人员所在组织id
     * @param orgId 当前指定车企id
     * @author sdy
     */
    Integer findTheNearestCompany(Integer memberOrgId, Integer orgId);

    int updateOrgInfo(String path) throws RuntimeException;
}
