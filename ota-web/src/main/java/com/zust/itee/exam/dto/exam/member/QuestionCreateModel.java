package com.zust.itee.exam.dto.exam.member;

/**
 *
 * @author sdy 创建题目所需信息
 *
 */
public class QuestionCreateModel {
	//id
	private Integer id;

	// 题干
	private String content;

	// 图片
	private String pic1;

	private String pic2;

	private String pic3;

	// 题目类型名
	private long typeId;

	// 题目知识点名
	private long subjectId;

	// 题目分数
	private double marks;

	// 题目答案
	private String answer;

	// 是否为选择题
	private boolean select;

	//难度
	private short difficulty;

	//新题型描述
	private String newType;

	//新知识点描述
	private String newSubject;

	//选项类型(font:文字；picture：图片)
	private String questionItemsType;

	//文字选项源string:选项1；选项2；选项3；……
	private String questionItemsContentStr;

	//图片选项url源string：图片1；图片2；……
	private String questionItemsPicStr;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public short getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(short difficulty) {
		this.difficulty = difficulty;
	}

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	public String getNewSubject() {
		return newSubject;
	}

	public void setNewSubject(String newSubject) {
		this.newSubject = newSubject;
	}

	public String getQuestionItemsType() {
		return questionItemsType;
	}

	public void setQuestionItemsType(String questionItemsType) {
		this.questionItemsType = questionItemsType;
	}

	public String getQuestionItemsContentStr() {
		return questionItemsContentStr;
	}

	public void setQuestionItemsContentStr(String questionItemsContentStr) {
		this.questionItemsContentStr = questionItemsContentStr;
	}

	public String getQuestionItemsPicStr() {
		return questionItemsPicStr;
	}

	public void setQuestionItemsPicStr(String questionItemsPicStr) {
		this.questionItemsPicStr = questionItemsPicStr;
	}


}
