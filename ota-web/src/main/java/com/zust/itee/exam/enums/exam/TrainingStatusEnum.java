package com.zust.itee.exam.enums.exam;

/**
 * 培训本身状态枚举
 *
 * @author terry
 *
 */
public enum TrainingStatusEnum {

	CANCELED((short) -1, "已取消"), RELEASED((short) 0, "已发布"), SIGNUP((short) 4,
			"报名中"), WAITINGTRAINING((short) 5, "待培训"), TRAINING((short) 1,
			"培训中"), CONFIRMING((short) 2, "确认中"), COMPLETED((short) 3, "已完成");

	private Short status;

	private String info;

	private TrainingStatusEnum(Short status, String info) {
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

	public static TrainingStatusEnum getIndexOf(Short status) {
		for (TrainingStatusEnum trainingStatusEnum : TrainingStatusEnum
				.values()) {
			if (trainingStatusEnum.getStatus() == status) {
				return trainingStatusEnum;
			}
		}
		return null;
	}

}
