package com.zust.itee.exam.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TpaperDesign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tpaper_design")
public class TpaperDesign implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = -5819209535707933898L;
	private Integer id;
	private Tpaper tpaper;
	private long questionSubjectId;
	private long questionTypeId;
	private Integer num;

	// Constructors

	/** default constructor */
	public TpaperDesign() {
	}

	/** full constructor */
	public TpaperDesign(Integer id, Tpaper tpaper, long questionSubjectId,
			long questionTypeId, Integer num) {
		this.id = id;
		this.tpaper = tpaper;
		this.questionSubjectId = questionSubjectId;
		this.questionTypeId = questionTypeId;
		this.num = num;
	}

	@Override
	public String toString() {
		return "TpaperDesign [id=" + id + ", tpaper=" + tpaper
				+ ", questionSubjectId=" + questionSubjectId
				+ ", questionTypeId=" + questionTypeId + ", num=" + num + "]";
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paper_id", nullable = false)
	public Tpaper getTpaper() {
		return this.tpaper;
	}

	public void setTpaper(Tpaper tpaper) {
		this.tpaper = tpaper;
	}

	@Column(name = "question_subject_id", nullable = false)
	public long getQuestionSubjectId() {
		return this.questionSubjectId;
	}

	public void setQuestionSubjectId(long questionSubjectId) {
		this.questionSubjectId = questionSubjectId;
	}

	@Column(name = "question_type_id", nullable = false)
	public long getQuestionTypeId() {
		return this.questionTypeId;
	}

	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	@Column(name = "num", nullable = false)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}