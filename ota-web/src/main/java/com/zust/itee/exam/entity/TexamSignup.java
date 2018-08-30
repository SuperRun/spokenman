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
 * TexamSignup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "texam_signup")
public class TexamSignup implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = -2344621576159273626L;
	private Integer id;
	private Texam texam;
	private Tdriver tdriver;
	private Date signupTime;
	private Double finalScore;
	private Integer finalNum;
	private String certificateNo;
	private String certificatePhoto;
	private Short status;			// ：-1->已报名；0->待考试；1->阅卷中；2->已完成
	private String remark;
	private Date takeExamTime;

	// Constructors

	/** default constructor */
	public TexamSignup() {
	}

	/** minimal constructor */
	public TexamSignup(Integer id, Texam texam, Tdriver tdriver,
			Date signupTime, Short status) {
		this.id = id;
		this.texam = texam;
		this.tdriver = tdriver;
		this.signupTime = signupTime;
		this.status = status;
	}

	/** full constructor */
	public TexamSignup(Integer id, Texam texam, Tdriver tdriver,
			Date signupTime, Double finalScore, String certificateNo,
			String certificatePhoto, Short status, String remark) {
		this.id = id;
		this.texam = texam;
		this.tdriver = tdriver;
		this.signupTime = signupTime;
		this.finalScore = finalScore;
		this.certificateNo = certificateNo;
		this.certificatePhoto = certificatePhoto;
		this.status = status;
		this.remark = remark;
	}



	/**
	 * 驾驶员报名考试时用
	 * @param texam
	 * @param tdriver
	 * @param signupTime
	 * @param status
	 */
	public TexamSignup(Texam texam, Tdriver tdriver, Date signupTime, Short status) {
		super();
		this.texam = texam;
		this.tdriver = tdriver;
		this.signupTime = signupTime;
		this.status = status;
	}

	@Override
	public String toString() {
		return "TexamSignup [id=" + id + ", texam=" + texam + ", tdriver="
				+ tdriver + ", signupTime=" + signupTime + ", finalScore="
				+ finalScore + ", certificateNo=" + certificateNo
				+ ", certificatePhoto=" + certificatePhoto + ", status="
				+ status + ", remark=" + remark + "]";
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
	@JoinColumn(name = "driver_id", nullable = false)
	public Tdriver getTdriver() {
		return this.tdriver;
	}

	public void setTdriver(Tdriver tdriver) {
		this.tdriver = tdriver;
	}

	@Column(name = "signup_time", nullable = false, length = 19)
	public Date getSignupTime() {
		return this.signupTime;
	}

	public void setSignupTime(Date signupTime) {
		this.signupTime = signupTime;
	}

	@Column(name = "final_score", precision = 22, scale = 0)
	public Double getFinalScore() {
		return this.finalScore;
	}


	public void setFinalScore(Double finalScore) {
		this.finalScore = finalScore;
	}

	@Column(name="final_num")
	public Integer getFinalNum() {
		return finalNum;
	}

	public void setFinalNum(Integer finalNum) {
		this.finalNum = finalNum;
	}

	@Column(name = "certificate_no", length = 50)
	public String getCertificateNo() {
		return this.certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	@Column(name = "certificate_photo")
	public String getCertificatePhoto() {
		return this.certificatePhoto;
	}

	public void setCertificatePhoto(String certificatePhoto) {
		this.certificatePhoto = certificatePhoto;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "take_exam_time", length = 19)
	public Date getTakeExamTime() {
		return takeExamTime;
	}

	public void setTakeExamTime(Date takeExamTime) {
		this.takeExamTime = takeExamTime;
	}


}