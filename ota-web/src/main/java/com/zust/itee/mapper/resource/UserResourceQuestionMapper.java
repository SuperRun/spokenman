package com.zust.itee.mapper.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.resource.UserResourceQuestion;

/**
 * 用户培训资源答题记录 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface UserResourceQuestionMapper {

    /**
     * 插入
     */
    Integer save(UserResourceQuestion userResourceQuestion);

    /**
     * 根据id获取
     */
    UserResourceQuestion getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<UserResourceQuestion> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(UserResourceQuestion userResourceQuestion);

    /**
     * 更新
     */
    Integer update(UserResourceQuestion userResourceQuestion);
}
