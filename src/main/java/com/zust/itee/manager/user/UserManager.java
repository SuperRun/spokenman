package com.zust.itee.manager.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zust.itee.dto.UserDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.request.user.OrgUserSaveDto;
import com.zust.itee.dto.request.user.PersonalUserSaveDto;
import com.zust.itee.dto.request.user.UserSearchDto;
import com.zust.itee.dto.request.user.UserUpdateDto;
import com.zust.itee.entity.user.User;
import com.zust.itee.exception.BusinessException;

/**
 * 用户 manager
 *
 * @author pojun
 */
public interface UserManager {

    /**
     * 用户登录
     */
    void userLogin(UserDto userDto, HttpSession session);

    /**
     * 根据搜索查询用户
     *
     * @throws BusinessException
     */
    PageBaseDto<UserDto> listUserBySearch(Short type, UserSearchDto searchDto,
            PageBaseDto pageBaseDto) throws BusinessException;

    /**
     * 获取用户详情
     *
     * @throws BusinessException
     */
    UserDto getById(Integer id) throws BusinessException;

    /**
     * 更新用户
     *
     * @throws BusinessException
     */
    void updateUser(Integer id, UserUpdateDto updateDto) throws BusinessException;

    /**
     * 更新用户状态
     *
     * @throws BusinessException
     */
    Integer updateUserStatus(Integer id, Short status) throws BusinessException;

    /**
     * 平台用户新增个人用户
     *
     * @throws BusinessException
     */
    void savePersonalUser(Integer userId, PersonalUserSaveDto personalUserSaveDto)
            throws BusinessException;

    /**
     * 平台用户新增单位用户
     *
     * @throws BusinessException
     */
    void saveOrgUser(Integer userId, OrgUserSaveDto orgUserSaveDto) throws BusinessException;

    /**
     * 平台/单位用户，新增单位个人用户
     */
    void saveOrgPersonalUser(Integer userId, Integer orgId,
            PersonalUserSaveDto personalUserSaveDto);

    /**
     * 单位用户获取单位中用户
     *
     * @throws BusinessException
     */
    PageBaseDto<UserDto> listSubUsers(Integer orgId, UserDto userDto, PageBaseDto pageBaseDto)
            throws BusinessException;

    /**
     * 校验用户是否存在
     */
    User validateNotNull(Integer id) throws BusinessException;

    /**
     * 转换为 dto
     */
    UserDto convertToDto(User user) throws BusinessException;

    /**
     * 转换为 dto list
     */
    List<UserDto> convertToDtoList(List<User> users) throws BusinessException;
}
