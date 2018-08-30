package com.zust.itee.service.impl.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.entity.resource.UserResourceQuestion;
import com.zust.itee.mapper.resource.UserResourceQuestionMapper;
import com.zust.itee.service.resource.UserResourceQuestionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户培训资源答题记录 service 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class UserResourceQuestionServiceImpl implements UserResourceQuestionService {

    @Resource
    private UserResourceQuestionMapper userResourceQuestionMapper;

    @Override
    public Integer upsert(UserResourceQuestion userResourceQuestion) {
        log.info("==插入或更新用户培训资源答题记录== description:{}", userResourceQuestion);
        if (userResourceQuestion.getId() == null || getById(userResourceQuestion.getId()) == null) {
            log.info("==插入或更新用户培训资源答题记录，插入用户培训资源答题记录== description:{}", userResourceQuestion);
            return userResourceQuestionMapper.save(userResourceQuestion);
        } else {
            log.info("==插入或更新用户培训资源答题记录，更新用户培训资源答题记录== description:{}", userResourceQuestion);
            return userResourceQuestionMapper.update(userResourceQuestion);
        }
    }

    @Override
    public UserResourceQuestion getById(Integer id) {
        log.info("==根据id获取用户培训资源答题记录== id:{}", id);
        return userResourceQuestionMapper.getById(id);
    }

    @Override
    public List<UserResourceQuestion> listByFilter(UserResourceQuestion userResourceQuestion,
            Integer page, Integer limit) {
        log.info("==精准查询用户培训资源答题记录== description:{},page:{},limit:{}", userResourceQuestion, page,
                limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(userResourceQuestion)));
        return userResourceQuestionMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(UserResourceQuestion userResourceQuestion) {
        return userResourceQuestionMapper.countByFilter(userResourceQuestion);
    }
}
