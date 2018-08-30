package com.zust.itee.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组织 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationDto {

    private Integer id;

    @ApiModelProperty("组织全称")
    private String name;

    @ApiModelProperty("组织简称")
    private String shortName;

    @ApiModelProperty("负责人id")
    private Integer userId;

    @ApiModelProperty("区域 id ，标准国标码")
    private Long areaId;
    @ApiModelProperty("区域名称")
    private String areaName;

    @ApiModelProperty(value = "组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级", allowableValues = "1,2,3,4,5")
    private Short level;
    @ApiModelProperty("等级名称")
    private String levelName;

    @ApiModelProperty("组织条线id")
    private Long typeId;
    @ApiModelProperty("组织条线名称")
    private String typeName;

    @ApiModelProperty("联系电话")
    private String phone;

    private String email;

    @ApiModelProperty("父组织id")
    private Integer parentId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建人id")
    private Integer creatorId;

    @ApiModelProperty("状态")
    private Short status;
    @ApiModelProperty("状态名称")
    private String statusName;
}
