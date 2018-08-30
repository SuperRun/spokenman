package com.zust.itee.exam.dto.exam.driver;

/**
 * 考试已完成驾驶员信息
 *
 * @author terry
 *
 */
public class ExamFinishDriverInfo {
	// 驾驶员id
	private Integer driverId;

	// 驾驶员姓名
	private String driverName;

	// 考试名称
	private String examName;

	// 组织名称
	private String orgName;

	// 考试通过分数
	private Integer passScore;

	// 考试通过题目数量
	private Integer passNum;

	// 驾驶员总分
	private Double finalScore;

	// 驾驶员总正确题目数
	private Integer finalNum;

	// 证书号
	private String certificateNo;

	// 证书照片
	private String certificatePhoto;


	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public Double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Double finalScore) {
		this.finalScore = finalScore;
	}

	public Integer getFinalNum() {
		return finalNum;
	}

	public void setFinalNum(Integer finalNum) {
		this.finalNum = finalNum;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getCertificatePhoto() {
		return certificatePhoto;
	}

	public void setCertificatePhoto(String certificatePhoto) {
		this.certificatePhoto = certificatePhoto;
	}

}
