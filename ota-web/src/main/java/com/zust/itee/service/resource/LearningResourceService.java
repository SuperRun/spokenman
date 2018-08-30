package com.zust.itee.service.resource;

import java.util.List;

import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.resource.LearningResource;

/**
 * 学习资源 service
 *
 * @author pojun
 */
public interface LearningResourceService {

    /**
     * 创建或更新
     */
    Integer upsert(LearningResource learningResource);

    /**
     * 根据id获取
     */
    LearningResource getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<LearningResource> listByFilter(LearningResource learningResource, Integer page,
            Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(LearningResource learningResource);

    /**
     * 根据搜索条件
     */
    List<LearningResource> listBySearch(LearningResource learningResource,
            DateFilter dateFilter, Integer page, Integer limit);

    /**
     * 根据搜索条件 count
     */
    Long countBySearch(LearningResource learningResource, DateFilter dateFilter);

    /**
     * 根据搜索条件及组织权限
     */
    List<LearningResource> listBySearchAndOrg(LearningResource learningResource,
            DateFilter dateFilter, Integer rootOrgId, Integer page, Integer limit);

    /**
     * 根据搜索条件及组织权限 count
     */
    Long countBySearchAndOrg(LearningResource learningResource, DateFilter dateFilter,
            Integer rootOrgId);

    /**
     * 根据搜索条件、权限获取
     */
    List<LearningResource> listBySearchAndAuth(LearningResource learningResource, Short userLevel,
            DateFilter dateFilter, Integer page, Integer limit);

    /**
     * 根据搜索条件、权限 count
     */
    Long countBySearchAndAuth(LearningResource learningResource, Short userLevel,
            DateFilter dateFilter);
}
