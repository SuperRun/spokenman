package com.zust.itee.exam.dto.exam.member;

/**
 * 接收组卷试卷基本信息
 *
 * @author sdy
 *
 */
public class PaperBaseInfo {

	// 试卷id
	private Integer paperId;

	// 试卷名称
	private String paperName;

	// 试卷描述
	private String paperDescription;

	// 试卷类型,0一人一卷，1众人同卷
	private short paperType;

	// 简单占比
	private double easyPercent;

	// 中等占比
	private double mediumPercent;

	// 困难占比
	private double hardPercent;

	// paperDesign转化的string
	private String paperDesignString;

	// 及格题目数
	private Integer passNum;

	// 及格分
	private Integer passScore;

	// 编辑考试时难度slide开始数值
	private Integer slideStart;

	// 编辑考试时难度slide结束数值
	private Integer slideEnd;

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getPaperDescription() {
		return paperDescription;
	}

	public void setPaperDescription(String paperDescription) {
		this.paperDescription = paperDescription;
	}

	public short getPaperType() {
		return paperType;
	}

	public void setPaperType(short paperType) {
		this.paperType = paperType;
	}

	public double getEasyPercent() {
		return easyPercent;
	}

	public void setEasyPercent(double easyPercent) {
		this.easyPercent = easyPercent;
	}

	public double getMediumPercent() {
		return mediumPercent;
	}

	public void setMediumPercent(double mediumPercent) {
		this.mediumPercent = mediumPercent;
	}

	public double getHardPercent() {
		return hardPercent;
	}

	public void setHardPercent(double hardPercent) {
		this.hardPercent = hardPercent;
	}

	public String getPaperDesignString() {
		return paperDesignString;
	}

	public void setPaperDesignString(String paperDesignString) {
		this.paperDesignString = paperDesignString;
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

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getSlideStart() {
		return slideStart;
	}

	public void setSlideStart(Integer slideStart) {
		this.slideStart = slideStart;
	}

	public Integer getSlideEnd() {
		return slideEnd;
	}

	public void setSlideEnd(Integer slideEnd) {
		this.slideEnd = slideEnd;
	}


}
