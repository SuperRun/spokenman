package com.zust.itee.service.org;

import java.util.List;

import com.zust.itee.entity.org.Organization;

/**
 * 组织service接口
 *
 * @author pojun
 */
public interface OrganizationService {

    /**
     * 创建或更新组织
     */
    Integer upsert(Organization organization);

    /**
     * 根据id获取组织
     */
    Organization getById(Integer id);

    /**
     * 根据查询条件分页获取组织(精确查询)
     *
     * @throws RuntimeException
     */
    List<Organization> listByFilter(Organization organization, Integer page, Integer limit)
            throws RuntimeException;

    /**
     * 根据精确查询 count
     */
    Long countByFilter(Organization organization);

    /**
     * 根据id获取父级组织
     *
     * @throws RuntimeException
     */
    Organization getParentOrg(Integer id) throws RuntimeException;

    /**
     * 获取当前组织的下级组织
     *
     * @throws RuntimeException
     */
    List<Organization> getSubOrgs(Integer id, Integer page, Integer limit) throws RuntimeException;
}
