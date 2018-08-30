package com.zust.itee.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户修改 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {

    private Integer id;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    private String email;

    @ApiModelProperty("组织区域id,标准国标码。更新单位用户时，对应单位联动更新")
    private Long areaId;
    @ApiModelProperty("组织名称。更新单位用户时，对应单位联动更新")
    private String orgName;
    @ApiModelProperty("组织简称。更新单位用户时，对应单位联动更新")
    private String orgShortName;

    @ApiModelProperty(value = "组织等级code。1:国家级,2:省部级,3:司厅局级,4:县初级,5:乡镇级。更新单位用户时，对应单位联动更新",
            allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty("组织条线id。更新单位用户时，对应单位联动更新")
    private Long typeId;
}
