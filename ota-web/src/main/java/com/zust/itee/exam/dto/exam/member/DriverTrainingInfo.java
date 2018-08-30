package com.zust.itee.exam.dto.exam.member;

/**
 * 培训列表中驾驶员信息
 *
 * @author sdy
 *
 */
public class DriverTrainingInfo {
	// 驾驶员id
	private Integer id;

	// 姓名
	private String name;

	// 驾驶员联系方式
	private String tel;

	// 所在车企名称
	private String orgName;

	// 散装办名称
	private String orgParentName;

	//状态
	private Short status;

	//状态描述
	private String statusInfo;

	//身份证号
	private String sfzNo;

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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}


	public String getSfzNo() {
		return sfzNo;
	}

	public void setSfzNo(String sfzNo) {
		this.sfzNo = sfzNo;
	}

	@Override
	public String toString() {
		return "DriverTrainingInfo [id=" + id + ", name=" + name + ", tel="
				+ tel + ", orgName=" + orgName + ", orgParentName="
				+ orgParentName + ", status=" + status + ", statusInfo="
				+ statusInfo + "]";
	}







}
