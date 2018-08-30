package com.zust.itee.exam.enums.star;

/**
 * 星级评比类别枚举
 *
 * @author terry
 *
 */
public enum StarRuleTypeEnum {
	RATE((long) 654, "当年理赔率"), RATEAVG((long) 655, "连续两年理赔率"), INJURECOUNT(
			(long) 656, "有责任伤人事故次数"), INJURE2COUNT((long) 657, "有责任伤2人以上事故次数"), DEADCOUNT(
			(long) 658, "有责任死亡事故次数");
	// 数据字典里的id
	private Long id;
	// 数据字典了valueStr
	private String valueStr;

	private StarRuleTypeEnum(Long id, String valueStr) {
		this.id = id;
		this.valueStr = valueStr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValueStr() {
		return valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

}
