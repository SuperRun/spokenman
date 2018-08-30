package com.zust.itee.exam.dto.exam.member;

import java.util.List;

/**
 * 已完成培训显示信息
 *
 * @author sdy
 *
 */
public class TrainingCompletedInfo {
	// 培训id
	private Integer id;

	// 培训名称
	private String name;

	// 培训描述
	private String description;

	//发起组织
	private String org;

	// 培训开始时间
	private String startTime;

	// 培训结束时间
	private String endTime;

	// 培训关联考试
	private String examName;

	//报名人数
	private Integer signupNum;

	//报名驾驶员
	private List<DriverInfoInExamManagement> signupDrivers;

	//通过人数
	private Integer passNum;

	//通过驾驶员
	private List<DriverInfoInExamManagement> passDrivers;

	//未通过人数
	private Integer failNum;

	//为通过驾驶员
	private List<DriverInfoInExamManagement> failDrivers;

	//缺席人数
	private Integer absentNum;

	//缺席驾驶员
	private List<DriverInfoInExamManagement> absentDrivers;

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

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getPassNum() {
		return passNum;
	}

	public void setPassNum(Integer passNum) {
		this.passNum = passNum;
	}

	public List<DriverInfoInExamManagement> getPassDrivers() {
		return passDrivers;
	}

	public void setPassDrivers(List<DriverInfoInExamManagement> passDrivers) {
		this.passDrivers = passDrivers;
	}

	public Integer getFailNum() {
		return failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	public List<DriverInfoInExamManagement> getFailDrivers() {
		return failDrivers;
	}

	public void setFailDrivers(List<DriverInfoInExamManagement> failDrivers) {
		this.failDrivers = failDrivers;
	}

	public Integer getAbsentNum() {
		return absentNum;
	}

	public void setAbsentNum(Integer absentNum) {
		this.absentNum = absentNum;
	}

	public List<DriverInfoInExamManagement> getAbsentDrivers() {
		return absentDrivers;
	}

	public void setAbsentDrivers(List<DriverInfoInExamManagement> absentDrivers) {
		this.absentDrivers = absentDrivers;
	}

	public Integer getSignupNum() {
		return signupNum;
	}

	public void setSignupNum(Integer signupNum) {
		this.signupNum = signupNum;
	}

	public List<DriverInfoInExamManagement> getSignupDrivers() {
		return signupDrivers;
	}

	public void setSignupDrivers(List<DriverInfoInExamManagement> signupDrivers) {
		this.signupDrivers = signupDrivers;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return "TrainingCompletedInfo [id=" + id + ", name=" + name
				+ ", description=" + description + ", org=" + org
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", examName=" + examName + ", signupNum=" + signupNum
				+ ", signupDrivers=" + signupDrivers + ", passNum=" + passNum
				+ ", passDrivers=" + passDrivers + ", failNum=" + failNum
				+ ", failDrivers=" + failDrivers + ", absentNum=" + absentNum
				+ ", absentDrivers=" + absentDrivers + "]";
	}





}
