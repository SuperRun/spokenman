package com.zust.itee.manager.resource;

import com.zust.itee.dto.UserResourceQuestionDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.request.resource.UserResourceQuestionSaveDto;
import com.zust.itee.exception.BusinessException;

/**
 * 用户培训资源答题记录 manager
 *
 * @author pojun
 */
public interface UserResourceQuestionManager {

    /**
     * 增加用户资源答题记录
     */
    void saveUserResourceQuestion(UserResourceQuestionSaveDto saveDto) throws BusinessException;

    /**
     * 获取用户答题记录
     */
    PageBaseDto<UserResourceQuestionDto> listByUser(Integer userId, PageBaseDto pageBaseDto)
            throws BusinessException;

    /**
     * 根据资源问题获取用户答题记录
     */
    PageBaseDto<UserResourceQuestionDto> listByResourceQuestion(Integer resourceQuestionId,
            PageBaseDto pageBaseDto) throws BusinessException;
}
