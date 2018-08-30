package com.zust.itee.dto.request.resource;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学习资源搜索dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResourceSearchDto {

    @ApiModelProperty("资源名称-模糊搜索")
    private String name;

    @ApiModelProperty("条线id")
    private Long typeId;

    @ApiModelProperty(value = "资源等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级。", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty(value = "资源类型。1：视频讲座；2：音频讲座；3：图文", allowableValues = "1,2,3")
    private Short type;

    @ApiModelProperty("发布人名称-模糊搜索")
    private String userName;

    @ApiModelProperty("讲师名称-模糊搜索")
    private String lecturerName;

    @ApiModelProperty("发布时间筛选-开始时间")
    // @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("发布时间筛选-结束时间")
    private Date endTime;

    @ApiModelProperty(value = "状态。1：正常；-1:已删除", allowableValues = "1,-1")
    private Short status;
}
