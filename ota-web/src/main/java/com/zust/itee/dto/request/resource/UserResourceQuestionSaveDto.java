package com.zust.itee.dto.request.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建答题记录 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResourceQuestionSaveDto {

    @ApiModelProperty(value = "用户id",required = true)
    private Integer userId;

    @ApiModelProperty(value = "资源问题 id",required = true)
    private Integer resourceQuestionId;

}
