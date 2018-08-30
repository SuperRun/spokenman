package com.zust.itee.dto.request.userapply;

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
public class OrgUserApply {

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty(value = "组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty("组织区域id，国标码")
    private Long areaId;

    @ApiModelProperty("组织条线id")
    private Long typeId;
}
