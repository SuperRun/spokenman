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
 * TpaperList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tpaper_list")
public class TpaperList implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 3213474634316478024L;
	private Integer id;
	private Tpaper tpaper;
	private Tquestion tquestion;
	private Integer sequence;

	// Constructors

	/** default constructor */
	public TpaperList() {
	}

	/** minimal constructor */
	public TpaperList(Integer id, Tpaper tpaper, Tquestion tquestion) {
		this.id = id;
		this.tpaper = tpaper;
		this.tquestion = tquestion;
	}

	/** full constructor */
	public TpaperList(Integer id, Tpaper tpaper, Tquestion tquestion,
			Integer sequence) {
		this.id = id;
		this.tpaper = tpaper;
		this.tquestion = tquestion;
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "TpaperList [id=" + id + ", tpaper=" + tpaper + ", tquestion="
				+ tquestion + ", sequence=" + sequence + "]";
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	@Column(name = "sequence")
	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}


}