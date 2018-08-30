package com.zust.itee.service.impl.training;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.constans.Constants;
import com.zust.itee.entity.training.TrainingUser;
import com.zust.itee.entity.user.User;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.mapper.training.TrainingUserMapper;
import com.zust.itee.service.training.TrainingUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 培训用户 service 实现类
 *
 * @author pojun
 */
@Service
@Slf4j
public class TrainingUserServiceImpl implements TrainingUserService {

    @Resource
    private TrainingUserMapper trainingUserMapper;

    @Override
    public Integer upsert(TrainingUser trainingUser) {
        log.info("==插入或更新培训用户== description:{}", trainingUser);
        if (trainingUser.getId() == null || getById(trainingUser.getId()) == null) {
            log.info("==插入或更新培训用户，插入培训用户== description:{}", trainingUser);
            return trainingUserMapper.save(trainingUser);
        } else {
            log.info("==插入或更新培训用户，更新培训用户== description:{}", trainingUser);
            return trainingUserMapper.update(trainingUser);
        }
    }

    @Override
    public TrainingUser getById(Integer id) {
        log.info("==根据id获取培训用户== id:{}", id);
        return trainingUserMapper.getById(id);
    }

    @Override
    public List<TrainingUser> listByFilter(TrainingUser trainingUser, Integer page, Integer limit) {
        log.info("==精准查询培训用户== description:{},page:{},limit:{}", trainingUser, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingUser)));
        return trainingUserMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(TrainingUser trainingUser) {
        return trainingUserMapper.countByFilter(trainingUser);
    }

    @Override
    public List<TrainingUser> listByFilterWithoutDeleted(TrainingUser trainingUser, Integer page,
            Integer limit) {
        log.info("==精准查询培训用户，排除已删除== description:{},page:{},limit:{}", trainingUser, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingUser)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingUserMapper.listByFilterWithoutDeleted(params);
    }

    @Override
    public Long countByFilterWithoutDeleted(TrainingUser trainingUser) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingUser)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingUserMapper.countByFilterWithoutDeleted(params);
    }

    @Override
    public List<TrainingUser> listBySearchWithoutDeleted(TrainingUser trainingUser, Integer page,
            Integer limit) {
        log.info("==查询培训用户，排除已删除== description:{},page:{},limit:{}", trainingUser, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        if (StringUtils.isNotBlank(trainingUser.getUserName())) {
            trainingUser.setUserName("%" + trainingUser.getUserName() + "%");
        } else {
            trainingUser.setUserName(null);
        }
        if (StringUtils.isNotBlank(trainingUser.getTrainingName())) {
            trainingUser.setTrainingName("%" + trainingUser.getTrainingName() + "%");
        } else {
            trainingUser.setTrainingName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingUser)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingUserMapper.listBySearchWithoutDeleted(params);
    }

    @Override
    public Long countBySearchWithoutDeleted(TrainingUser trainingUser) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(trainingUser.getUserName())) {
            trainingUser.setUserName("%" + trainingUser.getUserName() + "%");
        } else {
            trainingUser.setUserName(null);
        }
        if (StringUtils.isNotBlank(trainingUser.getTrainingName())) {
            trainingUser.setTrainingName("%" + trainingUser.getTrainingName() + "%");
        } else {
            trainingUser.setTrainingName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(trainingUser)));
        params.put(Constants.Mapper.DELETED_STATUS, NormalStatusType.DELETE.getStatus());
        return trainingUserMapper.countBySearchWithoutDeleted(params);
    }

    @Override
    public List<User> listUnselectedUserBySearch(User user, Integer trainingId, Integer page,
            Integer limit) {
        log.info("==根据搜索筛选未参加培训的用户== user:{},trainingId:{},page:{},limit:{}", user, trainingId,
                page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        if (StringUtils.isNotBlank(user.getName())) {
            user.setName("%" + user.getName() + "%");
        } else {
            user.setName(null);
        }
        if (StringUtils.isNotBlank(user.getOrgName())) {
            user.setOrgName("%" + user.getOrgName() + "%");
        } else {
            user.setOrgName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(user)));
        params.put(Constants.Mapper.TRAINING_ID, trainingId);
        return trainingUserMapper.listUnselectedUserBySearch(params);
    }

    @Override
    public Long countUnselectedUserBySearch(User user, Integer trainingId) {
        if (StringUtils.isNotBlank(user.getName())) {
            user.setName("%" + user.getName() + "%");
        } else {
            user.setName(null);
        }
        if (StringUtils.isNotBlank(user.getOrgName())) {
            user.setOrgName("%" + user.getOrgName() + "%");
        } else {
            user.setOrgName(null);
        }
        Map<String, Object> params = new HashMap<>();
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(user)));
        params.put(Constants.Mapper.TRAINING_ID, trainingId);
        return trainingUserMapper.countUnselectedUserBySearch(params);
    }
}
