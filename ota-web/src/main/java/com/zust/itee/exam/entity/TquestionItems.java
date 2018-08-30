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
 * TquestionItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tquestion_items")
public class TquestionItems implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 3772695860722606555L;
	private Integer id;
	private Tquestion tquestion;
	private short sequence;
	private String content;
	private String pic;

	// Constructors

	/** default constructor */
	public TquestionItems() {
	}

	/** minimal constructor */
	public TquestionItems(Integer id, Tquestion tquestion, short sequence,
			String content) {
		this.id = id;
		this.tquestion = tquestion;
		this.sequence = sequence;
		this.content = content;
	}

	/** full constructor */
	public TquestionItems(Integer id, Tquestion tquestion, short sequence,
			String content, String pic) {
		this.id = id;
		this.tquestion = tquestion;
		this.sequence = sequence;
		this.content = content;
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "TquestionItems [id=" + id + ", tquestion=" + tquestion
				+ ", sequence=" + sequence + ", content=" + content + ", pic="
				+ pic + "]";
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
	@JoinColumn(name = "question_id", nullable = false)
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	@Column(name = "sequence", nullable = false)
	public short getSequence() {
		return this.sequence;
	}

	public void setSequence(short sequence) {
		this.sequence = sequence;
	}

	@Column(name = "content", length = 100)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "pic")
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}