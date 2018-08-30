package com.zust.itee.exam.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tpaper entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tpaper")
public class Tpaper implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 3129912666811131137L;
	private Integer id;
	private Torganization torganization;
	private String name;
	private String description;
	private Date createTime;
	private double difficulty;
	private short type;
	private short status;
	private double easyPercent;
	private double mediumPercent;
	private double hardPercent;
	private Integer passNum;
	private Integer passScore;
	private Set<TpaperDesign> tpaperDesigns = new HashSet<TpaperDesign>(0);
	private Set<Texam> texams = new HashSet<Texam>(0);
	private Set<TpaperList> tpaperLists = new HashSet<TpaperList>(0);

	// Constructors

	/** default constructor */
	public Tpaper() {
	}

	/** minimal constructor */
	public Tpaper(Torganization torganization, String name,
			short type, short status) {
		this.torganization = torganization;
		this.name = name;
		this.type = type;
		this.status = status;
	}

	/** full constructor */
	public Tpaper(Integer id, Torganization torganization, String name,
			String description, Date createTime, double difficulty, short type,
			short status, Set<TpaperDesign> tpaperDesigns, Set<Texam> texams,
			Set<TpaperList> tpaperLists) {
		this.id = id;
		this.torganization = torganization;
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.difficulty = difficulty;
		this.type = type;
		this.status = status;
		this.tpaperDesigns = tpaperDesigns;
		this.texams = texams;
		this.tpaperLists = tpaperLists;
	}

	@Override
	public String toString() {
		return "Tpaper [id=" + id + ", torganization=" + torganization
				+ ", name=" + name + ", description=" + description
				+ ", createTime=" + createTime + ", difficulty=" + difficulty
				+ ", type=" + type + ", status=" + status + ", tpaperDesigns="
				+ tpaperDesigns + ", texams=" + texams + ", tpaperLists="
				+ tpaperLists + "]";
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
	@JoinColumn(name = "org_id", nullable = false)
	public Torganization getTorganization() {
		return this.torganization;
	}

	public void setTorganization(Torganization torganization) {
		this.torganization = torganization;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "difficulty", precision = 22, scale = 0)
	public double getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}

	@Column(name = "type", nullable = false)
	public short getType() {
		return this.type;
	}

	public void setType(short type) {
		this.type = type;
	}

	@Column(name = "status", nullable = false)
	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@Column(name = "easy_percent", nullable = false)
	public double getEasyPercent() {
		return easyPercent;
	}

	public void setEasyPercent(double easyPercent) {
		this.easyPercent = easyPercent;
	}

	@Column(name = "medium_percent", nullable = false)
	public double getMediumPercent() {
		return mediumPercent;
	}

	public void setMediumPercent(double mediumPercent) {
		this.mediumPercent = mediumPercent;
	}

	@Column(name = "hard_percent", nullable = false)
	public double getHardPercent() {
		return hardPercent;
	}

	public void setHardPercent(double hardPercent) {
		this.hardPercent = hardPercent;
	}

	@Column(name = "pass_num")
	public Integer getPassNum() {
		return passNum;
	}

	public void setPassNum(Integer passNum) {
		this.passNum = passNum;
	}

	@Column(name = "pass_score")
	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tpaper")
	public Set<TpaperDesign> getTpaperDesigns() {
		return this.tpaperDesigns;
	}

	public void setTpaperDesigns(Set<TpaperDesign> tpaperDesigns) {
		this.tpaperDesigns = tpaperDesigns;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tpaper")
	public Set<Texam> getTexams() {
		return this.texams;
	}

	public void setTexams(Set<Texam> texams) {
		this.texams = texams;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tpaper")
	public Set<TpaperList> getTpaperLists() {
		return this.tpaperLists;
	}

	public void setTpaperLists(Set<TpaperList> tpaperLists) {
		this.tpaperLists = tpaperLists;
	}



}