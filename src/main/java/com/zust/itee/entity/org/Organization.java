package com.zust.itee.entity.org;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组织实体类
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization implements Serializable {

    private Integer id;

    /**
     * 组织全称
     */
    private String name;

    /**
     * 组织简称
     */
    private String shortName;

    /**
     * 负责人id
     */
    private Integer userId;

    /**
     * 区域 id ，国标码
     */
    private Long areaId;

    /**
     * 组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级
     */
    private Short level;

    /**
     * 组织条线。关联data_dictionary
     */
    private Long typeId;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * email
     */
    private String email;

    /**
     * 父级组织id
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Integer creatorId;

    /**
     * 状态
     */
    private Short status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
