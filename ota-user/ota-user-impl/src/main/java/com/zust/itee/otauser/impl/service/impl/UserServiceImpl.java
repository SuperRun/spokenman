package com.zust.itee.otauser.impl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.zust.itee.otauser.client.domain.User;
import com.zust.itee.otauser.client.enums.UserStatusType;
import com.zust.itee.otauser.client.service.UserService;
import com.zust.itee.otauser.impl.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户 service 实现类
 *
 * @author pojun
 */
@Slf4j
@Component
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer upsert(User user) {
        log.info("==插入或更新用户== description:{}", user);
        if (user.getId() == null || getById(user.getId()) == null) {
            log.info("==插入或更新用户，插入用户== description:{}", user);
            return userMapper.save(user);
        } else {
            log.info("==插入或更新用户，更新用户== description:{}", user);
            return userMapper.update(user);
        }
    }

    @Override
    public User getById(Integer id) {
        log.info("==根据id获取用户== id:{}", id);
        return userMapper.getById(id);
    }

    @Override
    public List<User> listByFilter(User user, Integer page, Integer limit) {
        log.info("==精准查询用户== description:{},page:{},limit:{}", user, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(user)));
        return userMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(User user) {
        return userMapper.countByFilter(user);
    }

    @Override
    public User login(String userName, String password) {
        log.info("==用户登录== userName:{},password:{}", userName, password);
        User user = User.builder()
                .name(userName)
                .phone(userName)
                .password(password)
                .build();
        return userMapper.login(user);
    }

    @Override
    public List<User> listByOrgId(Integer orgId, Integer page, Integer limit) {
        return listByFilter(User.builder().orgId(orgId).build(), page, limit);
    }

    @Override
    public List<User> listBySearch(User user, Integer page, Integer limit) {
        log.info("==用户搜索查询== user:{},page:{},limit:{}", user, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        if (StringUtils.isNotBlank(user.getName())) {
            user.setName("%" + user.getName() + "%");
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(user)));
        return userMapper.listBySearch(params);
    }

    @Override
    public Long countBySearch(User user) {
        user.setName("%" + user.getName() + "%");
        return userMapper.countBySearch(user);
    }

    @Override
    public Integer updateUserStatus(Integer id, UserStatusType userStatusType) {
        if (getById(id) == null) {
            throw new RuntimeException("原用户不存在");
        }
        User user = User.builder().id(id).status(userStatusType.getStatus()).build();
        return upsert(user);
    }
}
