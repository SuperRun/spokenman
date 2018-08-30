package com.zust.itee.exam.enums;
/**
 *
 * @author terry
 * 性别
 *
 */
public enum SexEnum {

	FEMALE((short)0,"女"),
	MALE((short)1,"男");
	private short id;
	private String info;


	private SexEnum(short id, String info) {
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

	public static SexEnum getIndexOf(Short index){
		for(SexEnum sexEnum: SexEnum.values()){
			if(sexEnum.getId()==index){
				return sexEnum;
			}
		}
		return null;
	}


}
