package com.zust.itee.dto;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 dto
 *
 * @author pojun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    private String email;

    @ApiModelProperty("组织id")
    private Integer orgId;
    @ApiModelProperty("组织区域id,标准国标码")
    private Long areaId;
    @ApiModelProperty("组织区域名称")
    private String areaName;

    @ApiModelProperty("组织名称")
    private String orgName;
    @ApiModelProperty("组织简称")
    private String orgShortName;

    @ApiModelProperty(value = "组织等级code", allowableValues = "1,2,3,4,5")
    private Short level;
    @ApiModelProperty(value = "组织等级描述", allowableValues = "国家级,省部级,司厅局级,县初级,乡镇级")
    private String levelName;

    @ApiModelProperty("组织条线id")
    private Long typeId;
    @ApiModelProperty("组织条线描述")
    private String orgType;

    /**
     * 用户类型
     * {@link com.zust.itee.enums.user.UserStatusType}
     */
    @ApiModelProperty(value = "用户类型code", allowableValues = "0,1,2,3")
    private Short type;
    @ApiModelProperty(value = "用户类型描述", allowableValues = "平台用户,个人用户,单位用户,讲师")
    private String typeName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty(value = "用户状态", allowableValues = "-1,1,0,2,-2,3")
    private Short status;
    @ApiModelProperty(value = "用户状态描述", allowableValues = "删除,正常,审核拒绝,申请中,账号冻结,新注册")
    private String statusName;

    @ApiModelProperty("创建人id")
    private Integer creatorId;
    @ApiModelProperty("创建人姓名")
    private String creatorName;

    @ApiModelProperty("用户注册申请id")
    private Integer userApplyId;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "身份证照-正面")
    private String picFront;

    @ApiModelProperty(value = "身份证照-反面")
    private String picBack;

    @ApiModelProperty(value = "组织证明照")
    private String picOrg;

    @ApiModelProperty(value = "总学分")
    private Short score;

    @ApiModelProperty("总学习时长，小时")
    private Integer learnedHour;

    @ApiModelProperty("总学习时长，分钟")
    private Integer learnedMinute;

    @ApiModelProperty("总学习时长，秒")
    private Integer learnedSecond;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
