package com.zust.itee.exam.enums;

public enum DataDictionaryEnum {
	QUESTIONTYPEROOR(0, "题目题型"), QUESTIONSUBJECTROOT(0, "题目知识点"), ROOTDATA(0,
			"父节点"), SUBDATA(1, "子节点"), TRAFFICVIOLATIONTYPEROOR(0, "交通违章类型");

	// 数据字典等级
	private Integer level;

	// 具体描述
	private String valueStr;

	private DataDictionaryEnum(Integer level, String valueStr) {
		this.level = level;
		this.valueStr = valueStr;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getValueStr() {
		return valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

}
