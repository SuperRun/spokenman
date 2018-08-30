package com.zust.itee.exam.dto.exam.member.companyQuery;

/**
 * 车企查询考试、培训安排时查看驾驶员参加详情
 * @author sdy
 *
 */
public class DriverExamDetailInfoInCompanyQuery {
	//驾驶员id
	private Integer id;
	//报名状态
	private String signupInfo;
	//报名时间
	private String signupTime;
	//参加考试状态
	private String takeExamStatus;
	//考试成绩
	private Double score;
	//考试答对题目数量
	private Integer num;
	//是否通过
	private Boolean ifPass;
	//证书号
	private String certificateNo;
	//证书照片
	private String certificate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSignupInfo() {
		return signupInfo;
	}
	public void setSignupInfo(String signupInfo) {
		this.signupInfo = signupInfo;
	}
	public String getSignupTime() {
		return signupTime;
	}
	public void setSignupTime(String signupTime) {
		this.signupTime = signupTime;
	}
	public String getTakeExamStatus() {
		return takeExamStatus;
	}
	public void setTakeExamStatus(String takeExamStatus) {
		this.takeExamStatus = takeExamStatus;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getIfPass() {
		return ifPass;
	}
	public void setIfPass(Boolean ifPass) {
		this.ifPass = ifPass;
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
