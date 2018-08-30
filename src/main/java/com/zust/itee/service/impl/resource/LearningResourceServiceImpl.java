package com.zust.itee.service.impl.resource;

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
import com.zust.itee.enums.resource.LearningResourceAuthType;
import com.zust.itee.mapper.resource.LearningResourceMapper;
import com.zust.itee.service.resource.LearningResourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * 学习资源 service 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class LearningResourceServiceImpl implements LearningResourceService {

    @Resource
    private LearningResourceMapper learningResourceMapper;

    @Override
    public Integer upsert(LearningResource learningResource) {
        log.info("==插入或更新学习资源== description:{}", learningResource);
        if (learningResource.getId() == null || getById(learningResource.getId()) == null) {
            log.info("==插入或更新学习资源，插入学习资源== description:{}", learningResource);
            return learningResourceMapper.save(learningResource);
        } else {
            log.info("==插入或更新学习资源，更新学习资源== description:{}", learningResource);
            return learningResourceMapper.update(learningResource);
        }
    }

    @Override
    public LearningResource getById(Integer id) {
        log.info("==根据id获取学习资源== id:{}", id);
        return learningResourceMapper.getById(id);
    }

    @Override
    public List<LearningResource> listByFilter(LearningResource learningResource, Integer page,
            Integer limit) {
        log.info("==精准查询学习资源== description:{},page:{},limit:{}", learningResource, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResource)));
        return learningResourceMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(LearningResource learningResource) {
        return learningResourceMapper.countByFilter(learningResource);
    }

    @Override
    public List<LearningResource> listBySearch(LearningResource learningResource,
            DateFilter dateFilter, Integer page, Integer limit) {
        log.info("==搜索查询学习资源 == resource:{},date:{},page:{},limit:{}", learningResource, dateFilter,
                page, limit);
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

        return learningResourceMapper.listBySearch(params);
    }

    @Override
    public Long countBySearch(LearningResource learningResource, DateFilter dateFilter) {
        log.info("==搜索查询学习资源数量 == resource:{},date:{}", learningResource, dateFilter);
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
        return learningResourceMapper.countBySearch(params);
    }

    @Override
    public List<LearningResource> listBySearchAndOrg(LearningResource learningResource,
            DateFilter dateFilter, Integer rootOrgId, Integer page, Integer limit) {
        log.info("==根据搜索及组织查询学习资源 == resource:{},date:{},rootOrgId:{},page:{},limit:{}",
                learningResource, dateFilter, rootOrgId, page, limit);
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
        params.put(Constants.Mapper.ROOT_ORG_ID, rootOrgId);
        return learningResourceMapper.listBySearchAndOrg(params);
    }

    @Override
    public Long countBySearchAndOrg(LearningResource learningResource, DateFilter dateFilter,
            Integer rootOrgId) {
        log.info("==根据搜索及组织查询学习资源数量 == resource:{},date:{},rootOrgId:{}", learningResource,
                dateFilter, rootOrgId);
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
        params.put(Constants.Mapper.ROOT_ORG_ID, rootOrgId);
        return learningResourceMapper.countBySearchAndOrg(params);
    }

    @Override
    public List<LearningResource> listBySearchAndAuth(LearningResource learningResource,
            Short userLevel, DateFilter dateFilter, Integer page, Integer limit) {
        log.info("==根据搜索及权限获取学习资源== resource:{},date:{},userLevel:{},page:{},limit:{}",
                learningResource, dateFilter, userLevel, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResource)));
        params.put(Constants.Mapper.START_TIME, dateFilter.getStartTime());
        params.put(Constants.Mapper.END_TIME, dateFilter.getEndTime());
        params.put("userLevel", userLevel);
        params.put("allAuth", LearningResourceAuthType.ALL.getType());
        params.put("excludeSub", LearningResourceAuthType.EXCLUDE_SUB.getType());
        return learningResourceMapper.listBySearchAndAuth(params);
    }

    @Override
    public Long countBySearchAndAuth(LearningResource learningResource, Short userLevel,
            DateFilter dateFilter) {
        log.info("==根据搜索及权限获取学习资源数量== resource:{},date:{},userLevel:{}", learningResource,
                dateFilter, userLevel);
        Map<String, Object> params = new HashMap<>();
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResource)));
        params.put(Constants.Mapper.START_TIME, dateFilter.getStartTime());
        params.put(Constants.Mapper.END_TIME, dateFilter.getEndTime());
        params.put("userLevel", userLevel);
        params.put("allAuth", LearningResourceAuthType.ALL.getType());
        params.put("excludeSub", LearningResourceAuthType.EXCLUDE_SUB.getType());
        return learningResourceMapper.countBySearchAndAuth(params);
    }
}
