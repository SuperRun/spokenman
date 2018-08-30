package com.zust.itee.dto.request.training;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训筛选 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSearchDto {

    @ApiModelProperty("培训名称-模糊搜索")
    private String name;

    @ApiModelProperty("发布人组织id")
    private Integer orgId;

    @ApiModelProperty("发布人姓名-模糊搜索")
    private String userName;

    @ApiModelProperty("创建开始时间-时间筛选")
    private Date startTime;

    @ApiModelProperty("创建结束时间-时间筛选")
    private Date endTime;

    /**
     * {@link com.zust.itee.enums.org.OrgLevelType}
     */
    @ApiModelProperty(value = "培训等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty("培训条线id")
    private Long typeId;

    @ApiModelProperty(value = "培训状态码。1:已发布，2：报名中，3：待培训，4：培训证，5：确认中，6：已完成",
            allowableValues = "1,2,3,4,5,6")
    private Short status;
}
