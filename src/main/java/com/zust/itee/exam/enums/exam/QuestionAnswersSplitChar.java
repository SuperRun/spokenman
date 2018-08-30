package com.zust.itee.exam.enums.exam;

/**
 * 试题多个答案分隔符
 * @author sdy
 *
 */
public enum QuestionAnswersSplitChar {

	AND("&&","多选题用多个答案需同时满足"), //数据库存储格式:&&answer1&&answer2&&answer3……
	OR("##","填空题用多个答案只需要满足一个");
	private String splitChar;

	private String info;

	private QuestionAnswersSplitChar(String splitChar, String info) {
		this.splitChar = splitChar;
		this.info = info;
	}

	public String getSplitChar() {
		return splitChar;
	}

	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
