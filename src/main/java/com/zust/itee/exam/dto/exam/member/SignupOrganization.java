package com.zust.itee.exam.dto.exam.member;

import java.util.List;

/**
 * 批量导入时显示车企名
 * @author terry
 *
 */
public class SignupOrganization {
	//车企id
	private Integer id;

	//车企名
	private String name;

	//车企内驾驶员
	private List<SignupDriver> drivers;

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

	public List<SignupDriver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<SignupDriver> drivers) {
		this.drivers = drivers;
	}

	@Override
	public String toString() {
		return "SignupOrganization [id=" + id + ", name=" + name + ", drivers="
				+ drivers + "]";
	}








}
