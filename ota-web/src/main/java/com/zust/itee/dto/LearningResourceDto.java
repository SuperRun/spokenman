package com.zust.itee.dto;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学习资源 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResourceDto {

    @ApiModelProperty("资源id")
    private Integer id;

    @ApiModelProperty("资源名称")
    private String name;

    /**
     * 资源类型
     * {@link com.zust.itee.enums.resource.LearningResourceType}
     */
    @ApiModelProperty(value = "资源类型。1：视频讲座；2：音频讲座；3：图文", allowableValues = "1,2,3")
    private Short type;
    @ApiModelProperty("资源类型名称")
    private String typeName;

    @ApiModelProperty("资源 url")
    private String url;

    @ApiModelProperty("资源描述")
    private String description;

    @ApiModelProperty("讲师 id")
    private Integer lecturerId;

    @ApiModelProperty("讲师名称")
    private String lecturerName;
    @ApiModelProperty("讲师用户名称")
    private String lecturerUserName;

    @ApiModelProperty("发布人组织id")
    private Integer orgId;
    @ApiModelProperty("发布人组织名称")
    private String orgName;

    /**
     * 资源等级
     * {@link com.zust.itee.enums.org.OrgLevelType}
     */
    @ApiModelProperty(value = "资源等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级。", allowableValues = "1,2,3,4,5")
    private Short level;
    @ApiModelProperty("资源等级名称")
    private String levelName;

    @ApiModelProperty("条线id")
    private Long typeId;
    @ApiModelProperty("条线名称")
    private String typeIdName;

    @ApiModelProperty("发布人id")
    private Integer userId;
    @ApiModelProperty("发布人名称")
    private String userName;

    @ApiModelProperty("资源学分")
    private Short score;

    /**
     * 资源权限类型
     * {@link com.zust.itee.enums.resource.LearningResourceAuthType}
     */
    @ApiModelProperty(value = "资源权限类型。1：所有等级可见；0:下级不可见", allowableValues = "1,0")
    private Short authType;
    @ApiModelProperty("资源权限类型名称")
    private String authTypeName;

    @ApiModelProperty("资源时间")
    private Date time;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "资源状态。1：正常；-1：删除", allowableValues = "1,-1")
    private Short status;
    @ApiModelProperty("状态描述")
    private String statusName;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("宣传图片")
    private String pic;

    @ApiModelProperty("视频时长")
    private Integer duration;

    @ApiModelProperty("视频时长，小时")
    private Integer durationHour;

    @ApiModelProperty("视频时长，分")
    private Integer durationMinute;

    @ApiModelProperty("视频时长，秒")
    private Integer durationSecond;

    @ApiModelProperty("学习资源题目")
    private List<LearningResourceQuestionDto> questions;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
