package com.zust.itee.manager.training;

import com.zust.itee.dto.TrainingUserDto;
import com.zust.itee.dto.UserDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.training.TrainingUserSaveDto;
import com.zust.itee.dto.request.training.TrainingUserSearchDto;
import com.zust.itee.dto.request.training.TrainingUserUpdateDto;
import com.zust.itee.dto.request.user.UserSearchDto;
import com.zust.itee.exception.BusinessException;

/**
 * 培训用户 manager
 *
 * @author pojun
 */
public interface TrainingUserManager {

    /**
     * 根据 id 获取用户培训详情
     */
    TrainingUserDto getById(Integer id) throws BusinessException;

    /**
     * 搜索为参加培训的用户
     */
    PageBaseDto<UserDto> listUnselectedUserBySearch(SessionInfo sessionInfo, Integer trainingId,
            UserSearchDto searchDto, PageBaseDto pageBaseDto) throws BusinessException;

    /**
     * 根据培训获取用户培训情况
     */
    PageBaseDto<TrainingUserDto> listByTraining(Integer trainingId, TrainingUserSearchDto searchDto,
            PageBaseDto pageBaseDto) throws BusinessException;

    /**
     * 根据用户获取用户培训情况
     */
    PageBaseDto<TrainingUserDto> listByUser(Integer userId, TrainingUserSearchDto searchDto,
            PageBaseDto pageBaseDto) throws BusinessException;

    /**
     * 添加用户培训记录
     */
    void saveTrainingUser(TrainingUserSaveDto saveDto) throws BusinessException;

    /**
     * 更新用户培训记录状态
     */
    void updateTrainingUserStatus(Integer id, Short status) throws BusinessException;

    /**
     * 更新用户培训记录
     */
    void updateTrainingUser(Integer id, TrainingUserUpdateDto updateDto) throws BusinessException;

    /**
     * 删除用户培训记录
     */
    void deleteTrainingUser(Integer id) throws BusinessException;

    /**
     * 判断用户是否参加某用户
     */
    boolean judgeSignUp(Integer userId, Integer trainingId) throws BusinessException;

    /**
     * 获取用户培训情况
     *
     * @throws BusinessException
     */
    TrainingUserDto getUserTraining(Integer userId, Integer trainingId) throws BusinessException;
}
