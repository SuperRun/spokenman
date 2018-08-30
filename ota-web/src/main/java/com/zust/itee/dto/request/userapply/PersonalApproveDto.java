package com.zust.itee.dto.request.userapply;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户资料审核申请 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalApproveDto {

    @ApiModelProperty(value = "真实姓名", required = true)
    private String name;

    @ApiModelProperty(value = "身份证号", required = true)
    private String idCard;

    @ApiModelProperty(value = "身份证照-正面", required = true)
    private String picFront;

    @ApiModelProperty(value = "身份证照-反面", required = true)
    private String picBack;

    @ApiModelProperty(value = "组织条线 id", required = true)
    private Long typeId;

    @ApiModelProperty(value = "组织等级", required = true)
    private Short level;

    @ApiModelProperty(value = "组织区域id", required = true)
    private Long areaId;

    @ApiModelProperty(value = "组织名称", required = true)
    private String orgName;

    @ApiModelProperty(value = "组织证明照", required = true)
    private String picOrg;
}
