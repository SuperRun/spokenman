package com.zust.itee.enums;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 枚举类描述
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnumsType {

    @ApiModelProperty("码")
    private Short code;

    @ApiModelProperty("描述")
    private String name;
}
