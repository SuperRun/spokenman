package com.zust.itee.exam.enums.exam;

public enum ExamPartEntityStatusEnum {

	CANCELED((short)-1,"已删除"),
	AVAILABLE((short)1,"可用");

	private Short state;

	private String info;

	private ExamPartEntityStatusEnum(Short state, String info) {
		this.state = state;
		this.info = info;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static ExamPartEntityStatusEnum getStateInfoOf(short idx){
		for(ExamPartEntityStatusEnum examPartEntityStatusEnum: ExamPartEntityStatusEnum.values()){
			if(examPartEntityStatusEnum.getState()==idx){
				return examPartEntityStatusEnum;
			}
		}
		return null;
	}




}
