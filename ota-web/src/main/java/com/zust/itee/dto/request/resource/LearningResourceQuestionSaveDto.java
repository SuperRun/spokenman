package com.zust.itee.dto.request.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 保存学习资源问题 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResourceQuestionSaveDto {

    @ApiModelProperty(value = "资源id", required = true)
    private Integer resourceId;

    @ApiModelProperty("题目要求作答时间，格式根据前端视频弹窗插件确定")
    private String time;

    @ApiModelProperty(value = "题目", required = true)
    private String content;

    @ApiModelProperty(value = "答案。字母：A、B、C……", required = true)
    private String answer;

    private String pic1;

    private String pic2;

    private String pic3;

    /**
     * 题目类型
     * {@link com.zust.itee.enums.resource.LearningResourceQuestionType}
     */
    @ApiModelProperty(value = "题目类型。1：单选题；2:多选题", allowableValues = "1,2", required = true)
    private short typeId;

    @ApiModelProperty("题目选项列表 array 字符串描述。例：[{\"content\":\"选项一文字\",\"pic\":\"选项二图片 url\","
            + "\"sequence\":1},{\"content\":\"选项二文字\",\"pic\":\"选项二图片 url\",\"sequence\":2}]\n")
    private String items;
}
