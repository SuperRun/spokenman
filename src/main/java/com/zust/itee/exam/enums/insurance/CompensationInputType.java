package com.zust.itee.exam.enums.insurance;

/**
 * 理赔录入类型
 * @author sdy
 *
 */
public enum CompensationInputType {

	COMPANY("车企上报"),
	SYSTERM("系统导入");

	private String info;

	private CompensationInputType(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}





}
