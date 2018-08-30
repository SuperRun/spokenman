package com.zust.itee.mapper.training;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.training.TrainingResource;

/**
 * 培训资源 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface TrainingResourceMapper {

    /**
     * 插入
     */
    Integer save(TrainingResource trainingResource);

    /**
     * 根据id获取
     */
    TrainingResource getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<TrainingResource> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(TrainingResource trainingResource);

    /**
     * 更新
     */
    Integer update(TrainingResource trainingResource);

    /**
     * 根据查询条件获取（精确查询），排除已删除
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<TrainingResource> listByFilterWithoutDeleted(Map params);

    /**
     * 根据精确查询 count，排除已删除
     */
    Long countByFilterWithoutDeleted(Map params);

    List<TrainingResource> listBySearchWithoutDeleted(Map params);

    Long countBySearchWithoutDeleted(Map params);

    /**
     * 搜索未选进培训的资源
     */
    List<LearningResource> listUnselectedResource(Map params);

    /**
     * count 未选进培训的资源
     */
    Long countUnselectedResource(Map params);
}
