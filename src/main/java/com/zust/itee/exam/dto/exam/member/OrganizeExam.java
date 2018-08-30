package com.zust.itee.exam.dto.exam.member;

import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.util.MyDateUtil;

/**
 * 工作人员组织考试表单提交内容
 *
 * @author sdy
 */
public class OrganizeExam {

    //考试id
    private Integer examId;

    //考试名称
    private String examName;

    //考试描述
    private String examDescription;

    //报名开始时间
    private String signupStartTime;

    //报名结束时间
    private String signupEndTime;

    //考试开始时间
    private String examStartTime;

    //考试结束时间
    private String examEndTime;

    //考试时长
    private int duration;

    //考试类型(0正式考，1模拟考)
    private short examType;

    //考试子类型(0正式线上考试，2正式线下考试)
    private short examSubType;

    //试卷选择方式（exist选择已有试卷，new创建试卷）
    private String paperType;

    public OrganizeExam() {
        super();
    }

    public OrganizeExam(Texam texam) {
        super();
        this.examName = texam.getName();
        this.examDescription = texam.getDescription();
        this.signupStartTime = MyDateUtil.dateToAjaxString(texam.getSignupStartTime());
        this.signupEndTime = MyDateUtil.dateToAjaxString(texam.getSignupEndTime());
        this.examStartTime = MyDateUtil.dateToAjaxString(texam.getExamStartTime());
        this.examEndTime = MyDateUtil.dateToAjaxString(texam.getExamEndTime());
        this.duration = texam.getDuration();
        this.examType = texam.getType();
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDescription() {
        return examDescription;
    }

    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }

    public short getExamType() {
        return examType;
    }

    public void setExamType(short examType) {
        this.examType = examType;
    }

    public String getSignupStartTime() {
        return signupStartTime;
    }

    public void setSignupStartTime(String signupStartTime) {
        this.signupStartTime = signupStartTime;
    }

    public String getSignupEndTime() {
        return signupEndTime;
    }

    public void setSignupEndTime(String signupEndTime) {
        this.signupEndTime = signupEndTime;
    }

    public String getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(String examStartTime) {
        this.examStartTime = examStartTime;
    }

    public String getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(String examEndTime) {
        this.examEndTime = examEndTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public short getExamSubType() {
        return examSubType;
    }

    public void setExamSubType(short examSubType) {
        this.examSubType = examSubType;
    }
}
