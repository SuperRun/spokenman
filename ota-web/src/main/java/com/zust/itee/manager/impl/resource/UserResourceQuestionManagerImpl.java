package com.zust.itee.manager.impl.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zust.itee.dto.UserResourceQuestionDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.request.resource.UserResourceQuestionSaveDto;
import com.zust.itee.entity.resource.UserResourceQuestion;
import com.zust.itee.entity.user.User;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.resource.LearningResourceQuestionManager;
import com.zust.itee.manager.resource.UserResourceQuestionManager;
import com.zust.itee.manager.user.UserManager;
import com.zust.itee.service.resource.UserResourceQuestionService;
import com.zust.itee.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户培训资源答题记录 manager
 *
 * @author pojun
 */
@Service
@Slf4j
public class UserResourceQuestionManagerImpl implements UserResourceQuestionManager {

    @Resource
    private UserResourceQuestionService userResourceQuestionService;

    @Resource
    private UserManager userManager;

    @Resource
    private LearningResourceQuestionManager learningResourceQuestionManager;

    @Resource
    private UserService userService;

    @Override
    public void saveUserResourceQuestion(UserResourceQuestionSaveDto saveDto)
            throws BusinessException {
        log.info("==增加用户学习资源答题记录== save:{}", saveDto);
        userManager.validateNotNull(saveDto.getUserId());
        learningResourceQuestionManager.validateNoNull(saveDto.getResourceQuestionId());
        UserResourceQuestion userResourceQuestion = new UserResourceQuestion();
        BeanUtils.copyProperties(saveDto, userResourceQuestion);
        userResourceQuestionService.upsert(userResourceQuestion);
    }

    @Override
    public PageBaseDto<UserResourceQuestionDto> listByUser(Integer userId, PageBaseDto pageBaseDto)
            throws BusinessException {
        log.info("==获取用户学习资源答题记录== userId:{},page:{}", userId, pageBaseDto);
        return listByFilter(UserResourceQuestion.builder()
                .userId(userId)
                .build(), pageBaseDto);
    }

    @Override
    public PageBaseDto<UserResourceQuestionDto> listByResourceQuestion(Integer resourceQuestionId,
            PageBaseDto pageBaseDto) throws BusinessException {
        log.info("==根据学习资源问题获取用户学习资源答题记录== resourceQuestionId:{},page:{}", resourceQuestionId,
                pageBaseDto);
        return listByFilter(UserResourceQuestion.builder()
                .resourceQuestionId(resourceQuestionId)
                .build(), pageBaseDto);
    }

    @SuppressWarnings("unchecked")
    private PageBaseDto<UserResourceQuestionDto> listByFilter(UserResourceQuestion filter,
            PageBaseDto pageBaseDto) {
        log.info("==根据筛选条件获取学习资源答题记录== filter:{},page:{}", filter, pageBaseDto);
        List<UserResourceQuestion> userResourceQuestions = userResourceQuestionService.listByFilter(
                filter, pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==根据筛选条件获取学习资源答题记录，查库结果== res:{}", userResourceQuestions);
        Long sum = userResourceQuestionService.countByFilter(filter);
        log.info("==根据筛选条件获取学习资源答题记录，查库总数== sum:{}", sum);

        List<UserResourceQuestionDto> userResourceQuestionDtos = convertToDtoList(
                userResourceQuestions);
        pageBaseDto.setData(userResourceQuestionDtos);
        pageBaseDto.setSumAndPageNum(sum);
        log.info("==根据筛选条件获取学习资源答题记录，转换结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    private UserResourceQuestionDto convertToDto(UserResourceQuestion userResourceQuestion) {
        UserResourceQuestionDto userResourceQuestionDto = new UserResourceQuestionDto();
        BeanUtils.copyProperties(userResourceQuestion, userResourceQuestionDto);
        // 用户名称
        User user = userService.getById(userResourceQuestion.getUserId());
        if (user != null) {
            userResourceQuestionDto.setUserName(user.getName());
        }
        return userResourceQuestionDto;
    }

    private List<UserResourceQuestionDto> convertToDtoList(
            List<UserResourceQuestion> userResourceQuestions) {
        return userResourceQuestions.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
