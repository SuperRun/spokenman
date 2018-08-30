package com.zust.itee.dto.request.training;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训资源保存 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingResourceSaveDto {

    @ApiModelProperty(value = "培训 id", required = true)
    private Integer trainingId;

    @ApiModelProperty(value = "资源id", required = true)
    private Integer resourceId;

    @ApiModelProperty(value = "是否必修。0:非必修，1：必修", allowableValues = "0,1", required = true)
    private Short required;
}
