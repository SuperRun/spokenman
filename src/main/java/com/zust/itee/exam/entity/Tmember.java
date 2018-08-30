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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tmember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmember")
public class Tmember implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 6565741646494519044L;
    private Integer id;
    private Torganization torganization;
    private Tmember tmember;
    private String name;
    private String sfzNo;
    private String loginName;
    private String phone;
    private String password;
    private Date createTime;
    private short status;
    private Set<Tmember> tmembers = new HashSet<Tmember>(0);
    private Set<Torganization> torganizations = new HashSet<Torganization>(0);
    private Set<Tannouncement> tannouncements = new HashSet<Tannouncement>(0);

    private Integer otaUserId;

    // Constructors

    /**
     * default constructor
     */
    public Tmember() {
    }

    /**
     * minimal constructor
     */
    public Tmember(Integer id, String loginName, String password,
            Date createTime, short status) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.createTime = createTime;
        this.status = status;
    }

    public Tmember(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tmember(Integer id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public Tmember(Integer id, Torganization torganization, Tmember tmember,
            String name, String loginName, String password, Date createTime,
            short status, Set<Tmember> tmembers,
            Set<Torganization> torganizations) {
        this.id = id;
        this.torganization = torganization;
        this.tmember = tmember;
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.createTime = createTime;
        this.status = status;
        this.tmembers = tmembers;
        this.torganizations = torganizations;
    }

    @Override
    public String toString() {
        return "Tmember [id=" + id + ", torganization=" + torganization
                + ", tmember=" + tmember + ", name=" + name + ", sfzNo="
                + sfzNo + ", loginName=" + loginName + ", phone=" + phone
                + ", password=" + password + ", createTime=" + createTime
                + ", status=" + status + ", tmembers=" + tmembers
                + ", torganizations=" + torganizations + ", tannouncements="
                + tannouncements + "]";
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
    @JoinColumn(name = "org_id")
    public Torganization getTorganization() {
        return this.torganization;
    }

    public void setTorganization(Torganization torganization) {
        this.torganization = torganization;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    public Tmember getTmember() {
        return this.tmember;
    }

    public void setTmember(Tmember tmember) {
        this.tmember = tmember;
    }

    @Column(name = "name", length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sfz_no", length = 20)
    public String getSfzNo() {
        return sfzNo;
    }

    public void setSfzNo(String sfzNo) {
        this.sfzNo = sfzNo;
    }

    @Column(name = "login_name", nullable = false, length = 50)
    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "phone", length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "password", length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "status", nullable = false)
    public short getStatus() {
        return this.status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tmember")
    public Set<Tmember> getTmembers() {
        return this.tmembers;
    }

    public void setTmembers(Set<Tmember> tmembers) {
        this.tmembers = tmembers;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tmember")
    public Set<Torganization> getTorganizations() {
        return this.torganizations;
    }

    public void setTorganizations(Set<Torganization> torganizations) {
        this.torganizations = torganizations;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tmember")
    public Set<Tannouncement> getTannouncements() {
        return tannouncements;
    }

    public void setTannouncements(Set<Tannouncement> tannouncements) {
        this.tannouncements = tannouncements;
    }

    @Column(name = "ota_user_id")
    public Integer getOtaUserId() {
        return otaUserId;
    }

    public void setOtaUserId(Integer otaUserId) {
        this.otaUserId = otaUserId;
    }
}