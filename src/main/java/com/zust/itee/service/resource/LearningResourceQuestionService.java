package com.zust.itee.service.resource;

import java.util.List;

import com.zust.itee.entity.resource.LearningResourceQuestion;

/**
 * 学习资源问题关联 service
 *
 * @author pojun
 */
public interface LearningResourceQuestionService {

    /**
     * 创建或更新
     */
    Integer upsert(LearningResourceQuestion learningResourceQuestion);

    /**
     * 根据id获取
     */
    LearningResourceQuestion getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<LearningResourceQuestion> listByFilter(LearningResourceQuestion learningResourceQuestion,
            Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(LearningResourceQuestion learningResourceQuestion);

    /**
     * 根据查询条件分页获取(精确查询)，排除已删除
     */
    List<LearningResourceQuestion> listByFilterWithoutDeleted(
            LearningResourceQuestion learningResourceQuestion, Integer page, Integer limit);

    /**
     * 根据精确查询 count，排除已删除
     */
    Long countByFilterWithoutDeleted(LearningResourceQuestion learningResourceQuestion);
}
