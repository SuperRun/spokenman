package com.zust.itee.exam.dto.exam.member.companyQuery;

/**
 * 车企查看考试安排所需考试详情
 * @author sdy
 *
 */
public class ExamDetailInCompanyQuery {

	//id
	private Integer id;

	//考试名称
	private String name;

	//报名开始时间
	private String signupStartTime;

	//报名结束时间
	private String signupEndTime;

	//考试开始时间
	private String startTime;

	//考试结束时间
	private String endTime;

	//发起组织名称
	private String org;

	//考试说明
	private String description;

	//试卷名称
	private String paperName;

	//试卷总题数
	private Integer sum;

	//试卷总分
	private Double score;

	//试卷及格题目数
	private Integer passNum;

	//试卷及格分
	private Integer passScore;

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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





}
