package com.zust.itee.service.user;

import java.util.List;

import com.zust.itee.entity.user.UserResource;

/**
 * 用户资源查看记录 service
 *
 * @author pojun
 */
public interface UserResourceService {

    /**
     * 创建或更新
     */
    Integer upsert(UserResource userResource);

    /**
     * 根据id获取
     */
    UserResource getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<UserResource> listByFilter(UserResource userResource, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(UserResource userResource);

}
