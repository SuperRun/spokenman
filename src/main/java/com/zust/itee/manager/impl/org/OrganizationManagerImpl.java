package com.zust.itee.manager.impl.org;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.constans.Constants;
import com.zust.itee.dto.OrganizationDto;
import com.zust.itee.dto.request.userapply.OrgUserApply;
import com.zust.itee.entity.datadictionary.DataDictionary;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.user.User;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.org.OrgLevelType;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.org.OrganizationManager;
import com.zust.itee.service.datadictionary.DataDictionaryService;
import com.zust.itee.service.org.OrganizationService;
import com.zust.itee.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 组织 manager 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class OrganizationManagerImpl implements OrganizationManager {

    @Resource
    private OrganizationService organizationService;

    @Resource
    private UserService userService;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Resource
    private com.zust.itee.exam.service.OrganizationService examOrgService;

    @Override
    public List<OrganizationDto> listByAreaAndTypeAndLevel(Long areaId, Long typeId, Short level)
            throws RuntimeException {
        log.info("==按照组织区域及条线、等级筛选组织== areaId:{},typeId:{}", areaId, typeId, level);
        List<Organization> organizations = organizationService.listByFilter(
                Organization.builder().areaId(areaId).typeId(typeId).level(level).build(), 1,
                Integer.MAX_VALUE);
        log.info("==按照组织区域及条线、等级筛选组织，ota-org 响应== orgs:{}", organizations);
        List<OrganizationDto> res = convertDtoList(organizations);
        log.info("==按照组织区域及条线、等级筛选组织，转换后结果== res:{}", res);
        return res;
    }

    @Override
    @Transactional
    public Integer saveOrg(Integer userId, OrganizationDto organizationDto)
            throws BusinessException {
        log.info("==新增组织== userId:{},org:{}", userId, organizationDto);
        organizationDto.setCreatorId(userId);
        Organization organization = convertToEntity(organizationDto);
        log.info("==新增组织，转换后组织== userId:{},org:{}", userId, organization);
        Integer orgId = organizationService.upsert(organization);
        // 创建考试系统组织
        examOrgService.saveByOtaOrg(organization);
        return orgId;
    }

    @Override
    public Integer saveOrg(User user, OrgUserApply orgUserApply)
            throws BusinessException {
        log.info("==新增单位用户后，新增单位== user:{},org:{}", user, orgUserApply);
        if (user.getLevel() == null
                || user.getAreaId() == null
                || user.getTypeId() == null) {
            throw new BusinessException("新增单位等级、区域或条线为空");
        }
        Organization organization = Organization.builder()
                .level(user.getLevel())
                .areaId(user.getAreaId())
                .typeId(user.getTypeId())
                .creatorId(user.getId())
                .name(user.getOrgName())
                .userId(user.getId())
                .status(NormalStatusType.NORMAL.getStatus())
                .build();
        log.info("==新增单位用户后，新增单位，请求信息== org:{}", organization);
        organizationService.upsert(organization);
        // 新增考试系统组织
        examOrgService.saveByOtaOrg(organization);
        return organization.getId();
    }

    @Override
    public Organization getRootOrg() throws BusinessException {
        List<Organization> organizations = organizationService.listByFilter(
                Organization.builder()
                        .level(Constants.Org.ROOT_ORG_LEVEL)
                        .name(Constants.Org.ROOT_ORG_NAME)
                        .shortName(Constants.Org.ROOT_ORG_SHORT_NAME)
                        .build(), 1, 1);
        if (organizations == null || organizations.isEmpty()) {
            throw new BusinessException(
                    String.format("平台组织不存在，请联系管理员添加。level:%s,name:%s,shortName:%s",
                            Constants.Org.ROOT_ORG_LEVEL, Constants.Org.ROOT_ORG_NAME,
                            Constants.Org.ROOT_ORG_SHORT_NAME));
        }
        return organizations.get(0);
    }

    private OrganizationDto convertToDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        BeanUtils.copyProperties(organization, organizationDto);

        // 设置组织等级
        OrgLevelType orgLevelType = OrgLevelType.getByLevel(organizationDto.getLevel());
        if (orgLevelType != null) {
            organizationDto.setLevelName(orgLevelType.getName());
        }

        // 设置条线
        DataDictionary type = dataDictionaryService.getById(organizationDto.getTypeId());
        if (type != null) {
            organizationDto.setTypeName(type.getValueStr());
        }

        // 设置状态
        Optional<NormalStatusType> statusType = NormalStatusType.getByStatus(
                organizationDto.getStatus());
        statusType.ifPresent(normalStatusType -> organizationDto.setStatusName(
                normalStatusType.getDescription()));

        return organizationDto;
    }

    private List<OrganizationDto> convertDtoList(List<Organization> organizations) {
        return organizations.stream().map(this::convertToDto).collect(toList());
    }

    private Organization convertToEntity(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDto, organization);
        return organization;
    }
}
