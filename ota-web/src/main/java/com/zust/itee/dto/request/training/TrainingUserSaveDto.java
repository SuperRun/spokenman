package com.zust.itee.dto.request.training;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户培训记录添加 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingUserSaveDto {

    @ApiModelProperty(value = "培训id", required = true)
    private Integer trainingId;

    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;
}
