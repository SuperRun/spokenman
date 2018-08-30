package com.zust.itee.exam.enums.star;

/**
 * 星级模块数据字典相关枚举
 * @author sdy
 *
 */
public enum StarPartDataDictionaryEnum {

	TRAFFICVIOLATIONROOT(0,"交通违章类型");
	// 数据字典等级
	private Integer level;

	// 具体描述
	private String valueStr;

	private StarPartDataDictionaryEnum(Integer level, String valueStr) {
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
