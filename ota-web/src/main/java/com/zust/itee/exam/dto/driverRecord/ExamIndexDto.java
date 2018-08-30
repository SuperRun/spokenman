package com.zust.itee.exam.dto.driverRecord;

import java.util.Date;

/**
 * Created by liy on 2016/12/21.
 */
public class ExamIndexDto {

    private Integer id;
    private Integer examId;
    private String examName;
    private Date examTime;
    private Double finalScore;
    private String certificateNo;

    public ExamIndexDto(Integer id, Integer examId, String examName, Date examTime, Double finalScore, String certificateNo) {
        this.id = id;
        this.examId = examId;
        this.examName = examName;
        this.examTime = examTime;
        this.finalScore = finalScore;
        this.certificateNo = certificateNo;
    }

    public ExamIndexDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }
}
