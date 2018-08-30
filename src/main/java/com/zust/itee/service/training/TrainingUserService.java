package com.zust.itee.service.training;

import java.util.List;

import com.zust.itee.entity.training.TrainingUser;
import com.zust.itee.entity.user.User;

/**
 * 培训用户 service
 *
 * @author pojun
 */
public interface TrainingUserService {

    /**
     * 创建或更新
     */
    Integer upsert(TrainingUser trainingUser);

    /**
     * 根据id获取
     */
    TrainingUser getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<TrainingUser> listByFilter(TrainingUser trainingUser, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(TrainingUser trainingUser);

    /**
     * 根据查询条件分页获取(精确查询)，排除已删除
     */
    List<TrainingUser> listByFilterWithoutDeleted(TrainingUser trainingUser, Integer page,
            Integer limit);

    /**
     * 根据精确查询 count，排除已删除
     */
    Long countByFilterWithoutDeleted(TrainingUser trainingUser);

    /**
     * 根据查询条件分页获取(精确查询)，排除已删除
     */
    List<TrainingUser> listBySearchWithoutDeleted(TrainingUser trainingUser, Integer page,
            Integer limit);

    /**
     * 根据精确查询 count，排除已删除
     */
    Long countBySearchWithoutDeleted(TrainingUser trainingUser);

    /**
     * 根据搜索筛选未参加培训的用户
     */
    List<User> listUnselectedUserBySearch(User user, Integer trainingId, Integer page,
            Integer limit);

    /**
     * 根据搜索 count 未参加培训的用户
     */
    Long countUnselectedUserBySearch(User user, Integer trainingId);
}

