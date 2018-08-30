package com.zust.itee.exam.dto.exam.member;

import com.zust.itee.exam.entity.TexamResult;

/**
 * 查卷时使用,给出每道题详情，考生答案，标准答案，得分
 *
 * @author sdy
 */
public class CheckPaper {

    //问题详情
    private QuestionModel questionModel;

    //考生答案
    private String userAnswer;

    //得分
    private double score;

    public CheckPaper(QuestionModel questionModel, TexamResult texamResult) {
        super();
        this.questionModel = questionModel;
        this.userAnswer = texamResult.getAnswer();
        this.score = texamResult.getMarks();
    }

    public QuestionModel getQuestionModel() {
        return questionModel;
    }

    public void setQuestionModel(QuestionModel questionModel) {
        this.questionModel = questionModel;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CheckPaper [questionModel="
                + questionModel.toString()
                + ", userAnswer="
                + userAnswer
                + ", score="
                + score
                + "]";
    }
}
