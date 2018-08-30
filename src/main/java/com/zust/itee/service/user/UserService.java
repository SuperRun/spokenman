package com.zust.itee.service.user;

import java.util.List;

import com.zust.itee.entity.user.User;
import com.zust.itee.enums.user.UserStatusType;

/**
 * 用户service
 *
 * @author pojun
 */
public interface UserService {

    /**
     * 创建或更新
     */
    Integer upsert(User user);

    /**
     * 根据id获取
     */
    User getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<User> listByFilter(User user, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(User user);

    /**
     * 用户登录
     *
     * @param userName name 或 phone
     */
    User login(String userName, String password);

    /**
     * 查询组织中用户
     */
    List<User> listByOrgId(Integer orgId, Integer page, Integer limit);

    /**
     * 根据搜索查询用户
     *
     * @param user name 模糊查询 。 orgId 、 level 、 typeId 、 status 精确查询
     */
    List<User> listBySearch(User user, Integer page, Integer limit);

    /**
     * 根据搜索 count
     */
    Long countBySearch(User user);

    /**
     * 更新用户状态
     *
     * @throws RuntimeException 当原用户不存在
     */
    Integer updateUserStatus(Integer id, UserStatusType userStatusType) throws RuntimeException;
}
