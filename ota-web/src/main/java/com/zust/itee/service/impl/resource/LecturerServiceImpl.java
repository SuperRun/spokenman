package com.zust.itee.service.impl.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.entity.resource.Lecturer;
import com.zust.itee.mapper.resource.LecturerMapper;
import com.zust.itee.service.resource.LecturerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 讲师 service 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class LecturerServiceImpl implements LecturerService {

    @Resource
    private LecturerMapper lecturerMapper;

    @Override
    public Integer upsert(Lecturer lecturer) {
        log.info("==插入或更新讲师== description:{}", lecturer);
        if (lecturer.getId() == null || getById(lecturer.getId()) == null) {
            log.info("==插入或更新讲师，插入讲师== description:{}", lecturer);
            return lecturerMapper.save(lecturer);
        } else {
            log.info("==插入或更新讲师，更新讲师== description:{}", lecturer);
            return lecturerMapper.update(lecturer);
        }
    }

    @Override
    public Lecturer getById(Integer id) {
        log.info("==根据id获取讲师== id:{}", id);
        return lecturerMapper.getById(id);
    }

    @Override
    public List<Lecturer> listByFilter(Lecturer lecturer, Integer page, Integer limit) {
        log.info("==精准查询讲师== description:{},page:{},limit:{}", lecturer, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(lecturer)));
        return lecturerMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(Lecturer lecturer) {
        return lecturerMapper.countByFilter(lecturer);
    }

    @Override
    public List<Lecturer> listBySearch(Lecturer lecturer, Integer page, Integer limit) {
        log.info("==搜索查询讲师== lecture:{},page:{},limit:{}", lecturer, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        if (StringUtils.isNotBlank(lecturer.getName())) {
            lecturer.setName("%" + lecturer.getName() + "%");
        } else {
            lecturer.setName(null);
        }
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(lecturer)));
        return lecturerMapper.listBySearch(params);
    }

    @Override
    public Long countBySearch(Lecturer lecturer) {
        log.info("==搜索查询讲师数量== lecturer:{}", lecturer);
        if (StringUtils.isNotBlank(lecturer.getName())) {
            lecturer.setName("%" + lecturer.getName() + "%");
        } else {
            lecturer.setName(null);
        }
        return lecturerMapper.countBySearch(lecturer);
    }
}
