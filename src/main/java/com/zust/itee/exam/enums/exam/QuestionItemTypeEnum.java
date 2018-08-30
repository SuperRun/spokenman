package com.zust.itee.exam.enums.exam;

public enum QuestionItemTypeEnum {
	FONT("font", "文字选项"), PICTURE("picture", "图片选项"), NONE("none", "无选项");
	private String name;
	private String info;

	private QuestionItemTypeEnum(String name, String info) {
		this.name = name;
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
