package com.zust.itee.exam.enums.exam;

/**
 *考试状态本身
 * @author sdy
 *
 */
public enum ExamStatusEnum {
	CANCELED((short)-1,"已取消"),
	RELEASE((short)0,"已发布"),
	SIGNUP((short)1,"报名中"),
	WAITINGEXAM((short)2,"待考试"),
	EXAMING((short)3,"考试中"),
	MARKING((short)4,"阅卷中"),
	CERTIFICATING((short)5,"证书录入中"),
	FINISHED((short)6,"已完成")
	;
	private short state;
	private String stateInfo;

	private ExamStatusEnum(short state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
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

	public static ExamStatusEnum getStateInfoOf(short state){
		for(ExamStatusEnum examStatusEnum: ExamStatusEnum.values()){
			if(examStatusEnum.getState()==state){
				return examStatusEnum;
			}
		}
		return null;
	}


}
