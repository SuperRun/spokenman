package com.zust.itee.service.impl.training;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.constans.Constants;
import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.training.TrainingResource;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.mapper.training.TrainingResourceMapper;
import com.zust.itee.service.training.TrainingResourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * 培训资源 service 实现类
 *
 * @author pojun
 */
@Service
@Slf4j
public class TrainingResourceServiceImpl implements TrainingResourceService {

    @Resource
    private TrainingResourceMapper trainingResourceMapper;

    @Override
    public Integer upsert(TrainingResource trainingResource) {
        log.info("==插入或更新培训资源== description:{}", trainingResource);
        if (trainingResource.getId() == null || getById(trainingResource.getId()) == null) {
            log.info("==插入或更新培训资源，插入培训资源== description:{}", trainingResource);
            return trainingResourceMapper.save(trainingResource);
        } else {
            log.info("==插入或更新培训资源，更新培训资源== description:{}", trainingResource);
            return trainingResourceMapper.update(trainingResource);
        }
    }

    @Override
    public TrainingResource getById(Integer id) {
        log.info("==根据id获取培训资源== id:{}", id);
        return trainingResourceMapper.getById(id);
    }

    @Override
    public List<TrainingResource> listByFilter(TrainingResource trainingResource, Integer page,
            Integer limit) {
        log.info("==精准查询培训资源== description:{},page:{},limit:{}", trainingResource, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingResource)));
        return trainingResourceMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(TrainingResource trainingResource) {
        return trainingResourceMapper.countByFilter(trainingResource);
    }

    @Override
    public List<TrainingResource> listByFilterWithoutDeleted(TrainingResource trainingResource,
            Integer page, Integer limit) {
        log.info("==精准查询培训资源，排除已删除== description:{},page:{},limit:{}", trainingResource, page,
                limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingResource)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingResourceMapper.listByFilter(params);
    }

    @Override
    public Long countByFilterWithoutDeleted(TrainingResource trainingResource) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingResource)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingResourceMapper.countByFilterWithoutDeleted(params);
    }

    @Override
    public List<TrainingResource> listBySearchWithoutDeleted(TrainingResource trainingResource,
            Integer page, Integer limit) {
        log.info("==搜索查询培训资源，排除已删除== description:{},page:{},limit:{}", trainingResource, page,
                limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        if (StringUtils.isNotBlank(trainingResource.getResourceName())) {
            trainingResource.setResourceName("%" + trainingResource.getResourceName() + "%");
        } else {
            trainingResource.setResourceName(null);
        }
        if (StringUtils.isNotBlank(trainingResource.getLecturerName())) {
            trainingResource.setLecturerName("%" + trainingResource.getLecturerName() + "%");
        } else {
            trainingResource.setLecturerName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingResource)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingResourceMapper.listBySearchWithoutDeleted(params);
    }

    @Override
    public Long countBySearchWithoutDeleted(TrainingResource trainingResource) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(trainingResource.getResourceName())) {
            trainingResource.setResourceName("%" + trainingResource.getResourceName() + "%");
        } else {
            trainingResource.setResourceName(null);
        }
        if (StringUtils.isNotBlank(trainingResource.getLecturerName())) {
            trainingResource.setLecturerName("%" + trainingResource.getLecturerName() + "%");
        } else {
            trainingResource.setLecturerName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingResource)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingResourceMapper.countBySearchWithoutDeleted(params);
    }

    @Override
    public List<LearningResource> listUnselectedResource(LearningResource learningResource,
            DateFilter dateFilter, Integer trainingId, Integer rootOrgId, Integer page,
            Integer limit) {
        log.info("==搜索查询未选择进培训学习资源 == resource:{},date:{},trainingId:{},page:{},limit:{}",
                learningResource, dateFilter, trainingId, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.put(Constants.Mapper.START_TIME, dateFilter.getStartTime());
        params.put(Constants.Mapper.END_TIME, dateFilter.getEndTime());
        if (StringUtils.isNotBlank(learningResource.getName())) {
            learningResource.setName("%" + learningResource.getName() + "%");
        } else {
            learningResource.setName(null);
        }
        if (StringUtils.isNotBlank(learningResource.getUserName())) {
            learningResource.setUserName("%" + learningResource.getUserName() + "%");
        } else {
            learningResource.setUserName(null);
        }
        if (StringUtils.isNotBlank(learningResource.getLecturerName())) {
            learningResource.setLecturerName("%" + learningResource.getLecturerName() + "%");
        } else {
            learningResource.setLecturerName(null);
        }

        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResource)));
        params.put(Constants.Mapper.TRAINING_ID, trainingId);
        params.put(Constants.Mapper.ROOT_ORG_ID, rootOrgId);

        return trainingResourceMapper.listUnselectedResource(params);
    }

    @Override
    public Long countUnselectedResource(LearningResource learningResource,
            DateFilter dateFilter, Integer trainingId, Integer rootOrgId) {
        log.info("==搜索查询未选择进培训学习资源数量 == resource:{},date:{},trainingId:{}", learningResource,
                dateFilter, trainingId);
        Map<String, Object> params = new HashMap<>();
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResource)));
        params.put(Constants.Mapper.START_TIME, dateFilter.getStartTime());
        params.put(Constants.Mapper.END_TIME, dateFilter.getEndTime());
        if (StringUtils.isNotBlank(learningResource.getName())) {
            learningResource.setName("%" + learningResource.getName() + "%");
        } else {
            learningResource.setName(null);
        }
        if (StringUtils.isNotBlank(learningResource.getUserName())) {
            learningResource.setUserName("%" + learningResource.getUserName() + "%");
        } else {
            learningResource.setUserName(null);
        }
        if (StringUtils.isNotBlank(learningResource.getLecturerName())) {
            learningResource.setLecturerName("%" + learningResource.getLecturerName() + "%");
        }
        params.put(Constants.Mapper.TRAINING_ID, trainingId);
        params.put(Constants.Mapper.ROOT_ORG_ID, rootOrgId);
        return trainingResourceMapper.countUnselectedResource(params);
    }
}
