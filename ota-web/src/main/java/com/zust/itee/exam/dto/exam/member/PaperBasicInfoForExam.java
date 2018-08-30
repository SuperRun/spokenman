package com.zust.itee.exam.dto.exam.member;

/**
 * 创建考试时选取已有试卷，试卷列表展示基本信息
 *
 * @author terry
 *
 */
public class PaperBasicInfoForExam {
	// paperId
	private Integer id;

	// 试卷名称
	private String name;

	// 试卷描述
	private String description;

	// 试卷类型描述
	private String type;

	// 发布组织名称
	private String orgName;

	// 发布时间
	private String createTime;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}





}
