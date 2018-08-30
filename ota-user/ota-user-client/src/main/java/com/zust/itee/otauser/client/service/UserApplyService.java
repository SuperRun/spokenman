package com.zust.itee.otauser.client.service;

import java.util.List;

import com.zust.itee.otauser.client.domain.UserApply;
import com.zust.itee.otauser.client.enums.UserApplyStatusType;

/**
 * 用户注册申请 service
 *
 * @author pojun
 */
public interface UserApplyService {

    /**
     * 创建或更新
     */
    Integer upsert(UserApply userApply);

    /**
     * 根据id获取
     */
    UserApply getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<UserApply> listByFilter(UserApply userApply, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(UserApply userApply);

    /**
     * 查询某用户的申请
     */
    List<UserApply> listByUserId(Integer userId, Integer page, Integer limit);

    /**
     * 查询某组织的申请
     */
    List<UserApply> listByOrgId(Integer orgId, Integer page, Integer limit);

    /**
     * 修改用户申请状态
     */
    Integer updateUserApplyStatus(Integer id, UserApplyStatusType userApplyStatusType)
            throws RuntimeException;
}
