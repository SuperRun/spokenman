package com.zust.itee.exam.dto.exam;

import java.util.List;

import com.zust.itee.exam.dto.exam.member.PaperDesign;

/**
 * 模态框模拟考试信息
 * @author sdy
 *
 */
public class PracticeExamInfo {
	//id
	private Integer id;

	//名称
	private String name;

	//时长
	private Integer duration;

	//发布单位
	private String orgName;

	//总题目数
	private Integer questionSum;

	//总分
	private Double scoreSum;

	//及格题目数
	private Integer passNum;

	//及格分
	private Integer passScore;

	//备注
	private String description;

	//组卷设计
	private List<PaperDesign> paperDesigns;

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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PaperDesign> getPaperDesigns() {
		return paperDesigns;
	}

	public void setPaperDesigns(List<PaperDesign> paperDesigns) {
		this.paperDesigns = paperDesigns;
	}



}
