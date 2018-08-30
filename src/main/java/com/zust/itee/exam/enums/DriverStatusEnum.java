package com.zust.itee.exam.enums;

public enum DriverStatusEnum {

//	UNAVAILABLE((short)-3,""),
	QUIT((short)-2,"离职"),
	DELETED((short)-1,"已删除"),
	CHECKING((short)0,"待审核"),
	AVAILABLE((short)1,"驾驶员"),
	NEEDTTRAINED((short)2,"待培训"),  //证书录入后变为驾驶员
	RENEEDTTRAINED((short)3,"重新培训"); //比如撞死人了

	private Short status;

	private String info;



	private DriverStatusEnum(Short status, String info) {
		this.status = status;
		this.info = info;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}



}
