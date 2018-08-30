package com.zust.itee.exam.enums;

/**
 *
 * @author sdy
 *系统生成的excel文件模板路径
 */
public enum OutputFilePathEnmu {

	SCOREEXCEL("resources/excel/exam/member/score/","成绩录入批量导入模板"),
	CERTIFICATEEXCEL("resources/excel/exam/member/certificate/","证书号批量导入模板"),
	QUESTIONEXCEL("resources/excel/exam/member/question/","题目批量导入模板");
	//路径
	private String path;

	//说明
	private String info;

	private OutputFilePathEnmu(String path, String info) {
		this.path = path;
		this.info = info;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}




}
