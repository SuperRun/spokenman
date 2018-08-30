package com.zust.itee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目选项表
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionItemDto {

    private Integer id;

    @ApiModelProperty("选项文字内容")
    private String content;

    @ApiModelProperty("选项图片 url")
    private String pic;

    @ApiModelProperty("选项序列，阿拉伯数字。1、2、3……")
    private Short sequence;

    @ApiModelProperty("选项序列描述，字母。A、B、C……")
    private String sequenceStr;

    private Integer questionId;

    private Integer resourceQuestionId;
}
