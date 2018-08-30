package com.zust.itee.dto;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.dto.LecturerDto.LecturerDtoBuilder;
import com.zust.itee.enums.user.UserResourceStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户资源 dto
 *
 * @author asus
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResourceDto {

    private Integer id;

    private Integer resourceId;

    @ApiModelProperty("用户id")
    private Integer userId;

    //上次观看至视频时间
    private String time;

    private Date createTime;

    private Date updateTime;

    private Short score;

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

    @ApiModelProperty("资源时长")
    private Integer duration;

    @ApiModelProperty("视频时长，小时")
    private Integer durationHour;

    @ApiModelProperty("视频时长，分")
    private Integer durationMinute;

    @ApiModelProperty("视频时长，秒")
    private Integer durationSecond;

    @ApiModelProperty("资源图片")
    private String pic;

    @ApiModelProperty("已观看进度。例：50 （观看进度为 50%）")
    private Integer process;

    @ApiModelProperty("已观看小时数。例：观看了 1:20:50 ，字段值为 1")
    private Integer learnedHour;

    @ApiModelProperty("已观看分钟。例：观看了 1:20:50 ，字段值为 20")
    private Integer learnedMinute;

    @ApiModelProperty("已观看秒。例：观看了 1:20:50 ，字段值为 50")
    private Integer learnedSecond;

    /**
     * 学习状态
     * {@link com.zust.itee.enums.resource.UserResourceStatus}
     */
    @ApiModelProperty(value = "状态。1：学习中；2:学习完成", allowableValues = "1,2")
    private Short status;
    @ApiModelProperty("状态名称")
    private String statusName;

    public static UserResourceDto undefine() {
        return UserResourceDto.builder()
                .time("0")
                .score((short) 0)
                .process(0)
                .learnedHour(0)
                .learnedMinute(0)
                .learnedSecond(0)
                .status(UserResourceStatus.UNDEFINE.getStatus())
                .statusName(UserResourceStatus.UNDEFINE.getDescription())
                .build();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
