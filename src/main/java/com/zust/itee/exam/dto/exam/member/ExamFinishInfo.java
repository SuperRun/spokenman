package com.zust.itee.exam.dto.exam.member;

import java.util.List;

/**
 * 已完成考试考试情况
 *
 * @author sdy
 *
 */
public class ExamFinishInfo {
	// 考试id
	private Integer examId;
	// 考试名称
	private String examName;
	// 发布组织
	private String organization;
	// 发布时间
	private String createTime;
	// 报名人数
	private Integer signupNum;
	// 参加人数
	private Integer presentNum;
	// 通过人数
	private Integer passNum;

	//报名人员
	private List<DriverInfoInExamManagement> signupDrivers;

	// 参加人员
	private List<DriverInfoInExamManagement> presentDrivers;
	// 通过人员
	private List<DriverInfoInExamManagement> passDrivers;

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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getSignupNum() {
		return signupNum;
	}

	public void setSignupNum(Integer signupNum) {
		this.signupNum = signupNum;
	}

	public Integer getPresentNum() {
		return presentNum;
	}

	public void setPresentNum(Integer presentNum) {
		this.presentNum = presentNum;
	}

	public Integer getPassNum() {
		return passNum;
	}

	public void setPassNum(Integer passNum) {
		this.passNum = passNum;
	}

	public List<DriverInfoInExamManagement> getPresentDrivers() {
		return presentDrivers;
	}

	public void setPresentDrivers(
			List<DriverInfoInExamManagement> presentDrivers) {
		this.presentDrivers = presentDrivers;
	}

	public List<DriverInfoInExamManagement> getPassDrivers() {
		return passDrivers;
	}

	public void setPassDrivers(List<DriverInfoInExamManagement> passDrivers) {
		this.passDrivers = passDrivers;
	}

	public List<DriverInfoInExamManagement> getSignupDrivers() {
		return signupDrivers;
	}

	public void setSignupDrivers(List<DriverInfoInExamManagement> signupDrivers) {
		this.signupDrivers = signupDrivers;
	}




}
