package com.zust.itee.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建用户时创建组织 dto
 *
 * @author pojun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("新增单位用户/单位用户注册时单位信息")
public class UserOrgDto {

    @ApiModelProperty("新增单位用户时填写-组织简称")
    private String orgShortName;

    @ApiModelProperty("新增单位用户时填写-组织联系电话")
    private String orgPhone;

    @ApiModelProperty("新增单位用户时填写-组织 email")
    private String orgEmail;
}
