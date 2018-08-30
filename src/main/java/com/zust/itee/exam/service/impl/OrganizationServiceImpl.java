package com.zust.itee.exam.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Resource;

import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.exam.comparator.TorganizationComparator;
import com.zust.itee.exam.controller.RegionController;
import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.BaseDto;
import com.zust.itee.exam.dto.DataDictionaryModel;
import com.zust.itee.exam.dto.LinkPersonCreateDto;
import com.zust.itee.exam.dto.OrgBasicInfoCreateDto;
import com.zust.itee.exam.dto.OrgBasicInfoModifyDto;
import com.zust.itee.exam.dto.OrganizationCreateDto;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.OrganizationExcelDto;
import com.zust.itee.exam.dto.OrganizationExcelUpdateInfoDto;
import com.zust.itee.exam.dto.OrganizationListItemDto;
import com.zust.itee.exam.dto.OrganizationModifyDto;
import com.zust.itee.exam.dto.OrganizationNode;
import com.zust.itee.exam.dto.OrganizationTree;
import com.zust.itee.exam.dto.OrganizationUnverifyTree;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.MemberStatusEnum;
import com.zust.itee.exam.enums.OrganizationStatusEnum;
import com.zust.itee.exam.enums.OrganizationTypeEnum;
import com.zust.itee.exam.exception.NoSonCompanyException;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exam.service.OrganizationService;
import com.zust.itee.exam.util.MyExcelUtil;
import com.zust.itee.exam.util.MyStrUtil;
import com.zust.itee.manager.org.OrganizationManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrganizationServiceImpl extends BaseServiceImpl<Torganization>
        implements OrganizationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<Torganization> organizationDao;

    @Autowired
    private BaseDao<Tmember> memberDao;
    @Autowired
    private BaseDao<Tdriver> driverDao;
    @Autowired
    private BaseDao<DataDictionary> dataDictionaryDao;

    @Resource
    private MemberService memberService;

    @Resource
    private OrganizationManager organizationManager;

    @Override
    public Torganization getByOtaOrgId(Integer otaOrgId) {
        String hql = "from Torganization where otaOrgId=:orgId";
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", otaOrgId);
        return organizationDao.get(hql, params);
    }

    @Override
    public void saveByOtaOrg(Organization organization) {
        log.info("==创建考试系统组织== org:{}", organization);
        if (organization != null) {
            Organization rootOrg = organizationManager.getRootOrg();
            Torganization root = getByOtaOrgId(rootOrg.getId());
            Torganization torganization = new Torganization();
            torganization.setOtaOrgId(organization.getId());
            torganization.setName(organization.getName());
            torganization.setShortName(organization.getShortName());
            torganization.setTmember(memberService.getByOtaUserId(organization.getUserId()));
            // 组织均为车企
            torganization.setType(OrganizationTypeEnum.COMPANY.getType());
            torganization.setTel(organization.getPhone());
            torganization.setEmail(organization.getEmail());
            torganization.setTorganization(getByOtaOrgId(organization.getParentId()));
            // todo 区域映射
            torganization.setRegionId(organization.getAreaId());
            torganization.setStatus(NormalStatusType.NORMAL.getStatus());
            // 父组织为平台组织
            torganization.setTorganization(root);
            log.info("==创建考试系统组织，对应组织== org:{}", torganization);
            save(torganization);
        }
    }

    @Override
    public void updateBaseInfoByOtaOrg(Organization organization) {
        log.info("==更新考试系统组织信息== org:{}", organization);
        Torganization torganization = getByOtaOrgId(organization.getId());
        if (torganization != null) {
            // todo 区域映射
            torganization.setRegionId(organization.getAreaId());
            torganization.setName(organization.getName());
            torganization.setShortName(organization.getShortName());
            log.info("==更新考试系统组织信息，更新信息== torganization:{}", torganization);
            saveOrupdate(torganization);
        }
    }

    @Override
    public List<Torganization> findOrganizationsByType(short typeCode) {
        String hql = "from Torganization d where d.type = :t order by d.shortName";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("t", typeCode);
        return organizationDao.find(hql, params);
    }

    @Override
    public List<OrganizationDto> findOrganizationDtosByType(short typeCode) {
        return transToOrganizationDto(findOrganizationsByType(typeCode));
    }

    private List<Torganization> findParentOrganizationsByType(short typeCode) {
        if (typeCode == OrganizationTypeEnum.PROVINCE.getType()) {
            // 省级散装水泥办 没有父级机构
            return new ArrayList<>();
        }
        return findOrganizationsByType((short) (typeCode - 1));
    }

    @Override
    public List<OrganizationDto> findParentOrganizationDtosByType(short typeCode) {
        return transToOrganizationDto(findParentOrganizationsByType(typeCode));
    }

    private List<Torganization> findOrgsByParentId(Integer parentOrgId) {

        String hql = "from Torganization d where d.torganization = :o";

        Map<String, Object> params = new HashMap<>();
        params.put("o", new Torganization(parentOrgId));

        return organizationDao.find(hql, params);
    }

    @Override
    public List<OrganizationDto> findOrgDtosByParentId(Integer parentOrgId) {
        return transToOrganizationDto(findOrgsByParentId(parentOrgId));
    }

    @Override
    public PageBaseDto findOrgsByParentIdAll(Integer parentOrgId,
            PageBaseDto pageBaseDto) {

        // 基本语句
        String hql = "from Torganization d where "
                + "( d.torganization.id = :oId "
                + " or d.torganization.torganization.id = :oId "
                + " or d.torganization.torganization.torganization.id = :oId ) ";
        // TODO: 2016/9/14 搜索结果不全
        // 参数
        Map<String, Object> params = new HashMap<>();
        params.put("oId", parentOrgId);

        // 搜索条件
        if (pageBaseDto.getSearchKey() != null) {
            hql += " and ( d.name like :s";
            hql += " or d.shortName like :s)";
            params.put("s", "%" + pageBaseDto.getSearchKey() + "%");
        }

        // 默认分页
        if (pageBaseDto.getRows() == null) {
            pageBaseDto.setRows(PageBaseDto.ROWS_PER_PAGE);
        }
        if (pageBaseDto.getPage() == null) {
            pageBaseDto.setPage(1);
        }

        logger.info("取结果前 " + pageBaseDto.toString());

        // 取结果
        hql += "order by d.torganization.id asc";
        List<Torganization> torganizations = organizationDao.find(hql, params,
                pageBaseDto.getPage(), pageBaseDto.getRows());

        pageBaseDto.setList(transToOrganizationDto(torganizations));
        pageBaseDto.setSum(organizationDao.count("select count(*) " + hql,
                params));
        pageBaseDto.setPageNum(pageBaseDto.getRows(), pageBaseDto.getSum());
        logger.info("计算分页 " + pageBaseDto.toString() + "    "
                + torganizations.size());

        return pageBaseDto;
    }

    private Torganization getTopTorganization(Integer orgId) {
        Torganization torganization = organizationDao.get(Torganization.class,
                orgId);
        return getTopTorganization(torganization);
    }

    @Override
    public OrganizationDto getTopTorganizationDto(Integer orgId) {
        return transToOrganizationDto(getTopTorganization(orgId));
    }

    private Torganization getTopTorganization(Torganization torganization) {
        // 当机构不是省级机构时，往上追溯
        while (OrganizationTypeEnum.PROVINCE.getType() != torganization
                .getType()) {
            torganization = torganization.getTorganization();
        }
        return torganization;
    }

    /**
     * 把实体转化成供树状显示组织机构的数据模型
     */
    private OrganizationNode transToOrganizationNode(Torganization torganization) {
        OrganizationNode organizationNode = new OrganizationNode();
        BeanUtils.copyProperties(torganization, organizationNode);
        return organizationNode;
    }

    // @Override
    // public Integer createOrg(Torganization torganization, Integer parentId,
    // String linkPersonLoginName) throws RuntimeException {
    // return create(torganization, parentId, linkPersonLoginName).getId();
    // }

    /**
     * 把实体转化成供树状显示组织机构的数据模型
     */
    private List<OrganizationNode> transToOrganizationNode(
            Collection<Torganization> torganizations) {
        List<OrganizationNode> organizationNodes = new ArrayList<>();
        for (Torganization torganization : torganizations) {
            organizationNodes.add(transToOrganizationNode(torganization));
        }
        return organizationNodes;
    }

    // @Override
    // public Integer createOrg(OrganizationCreateDto organizationCreateDto,
    // OrgBasicInfoCreateDto orgBasicInfoCreateDto) {
    // Torganization torganization = new Torganization();
    // BeanUtils.copyProperties(organizationCreateDto, torganization);
    //
    // String linkPersonLoginName = organizationCreateDto.getLinkPerson();
    // Integer parentId = organizationCreateDto.getParentId();
    //
    // TorgBasicInfo torgBasicInfo = new TorgBasicInfo();
    // BeanUtils.copyProperties(orgBasicInfoCreateDto, torgBasicInfo);
    //
    // torganization = create(torganization, torgBasicInfo, parentId,
    // linkPersonLoginName);
    //
    // return torganization.getId() != null ? torganization.getId() : null;
    // }
    //
    // @Override
    // public Integer createOrg(OrganizationCreateDto organizationCreateDto,
    // OrgBasicInfoCreateDto orgBasicInfoCreateDto, LinkPersonCreateDto
    // linkPersonCreateDto, Integer createrId) {
    // //持久化linkPersonCreateDto 然后转交工作
    //
    // String lpName = linkPersonCreateDto.getLpName();
    // String loginName = linkPersonCreateDto.getLoginName();
    // Tmember tmember = new Tmember();
    // tmember.setLoginName(loginName);
    // tmember.setName(lpName);
    // tmember.setPassword(linkPersonCreateDto.getPassword());
    // tmember.setCreateTime(new Date());
    //
    // Tmember creater = memberDao.get(Tmember.class, createrId);
    // tmember.setTmember(creater);
    //
    // memberDao.save(tmember);
    //
    // organizationCreateDto.setLinkPerson(loginName);
    //
    // return createOrg(organizationCreateDto, orgBasicInfoCreateDto);
    //
    // }

    private Torganization create(Torganization torganization, Integer parentId,
            String linkPersonLoginName) {

        // 设置状态未审核
        // torganization.setStatus(OrganizationStatusEnum.UNVERIFY.getStatus());
        torganization.setStatus(OrganizationStatusEnum.ENABLE.getStatus());

        // 查找父级
        Torganization parentOrg = null;
        if (parentId != null) {
            parentOrg = organizationDao.get(Torganization.class, parentId);
            // 设置类型
            torganization.setType((short) (parentOrg.getType() + 1));
        }
        torganization.setTorganization(parentOrg);
        organizationDao.save(torganization);

        if (linkPersonLoginName != null) {
            // 设置负责人
            String hql = "from Tmember t where lower(t.loginName) = '"
                    + linkPersonLoginName.toLowerCase() + "'";
            Tmember tmember = memberDao.get(hql);
            if (tmember != null) {

                torganization.setTmember(tmember);
                organizationDao.saveOrUpdate(torganization);

                // 设置员工所属和员工在职状态
                tmember.setTorganization(torganization);
                tmember.setStatus(MemberStatusEnum.NORMAL.getState());
                memberDao.saveOrUpdate(tmember);
            }
        }

        return torganization;
    }

    @Override
    public boolean disable(Integer orgId) {
        try {
            Torganization torganization = organizationDao.get(
                    Torganization.class, orgId);
            torganization.setStatus(OrganizationStatusEnum.DISABLE.getStatus());
            organizationDao.saveOrUpdate(torganization);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<Torganization> getUnverifyList(Integer orgId, Integer cq,
            Integer p, Integer c, Integer d) {
        List<Torganization> list = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("s", OrganizationStatusEnum.UNVERIFY.getStatus());

        // TODO: 2016/8/30 这个方法还有问题
        String hql = "";

        if (p == null && cq == null && c == null && d == null) {
            cq = 1;
            c = 1;
            d = 1;
        }

        if (p != null && p == 1) {
            hql = "from Torganization d where d.status = :s";
            list.addAll(organizationDao.find(hql, params));
        }

        if (c != null && c == 1) {
            hql = "from Torganization d where d.torganization = :o and d.status = :s";
            params.put("o", new Torganization(orgId));
            list.addAll(organizationDao.find(hql, params));
        }

        if (d != null && d == 1) {
            hql = "from Torganization d where d.torganization.torganization = :o and d.status = :s";
            params.put("o", new Torganization(orgId));
            list.addAll(organizationDao.find(hql, params));
        }

        if (cq != null && cq == 1) {
            hql = "from Torganization d where d.torganization.torganization.torganization = :o "
                    + "and d.status = :s";
            params.put("o", new Torganization(orgId));
            list.addAll(organizationDao.find(hql, params));
        }

        return list;
    }

    @Override
    public List<OrganizationDto> getUnverifyDtoList(Integer orgId, Integer cq,
            Integer p, Integer c, Integer d) {
        return transToOrganizationDto(getUnverifyList(orgId, cq, p, c, d));
    }

    @Override
    public boolean enable(Integer orgId) {
        try {
            Torganization torganization = organizationDao.get(
                    Torganization.class, orgId);
            torganization.setStatus(OrganizationStatusEnum.ENABLE.getStatus());

            // 清空审核失败原因
            torganization.setRemark(null);

            organizationDao.saveOrUpdate(torganization);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean verifyFail(Integer orgId, String reason) {
        try {
            Torganization torganization = organizationDao.get(
                    Torganization.class, orgId);
            torganization.setStatus(OrganizationStatusEnum.FALIEDVERIFY
                    .getStatus());
            torganization.setRemark(reason);
            organizationDao.saveOrUpdate(torganization);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // @Override
    // public boolean modifyOrg(Integer orgId, OrganizationCreateDto
    // organizationCreateDto,
    // OrgBasicInfoCreateDto orgBasicInfoDto, Integer orgbiId) {
    //
    // try {
    // // 获取实体
    // Torganization torganization = copyToOldOrganization(orgId,
    // organizationCreateDto);
    // // 查找父级
    // Integer parentId = organizationCreateDto.getParentId();
    // Torganization parentOrg = null;
    // if (parentId != null) {
    // parentOrg = organizationDao.get(Torganization.class, parentId);
    // torganization.setType((short) (parentOrg.getType() + 1));
    // torganization.setTorganization(parentOrg);
    // }
    //
    //
    // // 设置负责人
    // String linkPersonLoginName = organizationCreateDto.getLinkPerson();
    // String hql = "from Tmember t where lower(t.loginName) = '"
    // + linkPersonLoginName.toLowerCase() + "'";
    // torganization.setTmember(memberDao.get(hql));
    //
    // //保存
    // organizationDao.saveOrUpdate(torganization);
    //
    //
    // // 车企
    // if (torganization.getType() == OrganizationTypeEnum.COMPANY
    // .getType()) {
    //
    // TorgBasicInfo torgBasicInfo = copyToOldOrgBasicInfo(orgbiId,
    // orgBasicInfoDto);
    // orgBasicInfoDao.saveOrUpdate(torgBasicInfo);
    // }
    // logger.debug("修改完成 true");
    // return true;
    // } catch (Exception e) {
    // e.printStackTrace();
    // return false;
    // }
    //
    //
    // }

    @Override
    public List<OrganizationDto> findOrgDtoByLinkedPerson(Integer userId) {
        return transToOrganizationDto(findByLinkedPerson(userId));
    }

    private List<Torganization> findByLinkedPerson(Integer userId) {
        Tmember tmember = new Tmember(userId);
        String hql = "from Torganization d where d.tmember = :m";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("m", tmember);
        return organizationDao.find(hql, params);
    }

    /**
     * 将修改过的信息拷贝到实体中 - dxb
     *
     * @param orgId 被赋值的实体
     * @param organizationCreateDto 新属性
     * @return 赋值完成的实体
     */
    private Torganization copyToOldOrganization(Integer orgId,
            OrganizationCreateDto organizationCreateDto) {
        Torganization torganization = organizationDao.get(Torganization.class,
                orgId);
        BeanUtils.copyProperties(organizationCreateDto, torganization);
        return torganization;
    }

    private Torganization getLinkedOrganization(Integer memberId) {
        String hql = "from Torganization d where d.tmember = :p";
        Map<String, Object> params = new HashMap<>();
        params.put("p", new Tmember(memberId));
        return organizationDao.get(hql, params);
    }

    /**
     * 转化成数据传输对象
     */
    private OrganizationDto transToOrganizationDto(Torganization torganization) {
        if (torganization == null) {
            return null;
        }
        try {
            OrganizationDto organizationDto = new OrganizationDto();
            logger.debug("转化dto " + torganization.toString());
            // 联系人 散装办信息
            BeanUtils.copyProperties(torganization, organizationDto);

            // 散装办类型
            organizationDto.setType(OrganizationTypeEnum.stateOf(torganization
                    .getType()));

            // 上级
            if (torganization.getTorganization() != null) {
                organizationDto.setParentOrg(new BaseDto(torganization
                        .getTorganization().getId(), torganization
                        .getTorganization().getName()));
            }

            // 负责人
            if (torganization.getTmember() != null) {
                organizationDto.setMemberId(torganization.getTmember().getId());
                organizationDto.setMemberTel(torganization.getTmember()
                        .getPhone());
                organizationDto.setMemberName(torganization.getTmember()
                        .getName()
                        + "("
                        + torganization.getTmember().getLoginName() + ")");
            }

            // 区域
            if (torganization.getRegionId() != null) {
                DataDictionary dataDictionary = dataDictionaryDao.get(
                        DataDictionary.class, torganization.getRegionId());
                organizationDto.setRegion(dataDictionary.getValueStr());
            }

            return organizationDto;
        } catch (LazyInitializationException e) {
            e.printStackTrace();
            return getModel(torganization.getId());
        }
    }

    /**
     * 转化成数据传输对象
     */
    private List<OrganizationDto> transToOrganizationDto(
            List<Torganization> torganizations) {
        List<OrganizationDto> list = new ArrayList<>();
        for (Torganization t : torganizations) {
            list.add(transToOrganizationDto(t));
        }
        return list;
    }

    public boolean modifyable(Integer orgId, Integer userId) {
        // TODO 现在部门负责人才能编辑
        String hql1 = "select count(*) from Torganization d where d.id = :orgId and d.tmember = :u";
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        params.put("u", new Tmember(userId));
        return organizationDao.count(hql1, params) > 0;
    }

    private List<Torganization> getFriendsOrganizationList(Integer orgId) {
        Torganization torganization = organizationDao.get(Torganization.class,
                orgId);
        if (torganization != null) {
            if (torganization.getType() == OrganizationTypeEnum.PROVINCE
                    .getType()) {
                return findOrganizationsByType(OrganizationTypeEnum.PROVINCE
                        .getType());
            }
            String hql = " from Torganization d where d.torganization = :org ";
            Map<String, Object> map = new HashMap<>();
            map.put("org", torganization.getTorganization());
            return organizationDao.find(hql, map);
        }
        return new ArrayList<>();
    }

    @Override
    public List<OrganizationDto> getFriendsOrganizationDtoList(Integer orgId) {
        return transToOrganizationDto(getFriendsOrganizationList(orgId));
    }

    private List<Torganization> getFriendsOfParentOrganizationListByOrgId(
            Integer orgId) {
        // String hql1 = "from Torganization d where d.torganization = :org1 ";
        // Map<String, Object> map1 = new HashMap<>();
        // map1.put("org1",new Torganization(orgId));
        logger.debug("getFriendsOfParentOrganizationListByOrgId- " + orgId);
        Torganization sonorg = organizationDao.get(Torganization.class, orgId);
        Torganization torganization = sonorg.getTorganization();
        if (torganization != null) {
            logger.debug("getFriendsOfParentOrganizationListByOrgId- torganization != null");

            String hql = " from Torganization d where d.torganization = :org ";
            Map<String, Object> map = new HashMap<>();
            if (torganization.getTorganization() != null) {
                logger.debug(
                        "getFriendsOfParentOrganizationListByOrgId- torganization"
                                + ".getTorganization() != null");

                map.put("org", torganization.getTorganization());
                return organizationDao.find(hql, map);
            } else if (sonorg.getType() == OrganizationTypeEnum.CITY.getType()) {
                return findOrganizationsByType(OrganizationTypeEnum.PROVINCE
                        .getType());
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<OrganizationDto> getFriendsOfParentOrganizationDtoListByOrgId(
            Integer orgId) {
        return transToOrganizationDto(getFriendsOfParentOrganizationListByOrgId(orgId));
    }

    @Override
    public Integer getRandomDepId(Integer memberId) {
        return -1;
    }

    private Torganization getShibenjiOrg(Torganization torganization) {
        if (torganization.getType() == OrganizationTypeEnum.CITY.getType()) {
            String hql = "from Torganization d where d.torganization = :parentOrg and d.name like"
                    + " :parentName";
            Map<String, Object> params = new HashMap<>();
            params.put("parentOrg", torganization);
            params.put("parentName", torganization.getName() + "%");
            return organizationDao.get(hql, params);
        }
        return null;
    }

    @Override
    public OrganizationDto getShibenjiOrgDto(Integer orgId) {
        return transToOrganizationDto(getShibenjiOrg(organizationDao.get(
                Torganization.class, orgId)));
    }

    @Override
    public Integer setLinkedPerson(Integer orgId, String loginName)
            throws RuntimeException {
        Torganization torganization = organizationDao.get(Torganization.class,
                orgId);
        if (torganization == null) {
            throw new RuntimeException("机构编号 = " + orgId + " 的机构不存在");
        }
        Tmember tmember = memberDao.get("from Tmember d where d.loginName ='"
                + loginName + "'");
        if (tmember == null) {
            throw new RuntimeException("登录名 = " + loginName + " 的员工不存在");
        }

        // 更新主管
        torganization.setTmember(tmember);
        organizationDao.saveOrUpdate(torganization);

        return torganization.getId();
    }

    @Override
    public OrganizationDto getModel(Integer orgId) {
        return transToOrganizationDto(organizationDao.get(Torganization.class,
                orgId));
    }

    @Override
    public Integer getParentOrgId(Integer orgId) {
        Torganization torganization = organizationDao.get(Torganization.class,
                orgId);
        if (torganization != null && torganization.getTorganization() != null) {
            return torganization.getTorganization().getId();
        }
        return null;
    }

    /**
     * 将实体转化成机构树的节点 不转化子机构
     */
    private OrganizationTree transToOrganizationTree(
            Torganization torganization, boolean withCompany) {

        if (torganization == null
                || torganization.getStatus() == OrganizationStatusEnum.DISABLE
                .getStatus()) {
            // torganization.getStatus() !=
            // OrganizationStatusEnum.ENABLE.getStatus()
            return null;
        }

        if (!withCompany
                && torganization.getType() == OrganizationTypeEnum.COMPANY
                .getType()) {
            return null;
        }

        OrganizationTree organizationTree = new OrganizationTree();
        BeanUtils.copyProperties(torganization, organizationTree);

        if (torganization.getType() != OrganizationTypeEnum.COMPANY.getType()
                && organizationTree.getRegionId() != null) {
            String regionValue = dataDictionaryDao.get(DataDictionary.class,
                    organizationTree.getRegionId()).getValueStr();
            if (regionValue != null) {
                organizationTree.setName(regionValue);
            }
        }

        List<OrganizationTree> childrenModel = new ArrayList<>();
        List<Torganization> childrenEntity = new ArrayList<>(
                torganization.getTorganizations());
        // 保证排序
        Collections.sort(childrenEntity, new TorganizationComparator());
        for (Torganization o : childrenEntity) {
            OrganizationTree organizationTree1 = transToOrganizationTree(o,
                    withCompany);
            if (organizationTree1 != null) {
                childrenModel.add(organizationTree1);
            }
        }
        organizationTree.setChildren(childrenModel);

        return organizationTree;
    }

    @Override
    public OrganizationTree getOrganizationTreeWithCompany(Integer rootId) {
        return transToOrganizationTree(getById(rootId), true);
    }

    @Override
    public OrganizationTree getOrganizationTreeWithoutCompany(Integer rootId) {
        return transToOrganizationTree(getById(rootId), false);
    }

    private OrganizationUnverifyTree transToOrganizationUnverifyTree(
            Torganization torganization) {
        if (torganization == null) {
            return null;
        }
        OrganizationUnverifyTree organizationUnverifyTree = new OrganizationUnverifyTree();
        BeanUtils.copyProperties(torganization, organizationUnverifyTree);

        // organizationUnverifyTree.setName((organizationUnverifyTree.getRegion()
        // == null
        // ? organizationUnverifyTree.getName() + "[-]" :
        // organizationUnverifyTree.getName() + "[" +
        // organizationUnverifyTree.getRegion() + "]"));

        return organizationUnverifyTree;
    }

    // private Torganization createShibenjiOrgByShiOrg(Torganization shiOrg) {
    // Torganization torganization = new Torganization();
    // torganization.setName(shiOrg.getName() + "(市本级)");
    // torganization.setShortName(shiOrg.getShortName() + "(市本级)");
    // torganization.setDescription(shiOrg.getDescription());
    // torganization.setTel(shiOrg.getTel());
    // torganization.setEmail(shiOrg.getEmail());
    // return torganization;
    // }

    @Override
    public List<OrganizationUnverifyTree> getOrganizationUnverifyTree(
            Integer orgId) {
        List<Torganization> unverifyList = getUnverifyList(orgId, null, null,
                null, null);
        List<OrganizationUnverifyTree> organizationUnverifyTrees = new ArrayList<>();
        for (Torganization torganization : unverifyList) {
            OrganizationUnverifyTree organizationUnverifyTree = transToOrganizationUnverifyTree(
                    torganization);
            if (organizationUnverifyTree != null) {
                organizationUnverifyTrees.add(organizationUnverifyTree);
            }
        }
        return organizationUnverifyTrees;
    }

    /**
     * 获取或者创建负责人帐号
     *
     * @param linkPersonCreateDto 负责人基本信息
     * @return 负责人持久化对象
     */
    private Tmember getOrCreateLinkPerson(
            LinkPersonCreateDto linkPersonCreateDto) {
        String loginName = linkPersonCreateDto.getLoginName().toLowerCase();
        String hql = "from Tmember d where d.loginName ='" + loginName + "'";
        Tmember tmember = memberDao.get(hql);
        if (tmember == null) {
            tmember = new Tmember();
            tmember.setPassword(linkPersonCreateDto.getPassword());
            tmember.setLoginName(loginName);
            tmember.setCreateTime(new Date());
            tmember.setStatus(MemberStatusEnum.NORMAL.getState());
        }

        tmember.setName(linkPersonCreateDto.getLpName());
        tmember.setPhone(linkPersonCreateDto.getLpTel());

        memberDao.saveOrUpdate(tmember);
        return tmember;
    }

    private Torganization create(OrganizationCreateDto organizationCreateDto) {
        Torganization torganization = new Torganization();
        BeanUtils.copyProperties(organizationCreateDto, torganization);

        // 父级
        Torganization parentOrg = getById(organizationCreateDto.getParentId());
        torganization.setTorganization(parentOrg);

        // 类型
        torganization.setType(organizationCreateDto.getOrgType());

        // 负责人
        Tmember tmember = memberDao.get(Tmember.class,
                organizationCreateDto.getMemberId());
        torganization.setTmember(tmember);
        organizationDao.save(torganization);
        tmember.setTorganization(torganization);
        memberDao.saveOrUpdate(tmember);
        return torganization;
    }

    @Override
    public List<OrganizationExcelDto> excelImportDataRead(String path)
            throws RuntimeException {

        List<OrganizationExcelDto> organizationExcelDtos = null;
        try {
            organizationExcelDtos = MyExcelUtil
                    .readExcelOrganizationExcelDtos(path);
            for (OrganizationExcelDto e : organizationExcelDtos) {
                // 市辖区处理
                if (e.getRegion() != null && e.getSuperRegion() != null) {
                    if (e.getRegion().equals(e.getSuperRegion())) {
                        e.setRegion(e.getSuperRegion() + "市辖区");
                    }
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException("EXCEL文件读取失败，请重新选择");
        }
        return organizationExcelDtos;
    }

    private DataDictionary getDDRegionCtrl() {
        String hql = "from DataDictionary d where d.codeType = :ctrl";
        Map<String, Object> params = new HashMap<>();
        params.put("ctrl", RegionController.ddRegion);

        return dataDictionaryDao.get(hql, params);
    }

    private DataDictionary getDDRegion(String province, String city) {
        if (MyStrUtil.hasEmpty(province)) {
            province = "浙江省";
        }
        String hql = "from DataDictionary d where d.valueStr=:c and d.dataDictionaryByUplink"
                + ".valueStr = :p and d.dataDictionaryByCtrlId.codeType = :ctrl";
        Map<String, Object> params = new HashMap<>();
        params.put("p", province);
        params.put("c", city);
        params.put("ctrl", RegionController.ddRegion);

        return dataDictionaryDao.get(hql, params);
    }

    private DataDictionary getDDRegion(String province) {
        if (MyStrUtil.hasEmpty(province)) {
            province = "浙江省";
        }
        String hql = "from DataDictionary d where d.valueStr=:p and d.dataDictionaryByCtrlId"
                + ".codeType = :ctrl";
        Map<String, Object> params = new HashMap<>();
        params.put("p", province);
        params.put("ctrl", RegionController.ddRegion);

        return dataDictionaryDao.get(hql, params);
    }

    private DataDictionary getDDRegion(String province, String city,
            String district) {
        if (MyStrUtil.hasEmpty(province)) {
            province = "浙江省";
        }
        // 市本级的处理
        if (city.equals(district)) {
            district += "市辖区";
        }

        String hql = "from DataDictionary d where d.valueStr=:d and d.dataDictionaryByUplink"
                + ".valueStr = :c and d.dataDictionaryByUplink.dataDictionaryByUplink.valueStr = "
                + ":p and d.dataDictionaryByCtrlId.codeType = :ctrl";
        Map<String, Object> params = new HashMap<>();
        params.put("p", province);
        params.put("d", district);
        params.put("c", city);
        params.put("ctrl", RegionController.ddRegion);

        return dataDictionaryDao.get(hql, params);
    }

    private DataDictionary createSubDD(String valueStr, Long pId) {

        DataDictionaryModel model = new DataDictionaryModel();
        model.setValueStr(valueStr);
        model.setParentId(pId);

        DataDictionary dd = new DataDictionary();
        BeanUtils.copyProperties(model, dd, new String[] { "actionTime" });
        Date d = new Date();
        Timestamp ts = new Timestamp(d.getTime());
        dd.setActionTime(ts);
        dd.setCodeType(null);
        if (model.getParentId() != null) {
            // codeType为空，parentId不为空，表示添加的是子项
            DataDictionary parentDD = dataDictionaryDao.get(
                    DataDictionary.class, model.getParentId());
            dd.setDataDictionaryByUplink(parentDD);
            if (parentDD.getDataDictionaryByCtrlId() == null)
            // 为主项增加子项
            {
                dd.setDataDictionaryByCtrlId(parentDD);
            } else
            // 为子项增加子项
            {
                dd.setDataDictionaryByCtrlId(parentDD
                        .getDataDictionaryByCtrlId());
            }
            dd.setLevel(parentDD.getLevel() + 1);
            dd.setStatus((short) 1);
            Long ddId = (Long) dataDictionaryDao.save(dd);
            dd.makeGlobalSeq();
            return dd;
        }
        return null;// codeType为空，parentId也为空，无效添加
    }

    /**
     * 根据层级获取县区级别的dd
     */
    private DataDictionary getDDRegionCreate(String province, String city,
            String district) {
        if (MyStrUtil.hasEmpty(province)) {
            province = "浙江省";
        }

        DataDictionary ddRegionP = getDDRegion(province);
        if (ddRegionP == null) {
            ddRegionP = createSubDD(province, getDDRegionCtrl().getId());
        }

        if (MyStrUtil.hasEmpty(city)) {
            return ddRegionP;
        }

        DataDictionary ddRegionC = getDDRegion(province, city);
        if (ddRegionC == null) {
            ddRegionC = createSubDD(city, ddRegionP.getId());
        }

        if (city.equals(district)) {
            district += "市辖区";
        }

        if (MyStrUtil.hasEmpty(district)) {
            return ddRegionC;
        }

        DataDictionary ddRegionDistrict = getDDRegion(province, city, district);
        if (ddRegionDistrict == null) {
            ddRegionDistrict = createSubDD(district, ddRegionC.getId());
        }

        return ddRegionDistrict;
    }

    @Override
    public Long getRegionIdByRegion(String province, String city,
            String district) {
        return getDDRegionCreate(province, city, district).getId();
    }

    private Torganization getOrCreateByRegion(String superRegion, String region) {
        OrganizationTypeEnum city = OrganizationTypeEnum.CITY;
        OrganizationTypeEnum district = OrganizationTypeEnum.DISTRICT;

        Long superRegionId = getDDRegionCreate(null, superRegion, null).getId();
        Long regionId = getDDRegionCreate(null, superRegion, region).getId();

        String hql1 = "from Torganization d where d.regionId ='"
                + superRegionId + "' and d.type='" + city.getType() + "'";
        Torganization torganization = organizationDao.get(hql1);
        Integer parentId = null;
        if (torganization == null) {
            // 市级不存在 创建
            LinkPersonCreateDto linkPersonCreateDto = new LinkPersonCreateDto();
            linkPersonCreateDto.setLoginName("admin@" + superRegion);
            linkPersonCreateDto.setLpName(superRegion);
            // linkPersonCreateDto.setLpTel(superRegion);
            linkPersonCreateDto.setPassword("123456");

            OrganizationCreateDto organizationCreateDto = new OrganizationCreateDto();
            organizationCreateDto.setName(superRegion);
            organizationCreateDto.setRegionId(superRegionId);
            organizationCreateDto.setOrgType(OrganizationTypeEnum.CITY
                    .getType());
            organizationCreateDto.setParentId(1);

            parentId = create(linkPersonCreateDto, organizationCreateDto, null);
            torganization = getById(parentId);
        } else {
            parentId = torganization.getId();
        }

        String hql2 = "from Torganization d where d.regionId ='" + regionId
                + "' and d.type='" + district.getType()
                + "' and d.torganization.regionId ='" + superRegionId + "'";
        Torganization torganization2 = organizationDao.get(hql2);
        if (torganization2 == null) {
            // 区县级不存在 创建
            LinkPersonCreateDto linkPersonCreateDto = new LinkPersonCreateDto();
            linkPersonCreateDto.setLoginName(
                    "admin@" + (region.equals(superRegion) ? region + "市辖区" : region));
            linkPersonCreateDto.setLpName(region);
            // linkPersonCreateDto.setLpTel(region);
            linkPersonCreateDto.setPassword("123456");

            OrganizationCreateDto organizationCreateDto = new OrganizationCreateDto();
            organizationCreateDto.setName(region);
            organizationCreateDto.setRegionId(regionId);
            organizationCreateDto.setParentId(parentId);
            organizationCreateDto.setOrgType(OrganizationTypeEnum.DISTRICT
                    .getType());

            Integer integer = create(linkPersonCreateDto,
                    organizationCreateDto, null);
            torganization2 = getById(integer);
        }

        return torganization2;
    }

    @Override
    public List<Integer> excelImport(
            List<OrganizationExcelDto> organizationExcelDtos)
            throws RuntimeException {
        List<Integer> ids = new ArrayList<>();
        for (OrganizationExcelDto o : organizationExcelDtos) {
            // 设置管理账号
            LinkPersonCreateDto linkPersonCreateDto = new LinkPersonCreateDto();
            linkPersonCreateDto.setLoginName("admin@" + o.getCompanyName());
            linkPersonCreateDto.setLpName("admin@" + o.getCompanyName());
            linkPersonCreateDto.setLpTel(o.getLegalPersonTel());
            linkPersonCreateDto.setPassword("123456");

            // 组织基本信息
            OrganizationCreateDto organizationCreateDto = new OrganizationCreateDto();
            organizationCreateDto.setName(o.getCompanyName());
            if (o.getAddress() != null) {
                organizationCreateDto.setDescription("地址：" + o.getAddress());
            }
            organizationCreateDto.setLinkmanName(o.getLegalPerson());
            organizationCreateDto.setTel(o.getLegalPersonTel());
            organizationCreateDto.setEmail(o.getLegalPersonEmail());

            // 区域dd
            DataDictionary ddRegionCreate = getDDRegionCreate(null,
                    o.getSuperRegion(), o.getRegion());
            organizationCreateDto.setRegionId(ddRegionCreate.getId());

            // 设置父级别
            organizationCreateDto.setParentId(getOrCreateByRegion(
                    o.getSuperRegion(), o.getRegion()).getId());
            // 机构类型
            organizationCreateDto.setOrgType(OrganizationTypeEnum.COMPANY
                    .getType());
            organizationCreateDto.setParentId(getOrCreateByRegion(
                    o.getSuperRegion(), o.getRegion()).getId());

            OrgBasicInfoCreateDto orgBasicInfoCreateDto = new OrgBasicInfoCreateDto();
            orgBasicInfoCreateDto.setLegalPerson(o.getLegalPerson());
            orgBasicInfoCreateDto.setLegalPersonTel(o.getLegalPersonTel());
            orgBasicInfoCreateDto.setLegalPersonEmail(o.getLegalPersonEmail());

            Integer orgId = create(linkPersonCreateDto, organizationCreateDto,
                    orgBasicInfoCreateDto);

            // 设置车队长
            DriverImportDto driverImportDto = new DriverImportDto();
            BeanUtils.copyProperties(o, driverImportDto);
            getOrCreateDriver(driverImportDto, orgId, o.getRegion());

            ids.add(orgId);
        }
        return ids;
    }

    private Integer getOrCreateDriver(DriverImportDto driverImportDto,
            Integer orgId, String birthPlace) {
        return null;
    }

    private Tdriver getTdriver(String sfzNo) {
        if (MyStrUtil.hasEmpty(sfzNo)) {
            return null;
        }
        String hql = "from Tdriver d where upper(d.sfzNo)='" + sfzNo + "'";
        return driverDao.get(hql);
    }

    @Override
    public Integer modify(Integer orgId,
            OrganizationModifyDto organizationModifyDto, Integer orgBiId,
            OrgBasicInfoModifyDto orgBasicInfoModifyDto)
            throws RuntimeException {

        return null;
    }

    @Override
    public List<OrganizationListItemDto> findOrganizationListItemDtos(
            Integer ctrlOrgId) {
        if (ctrlOrgId == null) {
            return null;
        }
        String hql = "from Torganization d where (d.torganization.id = :orgId "
                + " or d.torganization.torganization.id = :orgId "
                + " or d.torganization.torganization.torganization.id = :orgId )"
                + " and d.type = :typeCpn " + " and d.status != :statusDel "
                + " order by d.id";

        Map<String, Object> params = new HashMap<>();
        params.put("orgId", ctrlOrgId);
        params.put("typeCpn", OrganizationTypeEnum.COMPANY.getType());
        params.put("statusDel", OrganizationStatusEnum.DISABLE.getStatus());

        List<Torganization> torganizations = organizationDao.find(hql, params);
        return transToOrganizationListItemDtos(torganizations);
    }

    private List<OrganizationListItemDto> transToOrganizationListItemDtos(
            List<Torganization> torganizations) {

        List<OrganizationListItemDto> organizationListItemDtos = new ArrayList<>();
        for (Torganization o : torganizations) {
            OrganizationListItemDto organizationListItemDto = transToOrganizationListItemDto(o);
            if (organizationListItemDto != null) {
                organizationListItemDtos.add(organizationListItemDto);
            }
        }
        return organizationListItemDtos;
    }

    private OrganizationListItemDto transToOrganizationListItemDto(
            Torganization torganization) {
        if (torganization == null) {
            return null;
        }
        OrganizationListItemDto organizationListItemDto = new OrganizationListItemDto();
        BeanUtils.copyProperties(torganization, organizationListItemDto);
        return organizationListItemDto;
    }

    @Override
    public Integer findTheNearestCompany(Integer memberOrgId, Integer orgId) {
        try {
            // 当前orgId为车企时为true
            Boolean flag = false;
            if (orgId != null) {
                String hql = "from Torganization sdy where sdy.id='" + orgId
                        + "'";
                Torganization orgNow = organizationDao.get(hql);
                if (orgNow != null
                        && orgNow.getType() == OrganizationTypeEnum.COMPANY
                        .getType()) {
                    flag = true;
                }
            }

            if (orgId == null || !flag) {
                String hql = "from Torganization sdy where sdy.id='"
                        + memberOrgId + "'";
                Torganization memberOrganization = organizationDao.get(hql);
                if (memberOrganization != null
                        && memberOrganization.getType() == OrganizationTypeEnum.COMPANY
                        .getType()) {
                    return memberOrgId;
                }
                // 当前组织为空，还未指定车企(第一次点入)。选择该散装办下的第一个车企为展示车企
                hql = "from Torganization sdy where 1=1 "
                        + "and sdy.torganization.id=:orgId ";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("orgId", memberOrgId);
                List<Torganization> torganizations = organizationDao.find(hql,
                        params);
                Integer orgNow = null;
                int i = 0;
                int sum = torganizations.size();
                Stack<Integer> orgChangeId = new Stack<>();
                for (i = 0; i < sum; i++) {
                    Torganization torganization = torganizations.get(i);
                    if (torganization.getType() != OrganizationTypeEnum.COMPANY
                            .getType()) {
                        if (i == sum - 1) {
                            if (!orgChangeId.empty()) {
                                Integer iPre = orgChangeId.peek();
                                i = iPre + 1;
                                orgChangeId.pop();
                            }
                        }
                        // 不为车企，将子组织加入list
                        hql = "from Torganization sdy where 1=1 "
                                + "and sdy.torganization.id=:orgId ";
                        params.clear();
                        params.put("orgId", torganization.getId());
                        List<Torganization> torganizations2 = organizationDao
                                .find(hql, params);
                        // 增加遍历长度
                        torganizations.addAll(torganizations2);
                        // 记录转变点，为了当深度遍历完没有找到车企时寻找另外的组织的子组织
                        Integer iPre = i;
                        orgChangeId.push(iPre);
                        // 从新加的组织开始遍历，为了更快找到一个车企
                        i = sum - 1;
                        sum += torganizations2.size();
                    } else {
                        // 遍历到的第一个车企作为展示车企
                        orgNow = torganization.getId();
                        break;
                    }
                }
                if (orgNow == null) {
                    throw new NoSonCompanyException("找不到子车企");
                }
                return orgNow;
            } else {
                // 已有指定的车企，直接返回
                return orgId;
            }
        } catch (NoSonCompanyException e1) {
            throw e1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int updateOrgInfo(String path) throws RuntimeException {
        try {

            List<OrganizationExcelUpdateInfoDto> organizationExcelUpdateInfoDtos = MyExcelUtil
                    .readOrganizationExcelUpdateInfoDtos(
                            path);

            if (organizationExcelUpdateInfoDtos == null
                    || organizationExcelUpdateInfoDtos.size() == 0) {
                throw new RuntimeException("excel读取失败或者表格为空");
            }

            int count = 0;
            for (OrganizationExcelUpdateInfoDto o : organizationExcelUpdateInfoDtos) {

                Torganization orgGetByRegion = getOrCreateByRegion(o.getCity(), o.getDistrict());
                if (orgGetByRegion != null) {

                    orgGetByRegion.setName(o.getOrgName());
                    orgGetByRegion.setShortName(
                            MyStrUtil.hasEmpty(o.getOrgShortName()) ? null : o.getOrgShortName());
                    orgGetByRegion.setLinkmanName(o.getContactName());
                    orgGetByRegion.setEmail(o.getContactEmail());
                    orgGetByRegion.setTel(telCat(o.getContactTel(), o.getContactPhone()));

                    if (!MyStrUtil.hasEmpty(o.getOrgIntro())) {
                        orgGetByRegion.setDescription(o.getOrgIntro());
                    }

                    Tmember tmember = orgGetByRegion.getTmember();
                    if (tmember != null) {
                        tmember.setName(o.getManagerName());
                        tmember.setPhone(telCat(o.getManagerTel(), o.getManagerPhone()));
                    }

                    if (o.getCity().equals(o.getDistrict())) {
                        //市辖区
                        //设置其父级
                        Torganization orgParent = orgGetByRegion.getTorganization();

                        orgParent.setName(o.getOrgName());
                        orgParent.setShortName(MyStrUtil.hasEmpty(o.getOrgShortName()) ? null
                                : o.getOrgShortName());
                        orgParent.setLinkmanName(o.getContactName());
                        orgParent.setEmail(o.getContactEmail());
                        orgParent.setTel(telCat(o.getContactTel(), o.getContactPhone()));

                        if (!MyStrUtil.hasEmpty(o.getOrgIntro())) {
                            orgParent.setDescription(o.getOrgIntro());
                        }

                        Tmember tmember2 = orgParent.getTmember();
                        if (tmember2 != null) {
                            tmember2.setName(o.getManagerName());
                            tmember2.setPhone(telCat(o.getManagerTel(), o.getManagerPhone()));
                        }
                        memberDao.saveOrUpdate(tmember2);
                        organizationDao.saveOrUpdate(orgParent);
                        count++;
                    }

                    memberDao.saveOrUpdate(tmember);
                    organizationDao.saveOrUpdate(orgGetByRegion);

                    count++;
                } else {
                    //数据处理失败
                    System.out.println("数据处理失败 orgGetByRegion == null , " + JSON.toJSONString(o));
                }
            }

            return count;
        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    private String telCat(String phone1, String phone2) {
        boolean phone1Exsit = !MyStrUtil.hasEmpty(phone1);
        boolean phone2Exsit = !MyStrUtil.hasEmpty(phone2);
        if (phone1Exsit && phone2Exsit) {
            return phone1 + " / " + phone2;
        } else if (phone1Exsit) {
            return phone1;
        } else if (phone2Exsit) {
            return phone2;
        } else {
            return null;
        }
    }

    private class DriverImportDto {

        private String driverName;
        private String driverTel;
        private String driverEmail;
        private String driverSfzNo;

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverTel() {
            return driverTel;
        }

        public void setDriverTel(String driverTel) {
            this.driverTel = driverTel;
        }

        public String getDriverEmail() {
            return driverEmail;
        }

        public void setDriverEmail(String driverEmail) {
            this.driverEmail = driverEmail;
        }

        public String getDriverSfzNo() {
            return driverSfzNo;
        }

        public void setDriverSfzNo(String driverSfzNo) {
            this.driverSfzNo = driverSfzNo;
        }
    }

    @Override
    public Integer create(LinkPersonCreateDto linkPersonCreateDto,
            OrganizationCreateDto organizationCreateDto,
            OrgBasicInfoCreateDto orgBasicInfoCreateDto)
            throws RuntimeException {
        // TODO Auto-generated method stub
        return null;
    }
}
