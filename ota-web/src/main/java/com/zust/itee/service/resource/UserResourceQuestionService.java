package com.zust.itee.service.resource;

import java.util.List;

import com.zust.itee.entity.resource.UserResourceQuestion;

/**
 * 用户培训资源答题记录
 *
 * @author pojun
 */
public interface UserResourceQuestionService {

    /**
     * 创建或更新
     */
    Integer upsert(UserResourceQuestion userResourceQuestion);

    /**
     * 根据id获取
     */
    UserResourceQuestion getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<UserResourceQuestion> listByFilter(UserResourceQuestion userResourceQuestion, Integer page,
            Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(UserResourceQuestion userResourceQuestion);
}
