package com.zust.itee.service.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.entity.user.UserResource;
import com.zust.itee.mapper.user.UserResourceMapper;
import com.zust.itee.service.user.UserResourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户资源 service 实现类
 *
 * @author pojun
 */
@Service
@Slf4j
public class UserResourceServiceImpl implements UserResourceService {

    @Resource
    private UserResourceMapper userResourceMapper;

    @Override
    public Integer upsert(UserResource userResource) {
        log.info("==插入或更新用户资源查看记录== description:{}", userResource);
        if (userResource.getId() == null || getById(userResource.getId()) == null) {
            log.info("==插入或更新用户资源查看记录，插入用户资源查看记录== description:{}", userResource);
            return userResourceMapper.save(userResource);
        } else {
            log.info("==插入或更新用户资源查看记录，更新用户资源查看记录== description:{}", userResource);
            return userResourceMapper.update(userResource);
        }
    }

    @Override
    public UserResource getById(Integer id) {
        log.info("==根据id获取用户资源查看记录== id:{}", id);
        return userResourceMapper.getById(id);
    }

    @Override
    public List<UserResource> listByFilter(UserResource userResource, Integer page, Integer limit) {
        log.info("==精准查询用户资源查看记录== description:{},page:{},limit:{}", userResource, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(userResource)));
        return userResourceMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(UserResource userResource) {
        return userResourceMapper.countByFilter(userResource);
    }
}
