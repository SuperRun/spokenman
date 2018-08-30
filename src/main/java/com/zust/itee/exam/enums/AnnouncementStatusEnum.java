package com.zust.itee.exam.enums;

/**
 * 公告状态枚举类
 * @author liy
 *
 */
public enum AnnouncementStatusEnum {

	OVERDUE((short)-1,"已过期"),
	DELETE((short)0, "删除"),
	AVAILABLE((short)1,"有效")

	;
	private AnnouncementStatusEnum(short status, String info) {
		this.status = status;
		this.info = info;
	}
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

	public static AnnouncementStatusEnum stateOf(int index) {
		for (AnnouncementStatusEnum e : values()) {
			if (e.getStatus() == index) {
				return e;
			}
		}
		return null;
	}


}
