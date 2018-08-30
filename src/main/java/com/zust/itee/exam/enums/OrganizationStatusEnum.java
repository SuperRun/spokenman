package com.zust.itee.exam.enums;

public enum OrganizationStatusEnum {

	DISABLE((short) -1, "禁用"), UNVERIFY((short) 0, "待审核"), ENABLE((short) 1,
			"启用"),FALIEDVERIFY((short)-2,"审核失败")
	;

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

	private OrganizationStatusEnum(short status, String info) {
		this.status = status;
		this.info = info;
	}

	public static OrganizationStatusEnum stateOf(int index) {
		for (OrganizationStatusEnum e : values()) {
			if (e.getStatus() == index) {
				return e;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "OrganizationStatusEnum.toString{" +
				"info='" + info + '\'' +
				", status=" + status +
				'}';
	}
}
