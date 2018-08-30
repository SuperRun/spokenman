package com.zust.itee.manager.resource;

import java.util.List;

import com.zust.itee.dto.QuestionItemDto;
import com.zust.itee.entity.resource.QuestionItems;

/**
 * 题目选项 manager
 *
 * @author pojun
 */
public interface QuestionItemsManager {

    /**
     * 根据资源题目 id 获取对应选项
     */
    List<QuestionItemDto> getByResourceQuestionId(Integer questionId);

    /**
     * 插入题目选项
     */
    void saveQuestionItem(QuestionItems questionItems);
}
