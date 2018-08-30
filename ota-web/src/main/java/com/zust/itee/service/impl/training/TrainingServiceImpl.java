package com.zust.itee.service.impl.training;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.constans.Constants;
import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.training.Training;
import com.zust.itee.enums.training.TrainingStatus;
import com.zust.itee.mapper.training.TrainingMapper;
import com.zust.itee.service.training.TrainingService;

import lombok.extern.slf4j.Slf4j;

/**
 * 培训 service 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class TrainingServiceImpl implements TrainingService {

    @Resource
    private TrainingMapper trainingMapper;

    @Override
    public Integer upsert(Training training) {
        log.info("==插入或更新培训== description:{}", training);
        if (training.getId() == null || getById(training.getId()) == null) {
            log.info("==插入或更新培训，插入培训== description:{}", training);
            return trainingMapper.save(training);
        } else {
            log.info("==插入或更新培训，更新培训== description:{}", training);
            return trainingMapper.update(training);
        }
    }

    @Override
    public Training getById(Integer id) {
        log.info("==根据id获取培训== id:{}", id);
        return trainingMapper.getById(id);
    }

    @Override
    public List<Training> listByFilter(Training training, Integer page, Integer limit) {
        log.info("==精准查询培训== description:{},page:{},limit:{}", training, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(training)));
        return trainingMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(Training training) {
        return trainingMapper.countByFilter(training);
    }

    @Override
    public List<Training> listBySearch(Training training, DateFilter dateFilter, Integer page,
            Integer limit) {
        log.info("==搜索培训== training:{},date:{},page:{},limit:{}", training, dateFilter, page,
                limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        if (StringUtils.isNotBlank(training.getName())) {
            training.setName("%" + training.getName() + "%");
        } else {
            training.setName(null);
        }
        if (StringUtils.isNotBlank(training.getUserName())) {
            training.setUserName("%" + training.getUserName() + "%");
        } else {
            training.setUserName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(training)));
        params.put(Constants.Mapper.START_TIME, dateFilter.getStartTime());
        params.put(Constants.Mapper.END_TIME, dateFilter.getEndTime());
        params.put("now", new Date());

        List<Training> res = new LinkedList<>();
        if (training.getStatus() == null) {
            log.info("==搜索培训，无状态限制，搜索培训==");
            res = trainingMapper.listBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.RELEASED.getStatus())) {
            log.info("==搜索培训，搜索已发布培训==");
            res = trainingMapper.listReleasedBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.SIGN_UP.getStatus())) {
            log.info("==搜索培训，搜索报名中培训==");
            res = trainingMapper.listSignUpBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.WAITING_TRAINING.getStatus())) {
            log.info("==搜索培训，搜索待培训培训==");
            res = trainingMapper.listWaitingBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.TRAINING.getStatus())) {
            log.info("==搜索培训，搜索培训中培训==");
            res = trainingMapper.listTrainingBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.CONFIRMING.getStatus())) {
            log.info("==搜索培训，搜索确认中培训==");
            res = trainingMapper.listConfirmingBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.COMPLETED.getStatus())) {
            log.info("==搜索培训，搜索已完成培训==");
            res = trainingMapper.listCompletedBySearch(params);
        } else {
            log.info("==搜索培训，未知状态==");
            res = trainingMapper.listBySearch(params);
        }

        // 更新培训状态
        updateTrainingStatus(res);

        return res;
    }

    @Override
    public Long countBySearch(Training training, DateFilter dateFilter) {
        log.info("==搜索培训 count== training:{},date:{}", training, dateFilter);
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(training.getName())) {
            training.setName("%" + training.getName() + "%");
        } else {
            training.setName(null);
        }
        if (StringUtils.isNotBlank(training.getUserName())) {
            training.setUserName("%" + training.getUserName() + "%");
        } else {
            training.setUserName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(training)));
        params.put(Constants.Mapper.START_TIME, dateFilter.getStartTime());
        params.put(Constants.Mapper.END_TIME, dateFilter.getEndTime());
        params.put("now", new Date());

        if (training.getStatus() == null) {
            log.info("==搜索培训 count ，无状态限制，搜索培训==");
            return trainingMapper.countBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.RELEASED.getStatus())) {
            log.info("==搜索培训 count ，搜索已发布培训==");
            return trainingMapper.countReleasedBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.SIGN_UP.getStatus())) {
            log.info("==搜索培训 count ，搜索报名中培训==");
            return trainingMapper.countSignUpBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.WAITING_TRAINING.getStatus())) {
            log.info("==搜索培训 count ，搜索待培训培训==");
            return trainingMapper.countWaitingBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.TRAINING.getStatus())) {
            log.info("==搜索培训 count ，搜索培训中培训==");
            return trainingMapper.countTrainingBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.CONFIRMING.getStatus())) {
            log.info("==搜索培训 count ，搜索确认中培训==");
            return trainingMapper.countConfirmingBySearch(params);
        } else if (training.getStatus().equals(TrainingStatus.COMPLETED.getStatus())) {
            log.info("==搜索培训 count ，搜索已完成培训==");
            return trainingMapper.countCompletedBySearch(params);
        } else {
            log.info("==搜索培训，未知状态==");
            return trainingMapper.countBySearch(params);
        }
    }

    @Override
    public short judgeTrainingStatus(Training training) {
        Date now = new Date();
        if (now.before(training.getSignStartTime())) {
            // 已发布
            return TrainingStatus.RELEASED.getStatus();
        } else if (now.before(training.getSignEndTime())
                && now.after(training.getSignStartTime())) {
            // 报名中
            return TrainingStatus.SIGN_UP.getStatus();
        } else if (now.before(training.getStartTime())) {
            // 待培训
            return TrainingStatus.WAITING_TRAINING.getStatus();
        } else if (now.before(training.getEndTime())
                && now.after(training.getStartTime())) {
            // 培训中
            return TrainingStatus.TRAINING.getStatus();
        } else if (now.after(training.getEndTime())
                && training.getStatus() != TrainingStatus.COMPLETED.getStatus()) {
            // 确认中
            return TrainingStatus.CONFIRMING.getStatus();
        } else {
            // 已完成
            return TrainingStatus.COMPLETED.getStatus();
        }
    }

    @Override
    public void updateTrainingStatus(List<Training> trainings) {
        log.info("==更新培训状态== trainings:{}", trainings);
        trainings.forEach(training -> {
            if (training.getStatus() == null || training.getId() == null) {
                return;
            }
            Short status = judgeTrainingStatus(training);
            if (!training.getStatus().equals(status)) {
                log.info("==更新培训状态，原状态:{}，新状态:{},培训：{}", training.getStatus(), status, training);
                training.setStatus(status);
                upsert(training);
            }
        });
    }
}
