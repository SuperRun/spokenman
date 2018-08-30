package com.zust.itee.entity.resource;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学习资源 entity
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResource {

    private Integer id;

    private String name;

    /**
     * 资源类型
     * {@link com.zust.itee.enums.resource.LearningResourceType}
     */
    private Short type;

    private String url;

    private String description;

    private Integer lecturerId;

    private String lecturerName;

    private Integer orgId;

    /**
     * 资源等级
     * {@link com.zust.itee.enums.org.OrgLevelType}
     */
    private Short level;

    private Long typeId;

    // 发布人 id
    private Integer userId;

    // 发布人姓名
    private String userName;

    private Short score;

    /**
     * 资源权限类型
     * {@link com.zust.itee.enums.resource.LearningResourceAuthType}
     */
    private Short authType;

    private Date time;

    private Date createTime;

    private Date updateTime;

    private Short status;

    private String remark;

    private String pic;

    private Integer duration;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
