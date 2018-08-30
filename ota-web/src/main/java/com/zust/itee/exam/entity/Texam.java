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

import com.zust.itee.exam.dto.exam.member.OrganizeExam;
import com.zust.itee.exam.enums.exam.ExamTypeEnum;
import com.zust.itee.exam.util.MyDateUtil;

/**
 * Texam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "texam")
public class Texam implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1810149581976320317L;
    private Integer id;
    private Tpaper tpaper;
    private Tannouncement tannouncement;
    private Torganization torganization;
    private String name;
    private String description;
    private Date createTime;
    private Date signupStartTime;
    private Date signupEndTime;
    private Date examStartTime;
    private Date examEndTime;
    private short status;
    private short type; // 考试类型(0正式线上考，1模拟考，2正式线下考)
    private Integer duration;
    private Set<TexamSignup> texamSignups = new HashSet<TexamSignup>(0);
    private Set<TexamResult> texamResults = new HashSet<TexamResult>(0);

    // Constructors

    /**
     * default constructor
     */
    public Texam() {
    }

    /**
     * minimal constructor
     */
    public Texam(Tpaper tpaper, Torganization torganization, String name,
            Date createTime, short status) {
        this.tpaper = tpaper;
        this.torganization = torganization;
        this.name = name;
        this.createTime = createTime;
        this.status = status;
    }

    /**
     * full constructor
     */
    public Texam(Tpaper tpaper, Torganization torganization, String name,
            String description, Date createTime, Date signupStartTime,
            Date signupEndTime, Date examStartTime, Date examEndTime,
            short status, Set<TexamSignup> texamSignups,
            Set<TexamResult> texamResults) {
        this.tpaper = tpaper;
        this.torganization = torganization;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.signupStartTime = signupStartTime;
        this.signupEndTime = signupEndTime;
        this.examStartTime = examStartTime;
        this.examEndTime = examEndTime;
        this.status = status;
        this.texamSignups = texamSignups;
        this.texamResults = texamResults;
    }

    /**
     * 创建考试时用
     */
    public Texam(Torganization torganization, OrganizeExam organizeExam,
            short status, Date creatTime) {
        super();
        this.id = organizeExam.getExamId();
        this.torganization = torganization;
        this.name = organizeExam.getExamName();
        this.description = organizeExam.getExamDescription();
        this.createTime = creatTime;
        if (organizeExam.getExamStartTime() != null
                && organizeExam.getExamEndTime() != null) {
            this.duration = (int) (MyDateUtil.str2dateAjax(
                    organizeExam.getExamEndTime()).getTime() - MyDateUtil
                    .str2dateAjax(organizeExam.getExamStartTime()).getTime()) / 1000 / 60;
        } else {
            this.duration = organizeExam.getDuration();
        }
        this.signupStartTime = MyDateUtil.str2dateAjax(organizeExam
                .getSignupStartTime());
        this.signupEndTime = MyDateUtil.str2dateAjax(organizeExam
                .getSignupEndTime());
        this.examStartTime = MyDateUtil.str2dateAjax(organizeExam
                .getExamStartTime());
        this.examEndTime = MyDateUtil.str2dateAjax(organizeExam
                .getExamEndTime());
        if (organizeExam.getExamType() == ExamTypeEnum.OFFICIALONLINE.getId()) {
            this.type = organizeExam.getExamSubType();
        } else {
            this.type = organizeExam.getExamType();
        }
        this.status = status;
    }

    /**
     * 创建模拟考试用
     */
    public Texam(Torganization torganization, OrganizeExam organizeExam,
            Date creatTime) {
        super();
        this.id = organizeExam.getExamId();
        this.torganization = torganization;
        this.name = organizeExam.getExamName();
        this.description = organizeExam.getExamDescription();
        this.createTime = creatTime;
        this.duration = organizeExam.getDuration();
        this.type = organizeExam.getExamType();
    }

    @Override
    public String toString() {
        return "Texam [id=" + id + ", tpaper=" + tpaper + ", torganization="
                + torganization + ", name=" + name + ", description="
                + description + ", createTime=" + createTime
                + ", signupStartTime=" + signupStartTime + ", signupEndTime="
                + signupEndTime + ", examStartTime=" + examStartTime
                + ", examEndTime=" + examEndTime + ", status=" + status
                + ", texamSignups=" + texamSignups + ", texamResults="
                + texamResults + "]";
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
    @JoinColumn(name = "paper_id")
    public Tpaper getTpaper() {
        return this.tpaper;
    }

    public void setTpaper(Tpaper tpaper) {
        this.tpaper = tpaper;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    public Tannouncement getTannouncement() {
        return this.tannouncement;
    }

    public void setTannouncement(Tannouncement tannouncement) {
        this.tannouncement = tannouncement;
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

    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "signup_start_time", length = 19)
    public Date getSignupStartTime() {
        return this.signupStartTime;
    }

    public void setSignupStartTime(Date signupStartTime) {
        this.signupStartTime = signupStartTime;
    }

    @Column(name = "signup_end_time", length = 19)
    public Date getSignupEndTime() {
        return this.signupEndTime;
    }

    public void setSignupEndTime(Date signupEndTime) {
        this.signupEndTime = signupEndTime;
    }

    @Column(name = "exam_start_time", length = 19)
    public Date getExamStartTime() {
        return this.examStartTime;
    }

    public void setExamStartTime(Date examStartTime) {
        this.examStartTime = examStartTime;
    }

    @Column(name = "exam_end_time", length = 19)
    public Date getExamEndTime() {
        return this.examEndTime;
    }

    public void setExamEndTime(Date examEndTime) {
        this.examEndTime = examEndTime;
    }

    @Column(name = "status", nullable = false)
    public short getStatus() {
        return this.status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Column(name = "type", nullable = false)
    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Column(name = "duration", nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "texam")
    public Set<TexamSignup> getTexamSignups() {
        return this.texamSignups;
    }

    public void setTexamSignups(Set<TexamSignup> texamSignups) {
        this.texamSignups = texamSignups;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "texam")
    public Set<TexamResult> getTexamResults() {
        return this.texamResults;
    }

    public void setTexamResults(Set<TexamResult> texamResults) {
        this.texamResults = texamResults;
    }
}