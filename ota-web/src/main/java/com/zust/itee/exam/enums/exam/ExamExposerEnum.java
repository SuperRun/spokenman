package com.zust.itee.exam.enums.exam;

/**
 * 驾驶员报名考试列表中考试状态枚举
 * @author sdy
 *
 */
public enum ExamExposerEnum {
	//其他组织部门的考试
	CANTSIGNUP((short)-2,"无报名权限"),
	ENDSIGNUP((short)-1,"报名结束"),
	BEFORESIGNUP((short)0,"未开始报名"),
	INSIGNUP((short)1,"可报名"),
	HAVESIGNUPCANCANCEL((short)2,"已报名且可取消"),
	HAVESIGNUPCANTCANCEL((short)3,"已报名且不可取消")

	;

	//状态码
	private short state;
	//状态详情
	private String stateInfo;



	private ExamExposerEnum(short state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}



}
