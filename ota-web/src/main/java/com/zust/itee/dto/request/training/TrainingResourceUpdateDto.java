package com.zust.itee.dto.request.training;

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
public class TrainingResourceUpdateDto {

    @ApiModelProperty(value = "是否必修。0：非必修，1：必修", allowableValues = "0,1")
    private Short required;

    @ApiModelProperty("备注")
    private String remark;
}
