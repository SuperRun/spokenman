package com.zust.itee.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.user.UserResource;

/**
 * 用户资源查看 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface UserResourceMapper {

    /**
     * 插入
     */
    Integer save(UserResource userResource);

    /**
     * 根据id获取
     */
    UserResource getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<UserResource> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(UserResource userResource);

    /**
     * 更新
     */
    Integer update(UserResource userResource);
}
