package com.zust.itee.exam.enums.exam;

/**
 * 题型枚举
 *
 * @author sdy
 *
 */
public enum QuestionTypeEnum {

	SINGLECHOICE("单选题", "单选题，请选择一个答案"), MULTIPLECHOICE("多选题", "多选题，可能需要选择多个选项"), TRURORFALSE(
			"判断题", "判断题，请判断对错"), GAPFILLINT("填空题", "填空题，请填写答案"), SHORTANSWER(
			"简单题", "简答题，请填写答案"),NONETYPE("","");

	private String valueStr;

	private String help;

	private QuestionTypeEnum(String valueStr, String help) {
		this.valueStr = valueStr;
		this.help = help;
	}

	public String getValueStr() {
		return valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public static QuestionTypeEnum getIndexOf(String valueStr){
		for(QuestionTypeEnum questionTypeEnum: QuestionTypeEnum.values()){
			if(questionTypeEnum.getValueStr().equals(valueStr)){
				return questionTypeEnum;
			}
		}
		return QuestionTypeEnum.NONETYPE;
	}

}
