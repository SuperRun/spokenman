package com.zust.itee.exam.dto.exam.member;

import java.util.List;

/**
 * 创建考试时选取已有试卷，试卷展示详细信息
 *
 * @author sdy
 *
 */
public class PaperInfoForExam {
	// paperId
	private Integer id;

	// 试卷名称
	private String name;

	//试卷描述
	private String description;

	//试卷类型描述
	private String type;

	// 发布组织名称
	private String orgName;

	// 发布时间
	private String createTime;

	// 总题目数
	private Integer questionSum;

	// 总分
	private double scoreSum;

	// 及格题目数
	private Integer passNum;

	// 及格分
	private Integer passScore;

	//设计样式
	private List<PaperDesign> paperDesigns;

	//具体题目
	private List<QuestionModel> questions;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getQuestionSum() {
		return questionSum;
	}

	public void setQuestionSum(Integer questionSum) {
		this.questionSum = questionSum;
	}

	public double getScoreSum() {
		return scoreSum;
	}

	public void setScoreSum(double scoreSum) {
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

	public List<PaperDesign> getPaperDesigns() {
		return paperDesigns;
	}

	public void setPaperDesigns(List<PaperDesign> paperDesigns) {
		this.paperDesigns = paperDesigns;
	}

	public List<QuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "PaperInfoForExam [id=" + id + ", name=" + name
				+ ", description=" + description + ", type=" + type
				+ ", orgName=" + orgName + ", createTime=" + createTime
				+ ", questionSum=" + questionSum + ", scoreSum=" + scoreSum
				+ ", passNum=" + passNum + ", passScore=" + passScore
				+ ", paperDesigns=" + paperDesigns + ", questions=" + questions
				+ "]";
	}




}
