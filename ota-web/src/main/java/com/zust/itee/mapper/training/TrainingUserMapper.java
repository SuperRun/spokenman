package com.zust.itee.mapper.training;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.training.TrainingUser;
import com.zust.itee.entity.user.User;

/**
 * 培训用户 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface TrainingUserMapper {

    /**
     * 插入
     */
    Integer save(TrainingUser trainingUser);

    /**
     * 根据id获取
     */
    TrainingUser getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<TrainingUser> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(TrainingUser trainingUser);

    /**
     * 更新
     */
    Integer update(TrainingUser trainingUser);

    /**
     * 根据查询条件获取（精确查询），排除已删除
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<TrainingUser> listByFilterWithoutDeleted(Map params);

    /**
     * 根据精确查询 count，排除已删除
     */
    Long countByFilterWithoutDeleted(Map params);

    /**
     * 根据搜索查询获取（模糊查询），排除已删除
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<TrainingUser> listBySearchWithoutDeleted(Map params);

    /**
     * 根据搜索查询 count，排除已删除
     */
    Long countBySearchWithoutDeleted(Map params);

    /**
     * 搜索筛选未参加培训的用户
     */
    List<User> listUnselectedUserBySearch(Map params);

    /**
     * count 未参加培训的用户
     */
    Long countUnselectedUserBySearch(Map params);
}
