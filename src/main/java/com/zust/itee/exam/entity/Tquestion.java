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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zust.itee.exam.dto.exam.member.QuestionCreateModel;

/**
 * Tquestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tquestion")
public class Tquestion implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1368884097968913998L;
    private Integer id;
    private String content;
    private String pic1;
    private String pic2;
    private String pic3;
    private long subjectId;
    private long typeId;
    private String answer;
    private double marks;
    private short difficulty; // 1,2,3 易中难
    private short status;
    private String remark;
    private Date createTime;
    private short required;
    private Integer operatorId;
    private Set<TquestionItems> tquestionItemses = new HashSet<TquestionItems>(
            0);
    private Set<TpaperList> tpaperLists = new HashSet<TpaperList>(0);
    private Set<TexamResult> texamResults = new HashSet<TexamResult>(0);

    // Constructors

    /**
     * default constructor
     */
    public Tquestion() {
    }

    /**
     * minimal constructor
     */
    public Tquestion(Integer id, String content, long subjectId, long typeId,
            String answer, double marks, short status, Date createTime) {
        this.id = id;
        this.content = content;
        this.subjectId = subjectId;
        this.typeId = typeId;
        this.answer = answer;
        this.marks = marks;
        this.status = status;
        this.createTime = createTime;
    }

    /**
     * full constructor
     */
    public Tquestion(Integer id, String content, String pic1, String pic2,
            String pic3, long subjectId, long typeId, String answer,
            double marks, short difficulty, short status, String remark,
            Date createTime, Integer operatorId,
            Set<TquestionItems> tquestionItemses, Set<TpaperList> tpaperLists,
            Set<TexamResult> texamResults) {
        this.id = id;
        this.content = content;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.subjectId = subjectId;
        this.typeId = typeId;
        this.answer = answer;
        this.marks = marks;
        this.difficulty = difficulty;
        this.status = status;
        this.remark = remark;
        this.createTime = createTime;
        this.operatorId = operatorId;
        this.tquestionItemses = tquestionItemses;
        this.tpaperLists = tpaperLists;
        this.texamResults = texamResults;
    }

    public Tquestion(QuestionCreateModel questionCreateModel, Date createTime,
            Integer operatorId) {
        super();
        this.content = questionCreateModel.getContent();
        this.pic1 = questionCreateModel.getPic1();
        this.pic2 = questionCreateModel.getPic2();
        this.pic3 = questionCreateModel.getPic3();
        this.subjectId = questionCreateModel.getSubjectId();
        this.typeId = questionCreateModel.getTypeId();
        this.answer = questionCreateModel.getAnswer();
        this.marks = questionCreateModel.getMarks();
        this.difficulty = questionCreateModel.getDifficulty();
        this.status = 1;
        this.createTime = createTime;
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "Tquestion [id=" + id + ", content=" + content + ", pic1="
                + pic1 + ", pic2=" + pic2 + ", pic3=" + pic3 + ", subjectId="
                + subjectId + ", typeId=" + typeId + ", answer=" + answer
                + ", marks=" + marks + ", difficulty=" + difficulty
                + ", status=" + status + ", remark=" + remark + ", createTime="
                + createTime + ", operatorId=" + operatorId
                + ", tquestionItemses=" + tquestionItemses + ", tpaperLists="
                + tpaperLists + ", texamResults=" + texamResults + "]";
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

    @Column(name = "content", nullable = false, length = 500)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "pic1")
    public String getPic1() {
        return this.pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    @Column(name = "pic2")
    public String getPic2() {
        return this.pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    @Column(name = "pic3")
    public String getPic3() {
        return this.pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    @Column(name = "subject_id", nullable = false)
    public long getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "type_id", nullable = false)
    public long getTypeId() {
        return this.typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    @Column(name = "answer", nullable = false, length = 500)
    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(name = "marks", nullable = false, precision = 22, scale = 0)
    public double getMarks() {
        return this.marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Column(name = "difficulty")
    public short getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }

    @Column(name = "status", nullable = false)
    public short getStatus() {
        return this.status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "required", nullable = false)
    public short getRequired() {
        return this.required;
    }

    public void setRequired(short required) {
        this.required = required;
    }

    @Column(name = "operator_id")
    public Integer getOperatorId() {
        return this.operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tquestion")
    public Set<TquestionItems> getTquestionItemses() {
        return this.tquestionItemses;
    }

    public void setTquestionItemses(Set<TquestionItems> tquestionItemses) {
        this.tquestionItemses = tquestionItemses;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tquestion")
    public Set<TpaperList> getTpaperLists() {
        return this.tpaperLists;
    }

    public void setTpaperLists(Set<TpaperList> tpaperLists) {
        this.tpaperLists = tpaperLists;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tquestion")
    public Set<TexamResult> getTexamResults() {
        return this.texamResults;
    }

    public void setTexamResults(Set<TexamResult> texamResults) {
        this.texamResults = texamResults;
    }
}