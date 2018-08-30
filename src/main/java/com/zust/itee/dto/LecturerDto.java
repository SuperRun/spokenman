package com.zust.itee.dto;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 讲师 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LecturerDto {

    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("讲师姓名")
    private String name;

    @ApiModelProperty("讲师简介")
    private String introduction;

    @ApiModelProperty("讲师组织id")
    private Integer orgId;
    @ApiModelProperty("讲师组织名称")
    private String orgName;

    /**
     * {@link com.zust.itee.enums.org.OrgLevelType}
     */
    @ApiModelProperty(value = "讲师等级id", allowableValues = "1,2,3,4,5")
    private Short level;
    @ApiModelProperty(value = "讲师等级名称", allowableValues = "国家级,省部级,司厅局级,县处级,乡镇级")
    private String levelName;

    @ApiModelProperty("条线id")
    private Long typeId;
    @ApiModelProperty("条线名称")
    private String typeName;

    @ApiModelProperty("状态码")
    private Short status;
    @ApiModelProperty("状态名称")
    private String statusName;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
