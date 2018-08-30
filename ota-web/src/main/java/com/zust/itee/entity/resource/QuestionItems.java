package com.zust.itee.entity.resource;

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
public class QuestionItems {

    private Integer id;

    private String content;

    private String pic;

    private Short sequence;

    private Integer questionId;

    private Integer resourceQuestionId;
}
