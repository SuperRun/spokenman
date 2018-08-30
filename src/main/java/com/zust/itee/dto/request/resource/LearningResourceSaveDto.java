package com.zust.itee.dto.request.resource;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 插入学习资源 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResourceSaveDto {

    @ApiModelProperty("资源名称")
    private String name;

    /**
     * 资源类型
     * {@link com.zust.itee.enums.resource.LearningResourceType}
     */
    @ApiModelProperty(value = "资源类型。1：视频讲座；2：音频讲座；3：图文", allowableValues = "1,2,3")
    private Short type;

    @ApiModelProperty("资源 url")
    private String url;

    @ApiModelProperty("资源描述")
    private String description;

    @ApiModelProperty("讲师 id")
    private Integer lecturerId;

    @ApiModelProperty("讲师名称")
    private String lecturerName;

    /**
     * 资源等级
     * {@link com.zust.itee.enums.org.OrgLevelType}
     */
    @ApiModelProperty(value = "资源等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级。", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty("条线id")
    private Long typeId;

    @ApiModelProperty("资源学分")
    private Short score;

    /**
     * 资源权限类型
     * {@link com.zust.itee.enums.resource.LearningResourceAuthType}
     */
    @ApiModelProperty(value = "资源权限类型。1：所有等级可见；0:下级不可见", allowableValues = "1,0", required = true)
    private Short authType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("学习资源时间")
    private Date time;

    @ApiModelProperty("宣传图片")
    private String pic;

    @ApiModelProperty("视频时长")
    private Integer duration;
}
