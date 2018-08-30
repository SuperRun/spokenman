package com.zust.itee.otauser.client.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    private String email;

    /**
     * 所属组织id
     */
    private Integer orgId;

    /**
     * 组织区域 id
     */
    private Long areaId;

    /**
     * 用户类型
     * {@link com.zust.itee.otauser.client.enums.UserType}
     */
    private Short type;

    /**
     * 组织等级
     */
    private Short level;

    /**
     * 组织条线
     */
    private Long typeId;

    private Date createTime;

    private Short status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
