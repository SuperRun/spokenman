package com.zust.itee.manager.user;

import java.util.List;

import com.zust.itee.dto.UserDto;
import com.zust.itee.dto.UserResourceDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.user.UserResourceSaveDto;
import com.zust.itee.dto.request.user.UserResourceSearchDto;
import com.zust.itee.dto.request.user.UserResourceUpdateDto;
import com.zust.itee.exception.BusinessException;

/**
 * 用户资源 manager
 *
 * @author pojun
 */
public interface UserResourceManager {
	
	/**
	 * 根据用户ID分页获取用户资源
	 * 
	 */
	PageBaseDto<UserResourceDto> listUserResources(Integer userId,PageBaseDto pageBaseDto)
            throws BusinessException;
	/**
	 * 根据id 获取用户学习资源
	 * 
	 */
	UserResourceDto getUserResourceById(Integer id);
	/**
	 * 用户添加学习资源
	 * 
	 */
	void saveUserResource(SessionInfo sessionInfo,UserResourceSaveDto userResourceSaveDto);
	/**
	 * 更新用户学习资源
	 * 
	 */
	void updateUserResource(Integer id,UserResourceUpdateDto userResourceUpdateDto)
			throws BusinessException;
	/**
	 * 用户删除学习资源
	 * 
	 */
	void deleteUserResource(Integer id) throws BusinessException;
	/**
	 * 搜索用户学习资源
	 * 
	 */
	PageBaseDto<UserResourceDto> listUserResourceBySearch(UserResourceSearchDto userResourceSearchDto,PageBaseDto pageBaseDto);
	
	/**
	 * 根据登录状态的用户ID，资源ID获取用户学习资源
	 * @param userId
	 * @param resourceId
	 * @return
	 */
	UserResourceDto getUserResource(Integer userId, Integer resourceId);

}