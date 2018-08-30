package com.zust.itee.exam.enums;

public enum DriverWorkHistoryStatus {

//	UNAVAILABLE((short)-3,""),
WORKING((short) -2, "正常"),
	DELETED((short)-1,"已删除"),
	CHECKING((short)0,"待审核"),
	AVAILABLE((short)1,"可用");

	private Short status;

	private String info;



	private DriverWorkHistoryStatus(Short status, String info) {
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
