package com.zust.itee.exam.dto.exam.member;

/**
 * 组卷时显示知识点
 * @author sdy
 *
 */
public class PaperQuestionSubject {
	//知识点id
	private Long id;

	//知识点名称
	private String name;

	//题目个数
	private Long sum;

	//原先试卷已选题目个数
	private Integer count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}



	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PaperQuestionSubject [id=" + id + ", name=" + name + ", sum="
				+ sum + ", count=" + count + "]";
	}



}
