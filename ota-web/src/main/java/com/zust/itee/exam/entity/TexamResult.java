package com.zust.itee.exam.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TexamResult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "texam_result")
public class TexamResult implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 3392504232105575588L;
	private Integer id;
	private Texam texam;
	private Tquestion tquestion;
	private Tdriver tdriver;
	private Integer sequence;
	private String answer;
	private Date submitTime;
	private double marks;

	// Constructors

	/** default constructor */
	public TexamResult() {
	}

	/** minimal constructor */
	public TexamResult(Integer id, Texam texam, Tquestion tquestion,
			Tdriver tdriver, Integer sequence, String answer, Date submitTime) {
		this.id = id;
		this.texam = texam;
		this.tquestion = tquestion;
		this.tdriver = tdriver;
		this.sequence = sequence;
		this.answer = answer;
		this.submitTime = submitTime;
	}

	/** full constructor */
	public TexamResult(Integer id, Texam texam, Tquestion tquestion,
			Tdriver tdriver, Integer sequence, String answer, Date submitTime,
			double marks) {
		this.id = id;
		this.texam = texam;
		this.tquestion = tquestion;
		this.tdriver = tdriver;
		this.sequence = sequence;
		this.answer = answer;
		this.submitTime = submitTime;
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "TexamResult [id=" + id + ", texam=" + texam + ", tquestion="
				+ tquestion + ", tdriver=" + tdriver + ", sequence=" + sequence
				+ ", answer=" + answer + ", submitTime=" + submitTime
				+ ", marks=" + marks + "]";
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
	@JoinColumn(name = "exam_id", nullable = false)
	public Texam getTexam() {
		return this.texam;
	}

	public void setTexam(Texam texam) {
		this.texam = texam;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driver_id", nullable = false)
	public Tdriver getTdriver() {
		return this.tdriver;
	}

	public void setTdriver(Tdriver tdriver) {
		this.tdriver = tdriver;
	}

	@Column(name = "sequence", nullable = false)
	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Column(name = "answer",length = 500)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "submit_time", nullable = false, length = 19)
	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	@Column(name = "marks", precision = 22, scale = 0)
	public double getMarks() {
		return this.marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

}