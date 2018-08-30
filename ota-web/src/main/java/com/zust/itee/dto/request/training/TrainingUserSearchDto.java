package com.zust.itee.dto.request.training;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户培训情况搜索 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingUserSearchDto {

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("培训名称")
    private String trainingName;

    @ApiModelProperty(value = "用户培训状态。0：已报名，1：通过，2：不通过", allowableValues = "0,1,2")
    private Short status;

    @ApiModelProperty("用户区域id，国标码")
    private Long userAreaId;

    @ApiModelProperty("用户条线id")
    private Long userTypeId;

    @ApiModelProperty(value = "用户等级", allowableValues = " 1,2,3,4,5")
    private Short userLevel;
}
