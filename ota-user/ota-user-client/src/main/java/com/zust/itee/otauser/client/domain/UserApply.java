package com.zust.itee.otauser.client.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册申请实体类
 *
 * @author pojun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApply implements Serializable {

    private Integer id;

    /**
     * 申请人id
     */
    private Integer userId;

    /**
     * 申请组织id
     */
    private Integer orgId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 审批状态
     * {@link com.zust.itee.otauser.client.enums.UserApplyStatusType}
     */
    private Short status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

