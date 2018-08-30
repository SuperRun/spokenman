package com.zust.itee.service.training;

import java.util.List;

import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.training.Training;

/**
 * 培训 service
 *
 * @author pojun
 */
public interface TrainingService {

    /**
     * 创建或更新
     */
    Integer upsert(Training training);

    /**
     * 根据id获取
     */
    Training getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<Training> listByFilter(Training training, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(Training training);

    /**
     * 根据搜索条件获取
     */
    List<Training> listBySearch(Training training, DateFilter dateFilter, Integer page,
            Integer limit);

    /**
     * 根据搜索条件 count
     */
    Long countBySearch(Training training, DateFilter dateFilter);

    /**
     * 判断培训状态
     */
    short judgeTrainingStatus(Training training);

    /**
     * 更新培训状态（根据培训时间参数更新状态位）
     */
    void updateTrainingStatus(List<Training> trainings);
}
