package com.zust.itee.exam.enums;

public enum DepartmentStatusEnum {
	DELETED((short) -1, "已删除"), DISABLE((short) 0, "已禁用"), ENABLE((short) 1,
			"已启用");

	private short status;

	private String info;

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

	private DepartmentStatusEnum(short status, String info) {
		this.status = status;
		this.info = info;
	}

	public static DepartmentStatusEnum stateOf(int index) {
		for (DepartmentStatusEnum e : values()) {
			if (e.getStatus() == index) {
				return e;
			}
		}
		return null;
	}
}
