package com.zust.itee.exam.enums.exam;

/**
 * 题目难度
 * @author terry
 *
 */
public enum QuestionDifficultyEnum {

	EASY((short)1,"易"),
	MEDIUM((short)2,"中"),
	HARD((short)3,"难")
	;



	private Short id;
	private String info;


	private QuestionDifficultyEnum(Short id, String info) {
		this.id = id;
		this.info = info;
	}
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public static QuestionDifficultyEnum getIndexOf(Short index){
		for(QuestionDifficultyEnum questionDifficultyEnum: QuestionDifficultyEnum.values()){
			if(questionDifficultyEnum.getId()==index){
				return questionDifficultyEnum;
			}
		}
		return null;
	}


}
