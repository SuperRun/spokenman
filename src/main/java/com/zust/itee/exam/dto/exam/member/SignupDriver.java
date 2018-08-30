package com.zust.itee.exam.dto.exam.member;

/**
 * 报名批量导入驾驶员显示信息
 * @author terry
 *
 */
public class SignupDriver {

	//驾驶员id
	private Integer id;

	//驾驶员姓名
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SignupDriver [id=" + id + ", name=" + name + "]";
	}





}
