package com.zust.itee.manager.resource;

import java.util.List;

import com.zust.itee.dto.LearningResourceDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.resource.LearningResourceSaveDto;
import com.zust.itee.dto.request.resource.LearningResourceSearchDto;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.exception.BusinessException;

/**
 * 学习资源 manager
 *
 * @author pojun
 */
public interface LearningResourceManager {

    /**
     * 搜索学习资源
     *
     * @throws BusinessException
     */
    PageBaseDto<LearningResourceDto> searchLearningResource(Short userType, Integer orgId,
            Short userLevel, LearningResourceSearchDto searchDto, PageBaseDto pageBaseDto)
            throws BusinessException;

    /**
     * 新增学习资源
     *
     * @throws BusinessException
     */
    void saveLearningResource(SessionInfo sessionInfo, LearningResourceSaveDto saveDto)
            throws BusinessException;

    /**
     * 获取学习资源
     */
    LearningResourceDto getById(Integer id);

    /**
     * 更新学习资源
     *
     * @throws BusinessException
     */
    void updateLearningResource(Integer id, LearningResourceSaveDto updateDto)
            throws BusinessException;

    /**
     * 删除学习资源
     *
     * @throws BusinessException
     */
    void deleteLearningResource(Integer id) throws BusinessException;

    /**
     * 更新资源状态
     *
     * @throws BusinessException
     */
    void updateResourceStatus(Integer id, Short status) throws BusinessException;

    LearningResourceDto convertToDto(LearningResource learningResource);

    List<LearningResourceDto> convertToDtoList(List<LearningResource> learningResources);
}
