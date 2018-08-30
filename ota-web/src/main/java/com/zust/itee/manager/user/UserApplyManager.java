package com.zust.itee.manager.user;

import com.zust.itee.dto.request.userapply.OrgUserApply;
import com.zust.itee.dto.request.userapply.PersonalApproveDto;
import com.zust.itee.dto.request.userapply.PersonalUserApplyDto;
import com.zust.itee.exception.BusinessException;

/**
 * 用户申请 manager
 *
 * @author pojun
 */
public interface UserApplyManager {

    /**
     * 个人用户注册
     *
     * @throws BusinessException
     */
    void personalUserApply(PersonalUserApplyDto personalUserApplyDto) throws BusinessException;

    /**
     * 个人用户资料审核申请
     *
     * @throws BusinessException
     */
    void personalApprove(Integer userId, PersonalApproveDto personalApproveDto)
            throws BusinessException;

    /**
     * 单位用户注册
     *
     * @throws BusinessException
     */
    void orgUserApply(OrgUserApply orgUserApply) throws BusinessException;

    /**
     * 更新用户申请状态
     *
     * @throws BusinessException
     */
    void updateUserApplyStatus(Integer id, Short status) throws BusinessException;
}
