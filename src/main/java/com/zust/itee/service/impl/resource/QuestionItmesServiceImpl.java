package com.zust.itee.service.impl.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.entity.resource.QuestionItems;
import com.zust.itee.mapper.resource.QuestionItemsMapper;
import com.zust.itee.service.resource.QuestionItmesService;

import lombok.extern.slf4j.Slf4j;

/**
 * 题目选项 service 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class QuestionItmesServiceImpl implements QuestionItmesService {

    @Resource
    private QuestionItemsMapper questionItemsMapper;


    @Override
    public Integer upsert(QuestionItems questionItems) {
        log.info("==插入或更新题目选项== description:{}", questionItems);
        if (questionItems.getId() == null || getById(questionItems.getId()) == null) {
            log.info("==插入或更新题目选项，插入题目选项== description:{}", questionItems);
            return questionItemsMapper.save(questionItems);
        } else {
            log.info("==插入或更新题目选项，更新题目选项== description:{}", questionItems);
            return questionItemsMapper.update(questionItems);
        }
    }


    @Override
    public QuestionItems getById(Integer id) {
        log.info("==根据id获取题目选项== id:{}", id);
        return questionItemsMapper.getById(id);
    }


    @Override
    public List<QuestionItems> listByFilter(QuestionItems questionItems, Integer page, Integer limit){
        log.info("==精准查询题目选项== description:{},page:{},limit:{}",questionItems,page,limit);
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(questionItems)));
        return questionItemsMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(QuestionItems questionItems) {
        return questionItemsMapper.countByFilter(questionItems);
    }




}
