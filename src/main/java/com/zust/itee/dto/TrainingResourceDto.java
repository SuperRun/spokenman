package com.zust.itee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO 类描述
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingResourceDto {

    private Integer id;

    @ApiModelProperty("培训id")
    private Integer trainingId;

    @ApiModelProperty("资源id")
    private Integer resourceId;

    @ApiModelProperty("学习资源")
    private LearningResourceDto learningResource;

    @ApiModelProperty("是否必修。0：非必修，1：必修")
    private Short required;

    @ApiModelProperty("是否必修")
    private String requiredName;

    @ApiModelProperty("学分")
    private Short score;

    @ApiModelProperty("状态")
    private Short status;

    @ApiModelProperty("状态名称")
    private String statusName;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("讲师名称")
    private String lecturerName;

    @ApiModelProperty("资源条线id")
    private Long typeId;

    @ApiModelProperty("资源等级")
    private Short level;

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("资源图片")
    private String pic;
}
