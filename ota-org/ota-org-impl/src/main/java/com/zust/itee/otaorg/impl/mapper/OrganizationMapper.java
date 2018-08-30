package com.zust.itee.otaorg.impl.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zust.itee.otaorg.client.domain.Organization;

/**
 * 组织mapper
 *
 * @author pojun
 */
@Repository
public interface OrganizationMapper {

    /**
     * 插入组织
     */
    Integer save(Organization organization);

    /**
     * 根据id获取组织
     */
    Organization getById(Integer id);

    /**
     * 根据查询条件获取组织（精确查询）
     *
     * @param params 组织字段、分页字段（offset、limit）
     */
    List<Organization> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(Organization organization);

    /**
     * 更新组织
     */
    Integer update(Organization organization);
}
