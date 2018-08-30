package com.zust.itee.otadatadictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.zust.itee.otadatadictionary.client.domain.DataDictionary;
import com.zust.itee.otadatadictionary.client.enums.RootDataDictionary;
import com.zust.itee.otadatadictionary.client.service.DataDictionaryService;
import com.zust.itee.otadatadictionary.mapper.DataDictionaryMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据字典 service 实现类
 *
 * @author pojun
 */
@Component
@Service
@Slf4j
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Resource
    private DataDictionaryMapper datadictionaryMapper;

    @Override
    public Integer upsert(DataDictionary datadictionary) {
        log.info("==插入或更新数据字典== description:{}", datadictionary);
        if (datadictionary.getId() == null || getById(datadictionary.getId()) == null) {
            log.info("==插入或更新数据字典，插入数据字典== description:{}", datadictionary);
            return datadictionaryMapper.save(datadictionary);
        } else {
            log.info("==插入或更新数据字典，更新数据字典== description:{}", datadictionary);
            return datadictionaryMapper.update(datadictionary);
        }
    }

    @Override
    public DataDictionary getById(Long id) {
        log.info("==根据id获取数据字典== id:{}", id);
        return datadictionaryMapper.getById(id);
    }

    @Override
    public List<DataDictionary> listByFilter(DataDictionary datadictionary, Integer page,
            Integer limit) {
        log.info("==精准查询数据字典== description:{},page:{},limit:{}", datadictionary, page, limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(datadictionary)));
        return datadictionaryMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(DataDictionary datadictionary) {
        return datadictionaryMapper.countByFilter(datadictionary);
    }

    @Override
    public List<DataDictionary> listSubData(Long uplink, Integer page, Integer limit) {
        return listByFilter(DataDictionary.builder().uplink(uplink).build(), page, limit);
    }

    @Override
    public List<DataDictionary> listSubData(RootDataDictionary rootDataDictionary) {
        DataDictionary query = DataDictionary.builder()
                .level(rootDataDictionary.getLevel())
                .valueStr(rootDataDictionary.getValueStr())
                .build();
        List<DataDictionary> res = listByFilter(query, 1, 1);
        if (res == null || res.isEmpty()) {
            log.error("==根据根节点查询所有子节点，根节点不存在== root:{}", rootDataDictionary);
            throw new RuntimeException("根节点不存在");
        }
        DataDictionary root = res.get(0);
        return listSubData(root.getId(), 1, Integer.MAX_VALUE);
    }
}
