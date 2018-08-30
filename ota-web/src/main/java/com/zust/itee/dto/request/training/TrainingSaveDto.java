package com.zust.itee.dto.request.training;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建培训 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSaveDto {

    @ApiModelProperty(value = "培训名称", required = true)
    private String name;

    @ApiModelProperty("必修学分")
    private Short requiredScore;

    @ApiModelProperty("选修学分")
    private Short optionalScore;

    @ApiModelProperty(value = "培训开始时间", required = true)
    private Date startTime;

    @ApiModelProperty(value = "培训结束时间", required = true)
    private Date endTime;

    @ApiModelProperty(value = "报名开始时间", required = true)
    private Date signStartTime;

    @ApiModelProperty(value = "报名结束时间", required = true)
    private Date signEndTime;

    @ApiModelProperty(value = "等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty("条线 id")
    private Long typeId;

    @ApiModelProperty("说明")
    private String description;

    @ApiModelProperty("宣传图片")
    private String pic;

    @ApiModelProperty("关联考试 id")
    private Integer examId;
}
