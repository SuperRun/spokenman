package com.zust.itee.manager.training;

import com.zust.itee.dto.TrainingDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.request.training.TrainingSaveDto;
import com.zust.itee.dto.request.training.TrainingSearchDto;
import com.zust.itee.dto.request.training.TrainingUpdateDto;
import com.zust.itee.entity.training.Training;
import com.zust.itee.exception.BusinessException;

/**
 * 培训 manager
 *
 * @author pojun
 */
public interface TrainingManager {

    /**
     * 创建培训
     */
    void saveTraining(Integer userId, Integer orgId, TrainingSaveDto trainingSaveDto)
            throws BusinessException;

    /**
     * 搜索培训
     */
    PageBaseDto<TrainingDto> listBySearch(Integer userId, Short userType, Integer orgId,
            TrainingSearchDto searchDto, PageBaseDto pageBaseDto) throws BusinessException;

    /**
     * 获取培训详情
     */
    TrainingDto getById(Integer id) throws BusinessException;

    /**
     * 更新培训
     *
     * @throws BusinessException
     */
    void updateTraining(Integer id, TrainingUpdateDto updateDto) throws BusinessException;

    /**
     * 删除培训
     *
     * @throws BusinessException
     */
    void deleteTraining(Integer id) throws BusinessException;

    /**
     * 校验培训是否存在
     */
    Training validateNotNull(Integer id) throws BusinessException;

    TrainingDto convertToDto(Training training) throws BusinessException;
}
