package com.zust.itee.manager.training;

import com.zust.itee.dto.LearningResourceDto;
import com.zust.itee.dto.TrainingResourceDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.training.TrainingResourceSaveDto;
import com.zust.itee.dto.request.training.TrainingResourceSearchDto;
import com.zust.itee.dto.request.training.TrainingResourceUpdateDto;
import com.zust.itee.dto.request.training.TrainingSearchDto;
import com.zust.itee.entity.training.TrainingResource;
import com.zust.itee.exception.BusinessException;

/**
 * 培训资源 manager
 *
 * @author pojun
 */
public interface TrainingResourceManager {

    /**
     * 添加培训资源
     */
    void saveTrainingResource(TrainingResourceSaveDto trainingResourceSaveDto) throws
            BusinessException;

    /**
     * 搜索未选择进培训的资源
     */
    PageBaseDto<LearningResourceDto> listUnselectedResources(SessionInfo sessionInfo,
            Integer trainingId, TrainingSearchDto searchDto, PageBaseDto pageBaseDto);

    /**
     * 获取培训资源
     */
    PageBaseDto<TrainingResourceDto> listTrainingResources(Integer trainingId,
            PageBaseDto pageBaseDto, TrainingResourceSearchDto searchDto) throws BusinessException;

    /**
     * 更新培训资源
     *
     * @throws BusinessException
     */
    void updateTrainingResource(Integer id, TrainingResourceUpdateDto updateDto)
            throws BusinessException;

    /**
     * 删除培训资源
     *
     * @throws BusinessException
     */
    void deleteTrainingResource(Integer id) throws BusinessException;

    /**
     * 删除培训资源
     */
    void deleteTrainingResource(Integer trainingId, Integer resourceId) throws BusinessException;

    TrainingResourceDto convertToDto(TrainingResource trainingResource) throws BusinessException;
}
