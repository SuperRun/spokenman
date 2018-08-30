package com.zust.itee.otauser.impl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.zust.itee.otauser.client.domain.User;
import com.zust.itee.otauser.client.domain.UserApply;
import com.zust.itee.otauser.client.enums.UserApplyStatusType;
import com.zust.itee.otauser.client.enums.UserStatusType;
import com.zust.itee.otauser.client.service.UserApplyService;
import com.zust.itee.otauser.client.service.UserService;
import com.zust.itee.otauser.impl.mapper.UserApplyMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户申请 service 实现
 *
 * @author pojun
 */
@Slf4j
@Component
@Service
public class UserApplyServiceImpl implements UserApplyService {

    @Resource
    private UserApplyMapper userApplyMapper;

    @Resource
    private UserService userService;

    @Override
    public Integer upsert(UserApply userApply) {
        log.info("==插入或更新用户申请== description:{}", userApply);
        if (userApply.getId() == null || getById(userApply.getId()) == null) {
            log.info("==插入或更新用户申请，插入用户申请== description:{}", userApply);
            return userApplyMapper.save(userApply);
        } else {
            log.info("==插入或更新用户申请，更新用户申请== description:{}", userApply);
            return userApplyMapper.update(userApply);
        }
    }

    @Override
    public UserApply getById(Integer id) {
        log.info("==根据id获取用户申请== id:{}", id);
        return userApplyMapper.getById(id);
    }

    @Override
    public List<UserApply> listByFilter(UserApply userApply, Integer page, Integer limit) {
        log.info("==精准查询用户申请== description:{},page:{},limit:{}", userApply, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(userApply)));
        return userApplyMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(UserApply userApply) {
        return userApplyMapper.countByFilter(userApply);
    }

    @Override
    public List<UserApply> listByUserId(Integer userId, Integer page, Integer limit) {
        return listByFilter(UserApply.builder().userId(userId).build(), page, limit);
    }

    @Override
    public List<UserApply> listByOrgId(Integer orgId, Integer page, Integer limit) {
        return listByFilter(UserApply.builder().orgId(orgId).build(), page, limit);
    }

    @Override
    public Integer updateUserApplyStatus(Integer id, UserApplyStatusType userApplyStatusType)
            throws RuntimeException {
        log.info("==更改用户申请状态== id:{},status:{}", id, userApplyStatusType);
        UserApply userApply = getById(id);
        if (userApply == null) {
            log.error("==更改用户申请状态，申请不存在== id:{}", id);
            throw new RuntimeException("原申请不存在");
        }
        // 更新申请状态
        upsert(UserApply.builder().id(id).status(userApplyStatusType.getStatus()).build());
        // 更新申请人状态
        Integer userId = userApply.getUserId();
        if (userService.getById(userId) == null) {
            log.error("==更改用户申请状态后更改申请人状态，申请人不存在== userId:{}", userId);
            throw new RuntimeException("原申请人不存在");
        }
        Short userStatus = getUserStatusByApplyStatus(userApplyStatusType);
        User user = User.builder()
                .id(id)
                .status(userStatus)
                .build();
        return userService.upsert(user);
    }

    /**
     * 根据用户申请状态获取用户状态
     */
    private Short getUserStatusByApplyStatus(UserApplyStatusType userApplyStatusType) {
        if (UserApplyStatusType.APPLYING == userApplyStatusType) {
            return UserStatusType.APPLYING.getStatus();
        } else if (UserApplyStatusType.APPROVE == userApplyStatusType) {
            return UserStatusType.NORMAL.getStatus();
        } else if (UserApplyStatusType.REJECT == userApplyStatusType) {
            return UserStatusType.REJECTED.getStatus();
        }
        return null;
    }
}
