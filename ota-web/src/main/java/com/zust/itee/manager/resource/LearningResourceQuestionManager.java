package com.zust.itee.manager.resource;

import java.util.List;

import com.zust.itee.dto.LearningResourceQuestionDto;
import com.zust.itee.dto.request.resource.LearningResourceQuestionSaveDto;
import com.zust.itee.entity.resource.LearningResourceQuestion;
import com.zust.itee.exception.BusinessException;

/**
 * 学习资源问题 manager
 *
 * @author pojun
 */
public interface LearningResourceQuestionManager {

    /**
     * 根据 id 获取学习资源问题详情
     *
     * @throws BusinessException
     */
    LearningResourceQuestionDto getById(Integer id) throws BusinessException;

    /**
     * 插入学习资源问题
     *
     * @throws BusinessException
     */
    void saveQuestion(LearningResourceQuestionSaveDto saveDto) throws BusinessException;

    /**
     * 删除学习资源问题
     *
     * @throws BusinessException
     */
    void deleteQuestion(Integer id) throws BusinessException;

    /**
     * 获取学习资源中所有问题
     *
     * @throws BusinessException
     */
    List<LearningResourceQuestionDto> listResourceQuestions(Integer resourceId)
            throws BusinessException;

    /**
     * 更新学习资源问题
     *
     * @throws BusinessException
     */
    void updateQuestion(Integer id, LearningResourceQuestionSaveDto updateDto)
            throws BusinessException;

    /**
     * 校验学习资源问题是否为空
     */
    LearningResourceQuestion validateNoNull(Integer id) throws BusinessException;
}
