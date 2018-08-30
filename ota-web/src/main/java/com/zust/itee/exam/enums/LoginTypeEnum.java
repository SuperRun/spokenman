package com.zust.itee.exam.enums;

public enum LoginTypeEnum {

	DRIVER((short) 1, "考生"),
	MEMBER((short)0,"员工"),
	;

	private short type;

	private String info;

	private LoginTypeEnum(short type, String info) {
		this.type = type;
		this.info = info;
	}

	public static LoginTypeEnum stateOf(short index) {
		for (LoginTypeEnum e : values()) {
			if (e.getType() == index) {
				return e;
			}
		}
		return null;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
