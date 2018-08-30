package com.zust.itee.exam.dto.exam.member;

import java.util.List;

/**
 * 组卷时显示题型
 * @author sdy
 *
 */
public class PaperQuestionType {

	//题型id
	private Long id;

	//题型名称
	private String name;

	//该题型题目总数
	private Long sum;

	//该题型已选题目数
	private Integer count;

	//该题型分数
	private double score;

	//该题型知识点
	private List<PaperQuestionSubject> subjects;

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

	public List<PaperQuestionSubject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<PaperQuestionSubject> subjects) {
		this.subjects = subjects;
	}


	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PaperQuestionType [id=" + id + ", name=" + name + ", sum="
				+ sum + ", count=" + count + ", score=" + score + ", subjects="
				+ subjects + "]";
	}





}
