package com.zust.itee.manager.impl.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zust.itee.dto.LearningResourceDto;
import com.zust.itee.dto.LearningResourceQuestionDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.resource.LearningResourceSaveDto;
import com.zust.itee.dto.request.resource.LearningResourceSearchDto;
import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.datadictionary.DataDictionary;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.user.User;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.org.OrgLevelType;
import com.zust.itee.enums.resource.LearningResourceAuthType;
import com.zust.itee.enums.resource.LearningResourceType;
import com.zust.itee.enums.user.UserType;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.org.OrganizationManager;
import com.zust.itee.manager.resource.LearningResourceManager;
import com.zust.itee.manager.resource.LearningResourceQuestionManager;
import com.zust.itee.service.datadictionary.DataDictionaryService;
import com.zust.itee.service.org.OrganizationService;
import com.zust.itee.service.resource.LearningResourceService;
import com.zust.itee.service.user.UserService;
import com.zust.itee.util.FunctionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 学习资源 manager 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class LearningResourceManagerImpl implements LearningResourceManager {

    @Resource
    private LearningResourceService learningResourceService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Resource
    private OrganizationManager organizationManager;

    @Resource
    private LearningResourceQuestionManager learningResourceQuestionManager;

    @Resource
    private UserService userService;

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<LearningResourceDto> searchLearningResource(Short userType, Integer orgId,
            Short userLevel, LearningResourceSearchDto searchDto, PageBaseDto pageBaseDto)
            throws BusinessException {
        log.info("==学习资源搜索== userType:{},orgId:{},search:{},page:{}", userType, orgId, searchDto,
                pageBaseDto);
        List<LearningResource> resources = null;
        Long sum = null;
        LearningResource query = convertToEntity(searchDto);
        DateFilter dateFilter = DateFilter.builder()
                .startTime(searchDto.getStartTime())
                .endTime(searchDto.getEndTime())
                .build();
        if (UserType.PERSONAL.getValue() != userType) {
            // 平台用户、单位用户不涉及权限
            log.info("==学习资源搜索，平台用户、单位用户结合组织==");
            if (UserType.ROOT.getValue() != userType) {
                query.setOrgId(orgId);
            }
            log.info("==学习资源搜索，转换后 query == query:{},date:{},page:{}", query, dateFilter,
                    pageBaseDto);

            resources = learningResourceService.listBySearch(query, dateFilter,
                    pageBaseDto.getPage(), pageBaseDto.getRows());
            log.info("==学习资源搜索，搜索结果== res:{}", resources);
            sum = learningResourceService.countBySearch(query, dateFilter);
            log.info("==学习资源搜索，搜索总数== sum:{}", sum);
        } else {
            // 个人用户结合权限搜索
            log.info("==学习资源搜索，个人用户结合权限==");
            Organization root = organizationManager.getRootOrg();
            Short queryLevel = userLevel;
            if (root.getId().equals(orgId)) {
                // 注册个人用户仅可见开放所有权限资源
                queryLevel = Short.MAX_VALUE;
            }
            log.info("==学习资源搜索，转换后 query == query:{},date:{},page:{},userLevel:{}", query,
                    dateFilter, pageBaseDto, queryLevel);

            resources = learningResourceService.listBySearchAndAuth(query, queryLevel, dateFilter,
                    pageBaseDto.getPage(), pageBaseDto.getRows());
            log.info("==学习资源搜索，搜索结果== res:{}", resources);
            sum = learningResourceService.countBySearchAndAuth(query, queryLevel, dateFilter);
            log.info("==学习资源搜索，搜索总数== sum:{}", sum);
        }
        List<LearningResourceDto> res = convertToDtoList(resources);
        pageBaseDto.setSumAndPageNum(sum);
        pageBaseDto.setData(res);
        log.info("==学习资源搜索，转换后结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    @Override
    public void saveLearningResource(SessionInfo sessionInfo, LearningResourceSaveDto saveDto)
            throws BusinessException {
        log.info("==新增学习资源== session:{},save:{}", sessionInfo, saveDto);
        LearningResource learningResource = convertToEntity(saveDto, sessionInfo);
        log.info("==新增学习资源，转换后实体== learningResource:{}", learningResource);
        learningResourceService.upsert(learningResource);
    }

    @Override
    public LearningResourceDto getById(Integer id) {
        log.info("==根据 id 获取学习资源== id:{}", id);
        LearningResource learningResource = validateNotNull(id);
        log.info("==根基 id 获取学习资源，查库结果== resource:{}", learningResource);
        LearningResourceDto res = convertToDto(learningResource);
        log.info("==根据 id 获取学习资源，转换后结果== res:{}", res);
        return res;
    }

    @Override
    public void updateLearningResource(Integer id, LearningResourceSaveDto updateDto)
            throws BusinessException {
        log.info("==更新学习资源== id:{},update:{}", id, updateDto);
        validateNotNull(id);
        LearningResource learningResource = convertToEntity(updateDto, id);
        log.info("==更新学习资源，转换后实体== id:{},update:{}", id, updateDto);
        learningResourceService.upsert(learningResource);
    }

    @Override
    public void deleteLearningResource(Integer id) throws BusinessException {
        log.info("==删除学习资源== id:{}", id);
        LearningResource learningResource = validateNotNull(id);
        learningResource.setStatus(NormalStatusType.DELETE.getStatus());
        learningResourceService.upsert(learningResource);
    }

    @Override
    public void updateResourceStatus(Integer id, Short status) throws BusinessException {
        log.info("==更新资源状态== id:{},status:{}", id, status);
        Optional<NormalStatusType> statusTypeOptional = NormalStatusType.getByStatus(status);
        if (!statusTypeOptional.isPresent()) {
            throw new BusinessException("修改状态不合法");
        }
        LearningResource resource = validateNotNull(id);
        resource.setStatus(status);
        learningResourceService.upsert(resource);
    }

    private LearningResource validateNotNull(Integer id) {
        LearningResource learningResource = learningResourceService.getById(id);
        if (learningResource == null) {
            throw new BusinessException(String.format("学习资源不存在,id:%s", id));
        }
        return learningResource;
    }

    @Override
    public LearningResourceDto convertToDto(LearningResource learningResource) {
        final LearningResourceDto learningResourceDto = new LearningResourceDto();
        BeanUtils.copyProperties(learningResource, learningResourceDto);

        // 资源类型
        Optional<LearningResourceType> resourceType = LearningResourceType.getByType(
                learningResource.getType());
        resourceType.ifPresent(type -> learningResourceDto.setTypeName(type.getName()));

        // 组织名称
        if (learningResource.getOrgId() != null) {
            Organization organization = organizationService.getById(learningResource.getOrgId());
            if (organization != null) {
                learningResourceDto.setOrgName(organization.getName());
            }
        }

        // 资源等级
        OrgLevelType orgLevelType = OrgLevelType.getByLevel(learningResource.getLevel());
        if (orgLevelType != null) {
            learningResourceDto.setLevelName(orgLevelType.getName());
        }

        // 条线名称
        if (learningResource.getTypeId() != null) {
            DataDictionary dataDictionary = dataDictionaryService.getById(
                    learningResource.getTypeId());
            if (dataDictionary != null) {
                learningResourceDto.setTypeIdName(dataDictionary.getValueStr());
            }
        }

        // 权限类型名称
        Optional<LearningResourceAuthType> authType = LearningResourceAuthType.getByType(
                learningResource.getAuthType());
        authType.ifPresent(type -> learningResourceDto.setAuthTypeName(type.getName()));

        // 状态名称
        Optional<NormalStatusType> normalStatusType = NormalStatusType.getByStatus(
                learningResource.getStatus());
        normalStatusType.ifPresent(
                type -> learningResourceDto.setStatusName(type.getDescription()));

        // 资源问题
        List<LearningResourceQuestionDto> questionDtos = learningResourceQuestionManager
                .listResourceQuestions(
                        learningResource.getId());
        learningResourceDto.setQuestions(questionDtos);

        // 讲师用户名称
        User lecturer = userService.getById(learningResource.getLecturerId());
        if (lecturer != null) {
            learningResourceDto.setLecturerUserName(lecturer.getName());
        }

        // 资源时长
        Integer duration = learningResource.getDuration();
        learningResourceDto.setDurationHour(FunctionUtil.getDurationHour(duration));
        learningResourceDto.setDurationMinute(FunctionUtil.getDurationMinute(duration));
        learningResourceDto.setDurationSecond(FunctionUtil.getDurationSecond(duration));

        return learningResourceDto;
    }

    @Override
    public List<LearningResourceDto> convertToDtoList(List<LearningResource> learningResources) {
        return learningResources.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private LearningResource convertToEntity(LearningResourceSearchDto searchDto) {
        LearningResource learningResource = new LearningResource();
        BeanUtils.copyProperties(searchDto, learningResource);
        return learningResource;
    }

    private LearningResource convertToEntity(LearningResourceSaveDto saveDto,
            SessionInfo sessionInfo) {
        LearningResource learningResource = new LearningResource();
        BeanUtils.copyProperties(saveDto, learningResource);
        learningResource.setUserId(sessionInfo.getOtaUserId());
        learningResource.setUserName(sessionInfo.getName());
        learningResource.setOrgId(sessionInfo.getOrgId());
        learningResource.setStatus(NormalStatusType.NORMAL.getStatus());
        return learningResource;
    }

    private LearningResource convertToEntity(LearningResourceSaveDto updateDto, Integer id) {
        LearningResource learningResource = new LearningResource();
        BeanUtils.copyProperties(updateDto, learningResource);
        learningResource.setId(id);
        return learningResource;
    }
}
