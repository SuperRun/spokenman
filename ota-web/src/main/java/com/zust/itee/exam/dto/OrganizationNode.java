package com.zust.itee.exam.dto;

/**
 * 树状显示组织机构的节点
 *
 * @author dxb
 *
 * @date 2016年8月16日
 *
 */
public class OrganizationNode {

	private Integer id;

	private String name;

	private String shortName;

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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
