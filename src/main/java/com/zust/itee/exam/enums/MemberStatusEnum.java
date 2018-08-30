package com.zust.itee.exam.enums;

/**
 * 员工状态枚举类
 * @author dxb
 *
 */
public enum MemberStatusEnum {
	NORMAL((short) 1, "正常"),
	DISMISS((short) 0, "已离职"),
	DELETE((short)-1,"已删除"),
	;
	private short state;
	private String stateInfo;

	private MemberStatusEnum(short state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static MemberStatusEnum stateOf(short index) {
		for (MemberStatusEnum e : values()) {
			if (e.getState() == index) {
				return e;
			}
		}
		return null;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

}
