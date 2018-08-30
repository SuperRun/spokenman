package com.zust.itee.mapper.training;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.training.Training;

/**
 * 培训 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface TrainingMapper {

    /**
     * 插入
     */
    Integer save(Training training);

    /**
     * 根据id获取
     */
    Training getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<Training> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(Training training);

    /**
     * 更新
     */
    Integer update(Training training);

    /**
     * 根据搜索条件获取
     */
    List<Training> listBySearch(Map params);

    /**
     * 根基搜索条件 count
     */
    Long countBySearch(Map params);

    /**
     * 根据搜索条件获取已发布培训(未开始报名)
     */
    List<Training> listReleasedBySearch(Map params);

    /**
     * 根据搜索条件 count 已发布培训(未开始报名)
     */
    Long countReleasedBySearch(Map params);

    /**
     * 根据搜索条件获取报名中培训
     */
    List<Training> listSignUpBySearch(Map params);

    /**
     * 根据搜索条件 count 报名中培训
     */
    Long countSignUpBySearch(Map params);

    /**
     * 根据搜索条件获取待培训的培训
     */
    List<Training> listWaitingBySearch(Map params);

    /**
     * 根据搜索条件 count 待培训的培训
     */
    Long countWaitingBySearch(Map params);

    /**
     * 根据搜索条件获取培训中的培训
     */
    List<Training> listTrainingBySearch(Map params);

    /**
     * 根据搜索条件 count 培训中的培训
     */
    Long countTrainingBySearch(Map params);

    /**
     * 根据搜索条件获取确认中的培训
     */
    List<Training> listConfirmingBySearch(Map params);

    /**
     * 根据搜索条件 count 确认中的培训
     */
    Long countConfirmingBySearch(Map params);

    /**
     * 根据搜索条件获取已完成培训
     */
    List<Training> listCompletedBySearch(Map params);

    /**
     * 根据搜索条件 count 已完成培训
     */
    Long countCompletedBySearch(Map params);
}
