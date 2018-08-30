package com.zust.itee.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingDto {

    private Integer id;

    @ApiModelProperty("培训名称")
    private String name;

    @ApiModelProperty("发布人组织id")
    private Integer orgId;
    @ApiModelProperty("发布人组织名称")
    private String orgName;

    @ApiModelProperty("发布人id")
    private Integer userId;
    @ApiModelProperty("发布人姓名")
    private String userName;

    @ApiModelProperty("必修学分")
    private Short requiredScore;

    @ApiModelProperty("选修学分")
    private Short optionalScore;

    @ApiModelProperty("培训开始时间")
    private Date startTime;

    @ApiModelProperty("培训结束时间")
    private Date endTime;

    @ApiModelProperty("报名开始时间")
    private Date signStartTime;

    @ApiModelProperty("报名结束时间")
    private Date signEndTime;

    /**
     * {@link com.zust.itee.enums.org.OrgLevelType}
     */
    @ApiModelProperty(value = "培训等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级", allowableValues = "1,2,3,4,5")
    private Short level;
    @ApiModelProperty("培训等级名称")
    private String levelName;

    @ApiModelProperty("培训条线id")
    private Long typeId;
    @ApiModelProperty("培训条线名称")
    private String typeName;

    @ApiModelProperty("培训描述")
    private String description;

    @ApiModelProperty("培训关联考试 id")
    private Integer examId;
    @ApiModelProperty("培训关联考试名称")
    private String examName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty(value = "培训状态码。1:已发布，2：报名中，3：待培训，4：培训证，5：确认中，6：已完成",
            allowableValues = "1,2,3,4,5,6")
    private Short status;

    @ApiModelProperty("培训状态名称")
    private String statusName;

    @ApiModelProperty("宣传图片")
    private String pic;
}
