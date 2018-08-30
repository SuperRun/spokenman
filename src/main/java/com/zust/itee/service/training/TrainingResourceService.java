package com.zust.itee.service.training;

import java.util.List;

import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.training.TrainingResource;

/**
 * 培训资源 service
 *
 * @author pojun
 */
public interface TrainingResourceService {

    /**
     * 创建或更新
     */
    Integer upsert(TrainingResource trainingResource);

    /**
     * 根据id获取
     */
    TrainingResource getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<TrainingResource> listByFilter(TrainingResource trainingResource, Integer page,
            Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(TrainingResource trainingResource);

    /**
     * 根据查询条件分页获取(精确查询)，排除已删除
     */
    List<TrainingResource> listByFilterWithoutDeleted(TrainingResource trainingResource,
            Integer page, Integer limit);

    /**
     * 根据精确查询 count，排除已删除
     */
    Long countByFilterWithoutDeleted(TrainingResource trainingResource);

    List<TrainingResource> listBySearchWithoutDeleted(TrainingResource trainingResource,
            Integer page, Integer limit);

    Long countBySearchWithoutDeleted(TrainingResource trainingResource);

    List<LearningResource> listUnselectedResource(LearningResource learningResource,
            DateFilter dateFilter, Integer trainingId, Integer rootOrgId, Integer page,
            Integer limit);

    Long countUnselectedResource(LearningResource learningResource,
            DateFilter dateFilter, Integer trainingId, Integer rootOrgId);
}
