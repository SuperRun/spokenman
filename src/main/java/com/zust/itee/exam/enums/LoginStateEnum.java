package com.zust.itee.exam.enums;

public enum LoginStateEnum {

	SUCCESS((short) 1, "登录成功"), ERROR_PASSWORD((short) -1, "密码错误"), ACCOUNT_NOT_EXSIT(
			(short) -2, "账户不存在"), UNKONW((short) -3, "未知错误");

	private short status;

	private String info;

	private LoginStateEnum(short status, String info) {
		this.status = status;
		this.info = info;
	}

	public static LoginStateEnum stateOf(int index) {
		for (LoginStateEnum e : values()) {
			if (e.getStatus() == index) {
				return e;
			}
		}
		return null;
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
