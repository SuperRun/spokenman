package com.zust.itee.exam.enums.exam;

public enum ExamTypeEnum {
	OFFICIALONLINE((short)0,"线上正式考试"),

	PRACTICE((short)1,"模拟考试"),

	OFFICIALOFFLINE((short)2,"线下正式考试");

	//type id
	private short id;

	//type 描述
	private String info;

	private ExamTypeEnum(short id, String info) {
		this.id = id;
		this.info = info;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ExamTypeEnum getIndexOf(short id){
		for(ExamTypeEnum examTypeEnum: ExamTypeEnum.values()){
			if(examTypeEnum.getId()==id){
				return examTypeEnum;
			}
		}
		return null;
	}

}
