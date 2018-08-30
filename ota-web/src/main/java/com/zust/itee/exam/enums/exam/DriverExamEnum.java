package com.zust.itee.exam.enums.exam;

public enum DriverExamEnum {

	CANCELED((short)-1,"已取消"),

	SIGNUPD((short) 1, "已报名"),
	EXAMED((short) 2, "已考试"),
	SUBMITED((short)3,"已交卷"),
	SCORED((short)4,"成绩已提交"),
	FINISHED((short)5,"已完成");


	private short status;

	private String info;

	private DriverExamEnum(short status, String info) {
		this.status = status;
		this.info = info;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
