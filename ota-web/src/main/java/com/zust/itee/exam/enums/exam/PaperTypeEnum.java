package com.zust.itee.exam.enums.exam;

public enum PaperTypeEnum {

	RAMDOM((short) 1, "预设试卷"), PRESET((short) 0, "随机生成");

	private short type;

	private String info;

	private PaperTypeEnum(short type, String info) {
		this.type = type;
		this.info = info;
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

	public static PaperTypeEnum stateOf(int index) {
		for (PaperTypeEnum e : values()) {
			if (e.getType() == index) {
				return e;
			}
		}
		return null;
	}

}
