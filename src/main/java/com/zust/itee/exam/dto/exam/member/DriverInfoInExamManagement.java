package com.zust.itee.exam.dto.exam.member;

/**
 * 工作人员考试管理列表内容dto
 * @author sdy
 *
 */
public class DriverInfoInExamManagement {
	//驾驶员id
	private Integer driverId;

	//驾驶员姓名
	private String name;

	//驾驶员联系方式
	private String tel;

	//所在车企名称
	private String orgName;

	//散装办名称
	private String orgParentName;

	//证书号
	private String certificateNo;

	//证书
	private String certificate;

	//考试成绩
	private Double score;

	//成绩是否可以编辑
	private Boolean scoreEdit;

	//证书是否可以编辑
	private Boolean certificateEdit;

	//参加考试时间
	private String presentTime;

	//报名时间
	private String signupTime;

	//身份证号
	private String sfzNo;

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}



	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Boolean getScoreEdit() {
		return scoreEdit;
	}

	public void setScoreEdit(Boolean scoreEdit) {
		this.scoreEdit = scoreEdit;
	}

	public Boolean getCertificateEdit() {
		return certificateEdit;
	}

	public void setCertificateEdit(Boolean certificateEdit) {
		this.certificateEdit = certificateEdit;
	}

	public String getPresentTime() {
		return presentTime;
	}

	public void setPresentTime(String presentTime) {
		this.presentTime = presentTime;
	}

	public String getSignupTime() {
		return signupTime;
	}

	public void setSignupTime(String signupTime) {
		this.signupTime = signupTime;
	}


	public String getSfzNo() {
		return sfzNo;
	}

	public void setSfzNo(String sfzNo) {
		this.sfzNo = sfzNo;
	}

	@Override
	public String toString() {
		return "DriverInfoInExamManagement [driverId=" + driverId + ", name="
				+ name + ", tel=" + tel + ", orgName=" + orgName
				+ ", orgParentName=" + orgParentName + ", certificateNo="
				+ certificateNo + ", certificate=" + certificate + ", score="
				+ score + ", scoreEdit=" + scoreEdit + ", certificateEdit="
				+ certificateEdit + ", presentTime=" + presentTime
				+ ", signupTime=" + signupTime + "]";
	}




}
