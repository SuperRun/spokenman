package com.zust.itee.exam.enums;

/**
 * 理赔状态枚举
 *
 * @author sdy
 *
 */
public enum InsuranceCompensationStatusEnum {
	DELETED((short) -1, "已删除"), CHECKPENDING((short) 0, "待审核"), AVALIABLE(
			(short) 1, "可用");

	// 状态码
	private Short status;
	// 描述
	private String info;

	private InsuranceCompensationStatusEnum(Short status, String info) {
		this.status = status;
		this.info = info;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
