package com.zust.itee.exam.dto.exam.member;
/**
 * 组卷设计dto
 * @author sdy
 *
 */
public class PaperDesign {
	//知识点id
	private long subjectId;

	//题型id
	private long typeId;

	//数目
	private int num;

	//题型名称
	private String typeName;

	//知识点名称
	private String subjectName;


	public PaperDesign() {
		super();
	}


	public PaperDesign(long subjectId, long typeId, int num) {
		super();
		this.subjectId = subjectId;
		this.typeId = typeId;
		this.num = num;
	}


	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}



}
