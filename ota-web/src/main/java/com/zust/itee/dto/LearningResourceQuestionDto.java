package com.zust.itee.dto;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学习资源问题 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResourceQuestionDto {

    private Integer id;

    @ApiModelProperty("资源id")
    private Integer resourceId;

    @ApiModelProperty("题目题干")
    private String content;

    @ApiModelProperty("题目答案")
    private String answer;

    @ApiModelProperty("图1 url")
    private String pic1;

    @ApiModelProperty("图1 url")
    private String pic2;

    @ApiModelProperty("图1 url")
    private String pic3;

    @ApiModelProperty("题目要求作答时间")
    private String time;

    @ApiModelProperty(value = "题目类型。1：单选题；2:多选题", allowableValues = "1,2")
    private Short typeId;
    @ApiModelProperty("状态名称")
    private String typeName;

    @ApiModelProperty(value = "状态。-1：已删除；1:正常", allowableValues = "-1,1")
    private Short status;
    @ApiModelProperty("状态名称")
    private String statusName;

    @ApiModelProperty("题目选项")
    private List<QuestionItemDto> items;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
