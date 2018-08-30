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
 * 公告的实体类
 * Created by dxb on 2016/8/27.
 */
@Entity
@Table(name = "tannouncement")
public class Tannouncement {

    private Integer id;
    private String title;
    private String text;
    private Date createDate;
    private Date closureDate;
    private String attachment;
    private Short type;
    private Integer readCount;
    private Tmember tmember;
    private Torganization torganization;
    private Short status;
	private Set<Texam> texams = new HashSet<Texam>(0);

    public Tannouncement(String title, String text, Date createDate, Date closureDate, String attachment, Short type, Integer readCount, Tmember tmember, Torganization torganization) {
        this.title = title;
        this.text = text;
        this.createDate = createDate;
        this.closureDate = closureDate;
        this.attachment = attachment;
        this.type = type;
        this.readCount = readCount;
        this.tmember = tmember;
        this.torganization = torganization;
    }

    public Tannouncement() {

    }

    public Tannouncement(Integer id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "Tannouncement{" +
                "attachment='" + attachment + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", closureDate=" + closureDate +
                ", type=" + type +
                ", readCount=" + readCount +
                '}';
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title", length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "text", length = 2000)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "closure_date")
    public Date getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Date closureDate) {
        this.closureDate = closureDate;
    }

    @Column(name = "attachment", length = 45)
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }




    @Column(name = "read_count")
    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    @Column(name = "type")
    public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    public Tmember getTmember() {
        return tmember;
    }

    public void setTmember(Tmember tmember) {
        this.tmember = tmember;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_organization_id")
    public Torganization getTorganization() {
        return torganization;
    }

    public void setTorganization(Torganization torganization) {
        this.torganization = torganization;
    }

    @Column(name = "status")
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tannouncement")
	public Set<Texam> getTexams() {
		return this.texams;
	}

	public void setTexams(Set<Texam> texams) {
		this.texams = texams;
	}
}
