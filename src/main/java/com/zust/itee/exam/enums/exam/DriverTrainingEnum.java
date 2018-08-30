package com.zust.itee.exam.enums.exam;

/**
 * 驾驶员参加培训状态
 *
 * @author sdy
 *
 */
public enum DriverTrainingEnum {

	CANCELED((short) -1, "已取消"), PASS((short) 1, "通过"), FAILED((short) 2, "不通过"), ABSENT(
			(short) 3, "缺席"), SIGNUPED((short) 0, "已报名");

	private short status;

	private String info;

	private DriverTrainingEnum(short status, String info) {
		this.status = status;
		this.info = info;
	}

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

	public static DriverTrainingEnum getIndexOf(short status){
		for(DriverTrainingEnum driverTrainingEnum: DriverTrainingEnum.values()){
			if(driverTrainingEnum.getStatus()==status){
				return driverTrainingEnum;
			}
		}
		return null;
	}

}
