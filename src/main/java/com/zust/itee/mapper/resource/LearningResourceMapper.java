package com.zust.itee.mapper.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.resource.LearningResource;

/**
 * 学习资源 mapper
 *
 * @author pojun
 */
@Repository
@Mapper
public interface LearningResourceMapper {

    /**
     * 插入
     */
    Integer save(LearningResource learningResource);

    /**
     * 根据id获取
     */
    LearningResource getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<LearningResource> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(LearningResource learningResource);

    /**
     * 更新
     */
    Integer update(LearningResource learningResource);

    /**
     * 根据搜索条件获取
     */
    List<LearningResource> listBySearch(Map params);

    /**
     * 根据搜索条件获取总数
     */
    Long countBySearch(Map params);

    /**
     * 根据搜索条件及组织获取
     */
    List<LearningResource> listBySearchAndOrg(Map params);

    /**
     * 根据搜索条件及组织获取总数
     */
    Long countBySearchAndOrg(Map params);

    /**
     * 根据搜索条件、权限获取
     */
    List<LearningResource> listBySearchAndAuth(Map params);

    /**
     * 根据搜索条件、权限总数
     */
    Long countBySearchAndAuth(Map params);
}
