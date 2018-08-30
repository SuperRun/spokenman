package com.zust.itee.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平台管理员添加单位用户 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgUserSaveDto {

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户手机号")
    private String phone;

    private String email;

    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty("组织简称")
    private String orgShortName;

    @ApiModelProperty("组织区域 id ，标准国标码")
    private Long areaId;

    @ApiModelProperty(value = "组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty("组织条线id")
    private Long typeId;

    @ApiModelProperty("组织联系电话")
    private String orgPhone;

    @ApiModelProperty("组织 email")
    private String orgEmail;
}
