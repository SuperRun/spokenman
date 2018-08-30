package com.zust.itee.service.resource;

import java.util.List;

import com.zust.itee.entity.resource.QuestionItems;

/**
 * 题目选项 service
 *
 * @author pojun
 */
public interface QuestionItmesService {

    /**
     * 创建或更新
     */
    Integer upsert(QuestionItems questionItems);

    /**
     * 根据id获取
     */
    QuestionItems getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<QuestionItems> listByFilter(QuestionItems questionItems, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(QuestionItems questionItems);
}
