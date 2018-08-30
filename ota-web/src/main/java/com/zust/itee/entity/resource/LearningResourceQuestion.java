package com.zust.itee.entity.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学习资源问题关联
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResourceQuestion {

    private Integer id;

    private Integer resourceId;

    private String content;

    private String answer;

    private String pic1;

    private String pic2;

    private String pic3;

    private String time;

    private Short status;

    /**
     * 题目类型
     * {@link com.zust.itee.enums.resource.LearningResourceQuestionType}
     */
    private Short typeId;
}
