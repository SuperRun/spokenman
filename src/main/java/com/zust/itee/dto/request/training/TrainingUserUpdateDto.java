package com.zust.itee.dto.request.training;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户培训记录更新 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingUserUpdateDto {

    @ApiModelProperty("证书号")
    private String certificateNo;

    @ApiModelProperty("证书照片 url")
    private String certificatePhoto;
}
