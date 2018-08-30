package com.zust.itee.mapper.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.resource.LearningResourceQuestion;

/**
 * 学习资源问题关联 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface LearningResourceQuestionMapper {

    /**
     * 插入
     */
    Integer save(LearningResourceQuestion learningResourceQuestion);

    /**
     * 根据id获取
     */
    LearningResourceQuestion getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<LearningResourceQuestion> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(LearningResourceQuestion learningResourceQuestion);

    /**
     * 更新
     */
    Integer update(LearningResourceQuestion learningResourceQuestion);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<LearningResourceQuestion> listByFilterWithoutDeleted(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilterWithoutDeleted(Map params);
}
