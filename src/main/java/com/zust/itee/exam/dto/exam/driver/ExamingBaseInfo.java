package com.zust.itee.exam.dto.exam.driver;

/**
 * 考试时显示的考试基本信息
 * @author sdy
 *
 */
public class ExamingBaseInfo {
	//考生名字
	private String driverName;

	//考试名称
	private String examName;

	//试卷名称
	private String paperName;

	//考试时长（分钟）
	private Integer duration;

	//总题数
	private Integer questionSum;

	//总分
	private Double scoreSum;

	//及格题目数
	private Integer passNum;

	//及格分
	private Integer passScore;

	//最终得分
	private Double score;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getQuestionSum() {
		return questionSum;
	}

	public void setQuestionSum(Integer questionSum) {
		this.questionSum = questionSum;
	}

	public Double getScoreSum() {
		return scoreSum;
	}

	public void setScoreSum(Double scoreSum) {
		this.scoreSum = scoreSum;
	}

	public Integer getPassNum() {
		return passNum;
	}

	public void setPassNum(Integer passNum) {
		this.passNum = passNum;
	}

	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}




}
