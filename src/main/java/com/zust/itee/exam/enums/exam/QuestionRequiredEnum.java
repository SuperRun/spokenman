package com.zust.itee.exam.enums.exam;

public enum QuestionRequiredEnum {

	REQUIRED((short)1,"必选"),
	NOTREQUIRED((short)0,"非必选");

	private short required;
	private String info;

	private QuestionRequiredEnum(short required, String info) {
		this.required = required;
		this.info = info;
	}

	public short getRequired() {
		return required;
	}

	public void setRequired(short required) {
		this.required = required;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static QuestionRequiredEnum getIndexOf(Short id){
		for(QuestionRequiredEnum questionRequiredEnum: QuestionRequiredEnum.values()){
			if(questionRequiredEnum.getRequired()==id){
				return questionRequiredEnum;
			}
		}
		return null;
	}





}
