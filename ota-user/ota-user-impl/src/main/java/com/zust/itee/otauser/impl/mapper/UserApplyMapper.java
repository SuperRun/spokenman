package com.zust.itee.otauser.impl.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zust.itee.otauser.client.domain.UserApply;

/**
 * 用户注册申请mapper
 *
 * @author pojun
 */
@Repository
public interface UserApplyMapper {

    /**
     * 插入
     */
    Integer save(UserApply userApply);

    /**
     * 根据id获取
     */
    UserApply getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<UserApply> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(UserApply userApply);

    /**
     * 更新
     */
    Integer update(UserApply userApply);
}
