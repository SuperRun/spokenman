package com.zust.itee.service.impl.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.constans.Constants;
import com.zust.itee.entity.resource.LearningResourceQuestion;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.mapper.resource.LearningResourceQuestionMapper;
import com.zust.itee.service.resource.LearningResourceQuestionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 学习资源问题关联 service 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class LearningResourceQuestionServiceImpl implements LearningResourceQuestionService {

    @Resource
    private LearningResourceQuestionMapper learningResourceQuestionMapper;

    @Override
    public Integer upsert(LearningResourceQuestion learningResourceQuestion) {
        log.info("==插入或更新学习资源问题== description:{}", learningResourceQuestion);
        if (learningResourceQuestion.getId() == null
                || getById(learningResourceQuestion.getId()) == null) {
            log.info("==插入或更新学习资源问题，插入学习资源问题== description:{}", learningResourceQuestion);
            return learningResourceQuestionMapper.save(learningResourceQuestion);
        } else {
            log.info("==插入或更新学习资源问题，更新学习资源问题== description:{}", learningResourceQuestion);
            return learningResourceQuestionMapper.update(learningResourceQuestion);
        }
    }

    @Override
    public LearningResourceQuestion getById(Integer id) {
        log.info("==根据id获取学习资源问题== id:{}", id);
        return learningResourceQuestionMapper.getById(id);
    }

    @Override
    public List<LearningResourceQuestion> listByFilter(
            LearningResourceQuestion learningResourceQuestion, Integer page, Integer limit) {
        log.info("==精准查询学习资源问题== description:{},page:{},limit:{}", learningResourceQuestion, page,
                limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResourceQuestion)));
        return learningResourceQuestionMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(LearningResourceQuestion learningResourceQuestion) {
        return learningResourceQuestionMapper.countByFilter(learningResourceQuestion);
    }

    @Override
    public List<LearningResourceQuestion> listByFilterWithoutDeleted(
            LearningResourceQuestion learningResourceQuestion, Integer page, Integer limit) {
        log.info("==精准查询学习资源问题,排除已删除== description:{},page:{},limit:{}", learningResourceQuestion,
                page,
                limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResourceQuestion)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return learningResourceQuestionMapper.listByFilter(params);
    }

    @Override
    public Long countByFilterWithoutDeleted(LearningResourceQuestion learningResourceQuestion) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(learningResourceQuestion)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return learningResourceQuestionMapper.countByFilterWithoutDeleted(params);
    }
}
