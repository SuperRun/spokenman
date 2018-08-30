package com.zust.itee.manager.impl.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zust.itee.dto.QuestionItemDto;
import com.zust.itee.entity.resource.QuestionItems;
import com.zust.itee.manager.resource.QuestionItemsManager;
import com.zust.itee.service.resource.QuestionItmesService;
import com.zust.itee.util.FunctionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 题目选项 manager 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class QuestionItmesManagerImpl implements QuestionItemsManager {

    @Resource
    private QuestionItmesService questionItmesService;

    @Override
    public List<QuestionItemDto> getByResourceQuestionId(Integer questionId) {
        log.info("==根据资源题目获取选项== questionId:{}", questionId);
        List<QuestionItems> questionItems = questionItmesService.listByFilter(
                QuestionItems.builder()
                        .resourceQuestionId(questionId)
                        .build(), 1, Integer.MAX_VALUE);
        log.info("==根据资源题目获取选项，查库结果== items:{},questionId:{}", questionItems, questionId);
        return convertToDtoList(questionItems);
    }

    @Override
    public void saveQuestionItem(QuestionItems questionItems) {
        questionItmesService.upsert(questionItems);
    }

    private QuestionItemDto convertToDto(QuestionItems questionItems) {
        QuestionItemDto questionItemDto = new QuestionItemDto();
        BeanUtils.copyProperties(questionItems, questionItemDto);
        // 获取序列字符
        Short sequence = questionItemDto.getSequence();
        if (sequence != null) {
            questionItemDto.setSequenceStr(FunctionUtil.numToChar(sequence));
        }
        return questionItemDto;
    }

    private List<QuestionItemDto> convertToDtoList(List<QuestionItems> questionItems) {
        return questionItems.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
