package com.zust.itee.dto.request.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目选项保存 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionItemsUpdateDto {

    @ApiModelProperty("选项ID")
    private Integer id;

    @ApiModelProperty("选项文字描述")
    private String content;

    @ApiModelProperty("选项图片 url")
    private String pic;

    @ApiModelProperty("选项序列，从 1 开始")
    private Short sequence;
}
