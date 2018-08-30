package com.zust.itee.dto;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训用户 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingUserDto {

    private Integer id;

    @ApiModelProperty("培训 id")
    private Integer trainingId;
    @ApiModelProperty("培训名称")
    private String trainingName;

    @ApiModelProperty("培训信息")
    private TrainingDto training;

    @ApiModelProperty("用户 id")
    private Integer userId;
    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("已完成必修学分")
    private Short requiredScore;

    @ApiModelProperty("已完成选修学分")
    private Short optionalScore;

    @ApiModelProperty("考试成绩")
    private Short examScore;

    @ApiModelProperty("证书号")
    private String certificateNo;

    @ApiModelProperty("证书 url")
    private String certificatePhoto;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("状态")
    private Short status;
    @ApiModelProperty("状态名称")
    private String statusName;

    @ApiModelProperty("用户区域id，国标码")
    private Long userAreaId;

    @ApiModelProperty("用户条线id")
    private Long userTypeId;

    @ApiModelProperty(value = "用户等级")
    private Short userLevel;

    @ApiModelProperty("用户信息")
    private UserDto userDto;

    @ApiModelProperty("已学习课程")
    private List<TrainingResourceDto> learnedResources;

    @ApiModelProperty("未学习课程")
    private List<TrainingResourceDto> toLearnResources;

    @ApiModelProperty("培训配置学习资源")
    private List<TrainingResourceDto> trainingResources;

    @ApiModelProperty("培训配置必修学习资源")
    private List<TrainingResourceDto> requiredResources;

    @ApiModelProperty("培训配置选修学习资源")
    private List<TrainingResourceDto> optionalResources;

    @ApiModelProperty("总资源时长")
    private Integer duration;

    @ApiModelProperty("总资源时长，小时")
    private Integer durationHour;

    @ApiModelProperty("总资源时长，分")
    private Integer durationMinute;

    @ApiModelProperty("总资源时长，秒")
    private Integer durationSecond;

    @ApiModelProperty("已观看进度。例：50 （观看进度为 50%）")
    private Integer process;

    @ApiModelProperty("已观看小时数。例：观看了 1:20:50 ，字段值为 1")
    private Integer learnedHour;

    @ApiModelProperty("已观看分钟。例：观看了 1:20:50 ，字段值为 20")
    private Integer learnedMinute;

    @ApiModelProperty("已观看秒。例：观看了 1:20:50 ，字段值为 50")
    private Integer learnedSecond;
}
