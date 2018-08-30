package com.zust.itee.manager.org;

import java.util.List;

import com.zust.itee.dto.OrganizationDto;
import com.zust.itee.dto.request.userapply.OrgUserApply;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.user.User;
import com.zust.itee.exception.BusinessException;

/**
 * 组织 manager
 *
 * @author pojun
 */
public interface OrganizationManager {

    /**
     * 根据区域、条线筛选组织
     *
     * @throws RuntimeException
     */
    List<OrganizationDto> listByAreaAndTypeAndLevel(Long areaId, Long typeId, Short level)
            throws RuntimeException;

    /**
     * 新增组织
     *
     * @throws BusinessException
     */
    Integer saveOrg(Integer userId, OrganizationDto organizationDto) throws BusinessException;

    /**
     * 新增单位用户时创建组织
     *
     * @return 新增组织 id
     * @throws BusinessException
     */
    Integer saveOrg(User user, OrgUserApply orgUserApply)
            throws BusinessException;

    /**
     * 获取平台组织
     *
     * @throws BusinessException
     */
    Organization getRootOrg() throws BusinessException;
}
