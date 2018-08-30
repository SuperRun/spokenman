package com.zust.itee.exam.dto.exam.member;

/**
 *培训关联考试时显示的考试信息
 * @author sdy
 *
 */
public class ExamInfoForTraining {
	//考试id
	private Integer id;
	//考试名称
	private String name;
	//发起组织
	private String org;
	//考试开始时间
	private String examStartTime;
	//考试结束时间
	private String examEndTime;
	//试卷名称
	private String paperName;
	//总题数
	private Integer sum;
	//总分
	private Double score;
	//及格分
	private Integer passScore;
	//及格题目数
	private Integer passNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
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
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getPassScore() {
		return passScore;
	}
	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}
	public Integer getPassNum() {
		return passNum;
	}
	public void setPassNum(Integer passNum) {
		this.passNum = passNum;
	}
	@Override
	public String toString() {
		return "ExamInfoForTraining [id=" + id + ", name=" + name + ", org="
				+ org + ", examStartTime=" + examStartTime + ", examEndTime="
				+ examEndTime + ", paperName=" + paperName + ", sum=" + sum
				+ ", score=" + score + ", passScore=" + passScore
				+ ", passNum=" + passNum + "]";
	}









}
