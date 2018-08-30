package com.zust.itee.manager.impl.training;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.dto.LearningResourceDto;
import com.zust.itee.dto.TrainingResourceDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.training.TrainingResourceSaveDto;
import com.zust.itee.dto.request.training.TrainingResourceSearchDto;
import com.zust.itee.dto.request.training.TrainingResourceUpdateDto;
import com.zust.itee.dto.request.training.TrainingSearchDto;
import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.training.TrainingResource;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.training.TrainingResourceRequiredType;
import com.zust.itee.enums.user.UserType;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.org.OrganizationManager;
import com.zust.itee.manager.resource.LearningResourceManager;
import com.zust.itee.manager.training.TrainingResourceManager;
import com.zust.itee.service.resource.LearningResourceService;
import com.zust.itee.service.training.TrainingResourceService;
import com.zust.itee.service.training.TrainingService;

import lombok.extern.slf4j.Slf4j;

/**
 * 培训资源 manager 实现类
 *
 * @author pojun
 */
@Service
@Slf4j
public class TrainingResourceManagerImpl implements TrainingResourceManager {

    @Resource
    private LearningResourceService learningResourceService;

    @Resource
    private TrainingService trainingService;

    @Resource
    private TrainingResourceService trainingResourceService;

    @Resource
    private LearningResourceManager learningResourceManager;

    @Resource
    private OrganizationManager organizationManager;

    @Override
    public void saveTrainingResource(TrainingResourceSaveDto trainingResourceSaveDto)
            throws BusinessException {
        log.info("==添加培训资源== saveDto:{}", trainingResourceSaveDto);
        if (trainingService.getById(trainingResourceSaveDto.getTrainingId()) == null) {
            throw new BusinessException(
                    String.format("培训不存在,id:%s", trainingResourceSaveDto.getTrainingId()));
        }
        LearningResource learningResource = learningResourceService.getById(
                trainingResourceSaveDto.getResourceId());
        if (learningResource == null) {
            throw new BusinessException(
                    String.format("学习资源不存在,id:%s", trainingResourceSaveDto.getResourceId()));
        }

        TrainingResource query = TrainingResource.builder()
                .trainingId(trainingResourceSaveDto.getTrainingId())
                .resourceId(trainingResourceSaveDto.getResourceId())
                .build();
        List<TrainingResource> exists = trainingResourceService.listByFilter(query, 1, 1);
        if (exists != null && !exists.isEmpty()) {
            log.info("==添加培训资源，原有培训资源== exists:{}", JSONObject.toJSONString(exists));
            TrainingResource exist = exists.get(0);
            exist.setStatus(NormalStatusType.NORMAL.getStatus());
            trainingResourceService.upsert(exist);
            return;
        }

        TrainingResource trainingResource = new TrainingResource();
        BeanUtils.copyProperties(trainingResourceSaveDto, trainingResource);
        trainingResource.setStatus(NormalStatusType.NORMAL.getStatus());
        trainingResource.setScore(learningResource.getScore());
        trainingResource.setResourceName(learningResource.getName());
        trainingResource.setLecturerName(learningResource.getLecturerName());
        trainingResource.setTypeId(learningResource.getTypeId());
        trainingResource.setLevel(learningResource.getLevel());
        log.info("==添加培训资源，添加 entity== learnedResources:{}", trainingResource);
        trainingResourceService.upsert(trainingResource);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<LearningResourceDto> listUnselectedResources(SessionInfo sessionInfo,
            Integer trainingId, TrainingSearchDto searchDto, PageBaseDto pageBaseDto) {
        log.info("==搜索筛选未选择进培训的学习资源== session:{},trainingId:{},search:{},page:{}", searchDto,
                trainingId, searchDto, pageBaseDto);
        LearningResource query = new LearningResource();
        BeanUtils.copyProperties(searchDto, query);
        DateFilter dateFilter = DateFilter.builder()
                .startTime(searchDto.getStartTime())
                .endTime(searchDto.getEndTime())
                .build();
        Integer rootOrgId = null;
        if (UserType.COMPANY.getValue() == sessionInfo.getType()) {
            log.info("==搜索筛选未选择进培训的学习资源，单位用户添加组织筛选==");
            query.setOrgId(sessionInfo.getOrgId());
            Organization root = organizationManager.getRootOrg();
            rootOrgId = root.getId();
        } else if (UserType.ROOT.getValue() != sessionInfo.getType()) {
            throw new BusinessException("仅平台/单位用户有该权限");
        }
        List<LearningResource> resources = trainingResourceService.listUnselectedResource(query,
                dateFilter, trainingId, rootOrgId, pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==搜索筛选未选择进培训的学习资源,查库结果== resources:{}", resources);
        Long sum = trainingResourceService.countUnselectedResource(query, dateFilter, trainingId,
                rootOrgId);
        log.info("==搜索筛选未选择进培训的学习资源,查库总数== sum:{}", sum);
        pageBaseDto.setData(learningResourceManager.convertToDtoList(resources));
        pageBaseDto.setSumAndPageNum(sum);
        return pageBaseDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<TrainingResourceDto> listTrainingResources(Integer trainingId,
            PageBaseDto pageBaseDto, TrainingResourceSearchDto searchDto) throws BusinessException {
        log.info("==获取培训资源== trainingId:{},page:{}", trainingId, pageBaseDto);
        TrainingResource query = new TrainingResource();
        BeanUtils.copyProperties(searchDto, query);
        query.setTrainingId(trainingId);
        log.info("==获取培训资源,查询培训资源== query:{},page:{}", query, pageBaseDto);
        List<TrainingResource> trainingResources = trainingResourceService
                .listBySearchWithoutDeleted(query, pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==获取培训资源，查询结果== resources:{}", trainingResources);
        Long sum = trainingResourceService.countBySearchWithoutDeleted(query);
        log.info("==获取培训资源，查询总数== sum:{}", sum);

        List<TrainingResourceDto> trainingResourceDtos = convertToDtoList(trainingResources);
        pageBaseDto.setData(trainingResourceDtos);
        pageBaseDto.setSumAndPageNum(sum);
        log.info("==获取培训资源，转换后结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    @Override
    public void updateTrainingResource(Integer id, TrainingResourceUpdateDto updateDto)
            throws BusinessException {
        log.info("==更新培训资源== id:{},update:{}", id, updateDto);
        TrainingResource trainingResource = validateNotNull(id);
        BeanUtils.copyProperties(updateDto, trainingResource);
        trainingResource.setId(id);
        trainingResourceService.upsert(trainingResource);
    }

    @Override
    public void deleteTrainingResource(Integer id) throws BusinessException {
        log.info("==删除培训资源== id{}", id);
        TrainingResource trainingResource = validateNotNull(id);
        trainingResource.setStatus(NormalStatusType.DELETE.getStatus());
        trainingResourceService.upsert(trainingResource);
    }

    @Override
    public void deleteTrainingResource(Integer trainingId, Integer resourceId)
            throws BusinessException {
        log.info("==删除培训资源== trainingId{},resourceId:{}", trainingId, resourceId);
        List<TrainingResource> trainingResources = trainingResourceService.listByFilter(
                TrainingResource.builder()
                        .trainingId(trainingId)
                        .resourceId(resourceId)
                        .build(), 1, 1);
        if (trainingResources == null || trainingResources.isEmpty()) {
            throw new BusinessException(
                    String.format("培训资源不存在,trainingId:%s,resourceId:%s", trainingId, resourceId));
        }

        TrainingResource trainingResource = trainingResources.get(0);
        trainingResource.setStatus(NormalStatusType.DELETE.getStatus());
        trainingResourceService.upsert(trainingResource);
    }

    @Override
    public TrainingResourceDto convertToDto(TrainingResource trainingResource) {
        TrainingResourceDto trainingResourceDto = new TrainingResourceDto();
        BeanUtils.copyProperties(trainingResource, trainingResourceDto);
        // 学习资源
        LearningResource learningResource = learningResourceService.getById(
                trainingResource.getResourceId());
        LearningResourceDto resourceDto = learningResourceManager.convertToDto(learningResource);
        trainingResourceDto.setLearningResource(resourceDto);

        // 是否必修
        Optional<TrainingResourceRequiredType> requiredType = TrainingResourceRequiredType
                .getByType(
                        trainingResource.getRequired());
        requiredType.ifPresent(type -> trainingResourceDto.setRequiredName(type.getName()));

        // 状态名称
        Optional<NormalStatusType> normalStatusType = NormalStatusType.getByStatus(
                trainingResource.getStatus());
        normalStatusType.ifPresent(
                statusType -> trainingResourceDto.setStatusName(statusType.getDescription()));

        return trainingResourceDto;
    }

    private List<TrainingResourceDto> convertToDtoList(List<TrainingResource> trainingResources) {
        return trainingResources.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TrainingResource validateNotNull(Integer id) {
        TrainingResource trainingResource = trainingResourceService.getById(id);
        if (trainingResource == null) {
            throw new BusinessException(String.format("培训资源不存在,id:%s", id));
        }
        return trainingResource;
    }
}
