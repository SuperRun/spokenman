package com.zust.itee.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.user.User;

/**
 * 用户mapper
 *
 * @author pojun
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 插入
     */
    Integer save(User user);

    /**
     * 根据id获取
     */
    User getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<User> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(User user);

    /**
     * 更新
     */
    Integer update(User user);

    /**
     * 登录
     */
    User login(User user);

    /**
     * 根据搜索查询 user
     */
    List<User> listBySearch(Map params);

    /**
     * 根据搜索 count
     */
    Long countBySearch(User user);
}
