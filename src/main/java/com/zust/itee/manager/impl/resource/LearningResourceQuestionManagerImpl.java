package com.zust.itee.manager.impl.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zust.itee.dto.LearningResourceQuestionDto;
import com.zust.itee.dto.QuestionItemDto;
import com.zust.itee.dto.request.resource.LearningResourceQuestionSaveDto;
import com.zust.itee.dto.request.resource.QuestionItemsSaveDto;
import com.zust.itee.dto.request.resource.QuestionItemsUpdateDto;
import com.zust.itee.entity.resource.LearningResourceQuestion;
import com.zust.itee.entity.resource.QuestionItems;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.resource.LearningResourceQuestionType;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.resource.LearningResourceQuestionManager;
import com.zust.itee.manager.resource.QuestionItemsManager;
import com.zust.itee.service.resource.LearningResourceQuestionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 学习资源问题 manager 实现类
 *
 * @author pojun
 */
@Service
@Slf4j
public class LearningResourceQuestionManagerImpl implements LearningResourceQuestionManager {

    @Resource
    private LearningResourceQuestionService learningResourceQuestionService;

    @Resource
    private QuestionItemsManager questionItemsManager;

    @Override
    public LearningResourceQuestionDto getById(Integer id) throws BusinessException {
        log.info("==获取学习资源问题== id:{}", id);
        LearningResourceQuestion question = validateNoNull(id);
        log.info("==获取学习资源问题，转换前结果== res:{}", question);
        LearningResourceQuestionDto questionDto = convertToDto(question);
        log.info("==获取学习资源问题，转换后结果== res:{}", questionDto);
        return questionDto;
    }

    @Override
    @Transactional
    public void saveQuestion(LearningResourceQuestionSaveDto saveDto) throws BusinessException {
        log.info("==插入学习资源问题== save:{}", saveDto);
        LearningResourceQuestion question = new LearningResourceQuestion();
        BeanUtils.copyProperties(saveDto, question);
        question.setStatus(NormalStatusType.NORMAL.getStatus());
        log.info("==插入学习资源问题，插入问题== question:{}", question);
        learningResourceQuestionService.upsert(question);

        // 插入问题选项
        String itemsStr = saveDto.getItems();
        try {
            JSONArray itemsArray = JSONArray.parseArray(itemsStr);
            for (int i = 0; i < itemsArray.size(); i++) {
                QuestionItemsSaveDto item = JSONObject.parseObject(
                        itemsArray.getJSONObject(i).toJSONString(), QuestionItemsSaveDto.class);
                QuestionItems questionItems = new QuestionItems();
                BeanUtils.copyProperties(item, questionItems);
                questionItems.setResourceQuestionId(question.getId());
                log.info("==插入学习资源问题，插入题目选项== item:{},questionId:{}", questionItems,
                        question.getId());
                questionItemsManager.saveQuestionItem(questionItems);
            }
        } catch (Exception e) {
            log.error("==插入学习资源问题，插入问题选项出错== saveDto:{},e:{}", saveDto, e);
            throw new BusinessException("插入题目选项出错");
        }
    }

    @Override
    public void deleteQuestion(Integer id) throws BusinessException {
        log.info("==删除学习资源问题== id:{}", id);
        LearningResourceQuestion question = validateNoNull(id);
        question.setStatus(NormalStatusType.DELETE.getStatus());
        learningResourceQuestionService.upsert(question);
    }

    @Override
    public List<LearningResourceQuestionDto> listResourceQuestions(Integer resourceId)
            throws BusinessException {
        log.info("==获取学习资源中所有问题== resourceId:{}", resourceId);
        List<LearningResourceQuestion> questions = learningResourceQuestionService
                .listByFilterWithoutDeleted(
                        LearningResourceQuestion.builder()
                                .resourceId(resourceId)
                                .status(NormalStatusType.NORMAL.getStatus())
                                .build(), 1, Integer.MAX_VALUE);
        log.info("==获取学习资源中所有问题，获取结果== questions:{}", questions);
        List<LearningResourceQuestionDto> questionDtos = convertToDtoList(questions);
        log.info("==获取学习资源中所有问题，转换后结果== res:{}", questionDtos);
        return questionDtos;
    }

    @Override
    public void updateQuestion(Integer id, LearningResourceQuestionSaveDto updateDto)
            throws BusinessException {
        log.info("==更新学习资源问题== id:{},update:{}", id, updateDto);
        validateNoNull(id);
        LearningResourceQuestion question = convertToEntity(updateDto, id);
        log.info("==更新学习资源，转换后实体== id:{},update:{}", id, updateDto);
        learningResourceQuestionService.upsert(question);

        //更新问题选项
        String itemsStr = updateDto.getItems();
        try {
            JSONArray itemsArray = JSONArray.parseArray(itemsStr);
            for (int i = 0; i < itemsArray.size(); i++) {
                QuestionItemsUpdateDto item = JSONObject.parseObject(
                        itemsArray.getJSONObject(i).toJSONString(), QuestionItemsUpdateDto.class);
                QuestionItems questionItems = new QuestionItems();
                BeanUtils.copyProperties(item, questionItems);
                log.info("==更新学习资源问题，更新题目选项== item:{},questionId:{}", questionItems,
                        question.getId());
                questionItemsManager.saveQuestionItem(questionItems);
            }
        } catch (Exception e) {
            log.error("==更新学习资源问题，更新问题选项出错== saveDto:{},e:{}", updateDto, e);
            throw new BusinessException("更新题目选项出错");
        }
    }

    private LearningResourceQuestion convertToEntity(LearningResourceQuestionSaveDto updateDto,
            Integer id) {
        LearningResourceQuestion question = new LearningResourceQuestion();
        BeanUtils.copyProperties(updateDto, question);
        question.setId(id);
        return question;
    }

    private LearningResourceQuestionDto convertToDto(LearningResourceQuestion resourceQuestion) {
        LearningResourceQuestionDto resourceQuestionDto = new LearningResourceQuestionDto();
        BeanUtils.copyProperties(resourceQuestion, resourceQuestionDto);

        // 状态
        Optional<NormalStatusType> normalStatusType = NormalStatusType.getByStatus(
                resourceQuestion.getStatus());
        normalStatusType.ifPresent(
                status -> resourceQuestionDto.setStatusName(status.getDescription()));
        //题目类型
        Optional<LearningResourceQuestionType> resourceQuestionType =
                LearningResourceQuestionType.getByType(
                        resourceQuestion.getTypeId());
        resourceQuestionType.ifPresent(
                type -> resourceQuestionDto.setTypeName(type.getName()));

        // 题目选项
        List<QuestionItemDto> itmes = questionItemsManager.getByResourceQuestionId(
                resourceQuestion.getId());
        resourceQuestionDto.setItems(itmes);

        return resourceQuestionDto;
    }

    private List<LearningResourceQuestionDto> convertToDtoList(
            List<LearningResourceQuestion> resourceQuestions) {
        return resourceQuestions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public LearningResourceQuestion validateNoNull(Integer id) {
        LearningResourceQuestion question = learningResourceQuestionService.getById(id);
        if (question == null) {
            throw new BusinessException(String.format("学习资源问题不存在,id:%s", id));
        }
        return question;
    }
}
