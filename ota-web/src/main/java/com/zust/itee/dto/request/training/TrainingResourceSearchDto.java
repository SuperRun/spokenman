package com.zust.itee.dto.request.training;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训资源搜索 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingResourceSearchDto {

    @ApiModelProperty("资源名称-模糊搜索")
    private String resourceName;

    @ApiModelProperty("讲师名称-模糊搜索")
    private String lecturerName;

    @ApiModelProperty("条线id")
    private Long typeId;

    @ApiModelProperty(value = "资源等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级。", allowableValues = "1,2,3,4,5")
    private Short level;
}
