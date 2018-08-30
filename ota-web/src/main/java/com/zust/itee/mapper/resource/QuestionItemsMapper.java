package com.zust.itee.mapper.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.resource.QuestionItems;

/**
 * 题目选项 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface QuestionItemsMapper {

    /**
     * 插入
     */
    Integer save(QuestionItems questionItems);

    /**
     * 根据id获取
     */
    QuestionItems getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<QuestionItems> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(QuestionItems questionItems);

    /**
     * 更新
     */
    Integer update(QuestionItems questionItems);
}
