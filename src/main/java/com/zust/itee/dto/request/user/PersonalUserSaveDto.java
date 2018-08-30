package com.zust.itee.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平台用户新增用户 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalUserSaveDto {

    @ApiModelProperty(value = "用户类型。1：个人用户，3：讲师用户", required = true, allowableValues = "1,3")
    private Short type;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户手机号")
    private String phone;

    private String email;
}
