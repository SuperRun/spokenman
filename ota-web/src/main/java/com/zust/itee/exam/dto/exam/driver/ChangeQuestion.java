package com.zust.itee.exam.dto.exam.driver;

import java.util.List;

/**
 * 驾驶员考试时切题需要传递的信息
 * @author sdy
 *
 */
public class ChangeQuestion {
	//当前题目的id
	private Integer questionIdNow;

	//当前题目类型
	private long questionTypeNow;

	//当前题目答案
	private String answer;

	//当前序号
	private Integer sequenceNow;

	//下一题题目序号
	private Integer sequenceNext;

	//是否可以提交答案
	private boolean submitable;

	//总题数
	private Long sum;

	//题目列表中某题是否答过
	private List<Boolean> sequenceStatus;

	//是否已交卷
	private boolean haveSubmit;

	public Integer getQuestionIdNow() {
		return questionIdNow;
	}

	public void setQuestionIdNow(Integer questionIdNow) {
		this.questionIdNow = questionIdNow;
	}

	public long getQuestionTypeNow() {
		return questionTypeNow;
	}

	public void setQuestionTypeNow(long questionTypeNow) {
		this.questionTypeNow = questionTypeNow;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getSequenceNow() {
		return sequenceNow;
	}

	public void setSequenceNow(Integer sequenceNow) {
		this.sequenceNow = sequenceNow;
	}

	public Integer getSequenceNext() {
		return sequenceNext;
	}

	public void setSequenceNext(Integer sequenceNext) {
		this.sequenceNext = sequenceNext;
	}



	public boolean isSubmitable() {
		return submitable;
	}

	public void setSubmitable(boolean submitable) {
		this.submitable = submitable;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}

	public List<Boolean> getSequenceStatus() {
		return sequenceStatus;
	}

	public void setSequenceStatus(List<Boolean> sequenceStatus) {
		this.sequenceStatus = sequenceStatus;
	}

	public boolean isHaveSubmit() {
		return haveSubmit;
	}

	public void setHaveSubmit(boolean haveSubmit) {
		this.haveSubmit = haveSubmit;
	}







}
