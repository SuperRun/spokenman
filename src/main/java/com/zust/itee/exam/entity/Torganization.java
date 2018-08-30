package com.zust.itee.exam.entity;

import static javax.persistence.GenerationType.IDENTITY;

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
 * Torganization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "torganization")
public class Torganization implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 8497807147990433456L;
    private Integer id;
    private Torganization torganization; // 上级散装办
    private Tmember tmember; // 负责人
    private String name;// 全称
    private String shortName;// 简称
    private String description;// 描述
    private String linkmanName;// 联系人
    private String tel;// 联系电话
    private String email;// 电子邮箱
    private short type;// 类型

    private short status;
    private String remark;
    private Set<Tpaper> tpapers = new HashSet<Tpaper>(0);
    private Set<Tdriver> tdrivers = new HashSet<Tdriver>(0);
    private Set<Tmember> tmembers = new HashSet<Tmember>(0);
    private Set<Texam> texams = new HashSet<Texam>(0);
    private Set<Torganization> torganizations = new HashSet<Torganization>(0);
    private Set<Tannouncement> tannouncements = new HashSet<Tannouncement>(0);

    private Long regionId;

    private Integer otaOrgId;

    // Constructors

    /**
     * default constructor
     */
    public Torganization() {
    }

    /**
     * minimal constructor
     */
    public Torganization(String name, short type, short status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    /**
     * full constructor
     */
    public Torganization(Torganization torganization, Tmember tmember,
            String name, String shortName, String description, String tel,
            String email, short type, short status, Set<Tpaper> tpapers,

            Set<Tdriver> tdrivers,

            Set<Tmember> tmembers,
            Set<Texam> texams, Set<Torganization> torganizations) {
        this.torganization = torganization;
        this.tmember = tmember;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.tel = tel;
        this.email = email;
        this.type = type;
        this.status = status;
        this.tpapers = tpapers;
        this.tdrivers = tdrivers;
        this.tmembers = tmembers;
        this.texams = texams;
        this.torganizations = torganizations;
    }

    public Torganization(Integer id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Torganization{" + "type=" + type + ", id=" + id + ", name='"
                + name + '\'' + ", email='" + email + '\'' + ", description='"
                + description + '\'' + ", remark='" + remark + '\''
                + ", shortName='" + shortName + '\'' + ", status=" + status
                + '}';
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
    @JoinColumn(name = "parent_id")
    public Torganization getTorganization() {
        return this.torganization;
    }

    public void setTorganization(Torganization torganization) {
        this.torganization = torganization;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_person_id")
    public Tmember getTmember() {
        return this.tmember;
    }

    public void setTmember(Tmember tmember) {
        this.tmember = tmember;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "short_name", length = 20)
    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name = "description", length = 500)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "tel", length = 50)
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "email", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "torganization")
    public Set<Tpaper> getTpapers() {
        return this.tpapers;
    }

    public void setTpapers(Set<Tpaper> tpapers) {
        this.tpapers = tpapers;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "torganization")
    public Set<Tdriver> getTdrivers() {
        return this.tdrivers;
    }

    public void setTdrivers(Set<Tdriver> tdrivers) {
        this.tdrivers = tdrivers;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "torganization")
    public Set<Tmember> getTmembers() {
        return this.tmembers;
    }

    public void setTmembers(Set<Tmember> tmembers) {
        this.tmembers = tmembers;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "torganization")
    public Set<Texam> getTexams() {
        return this.texams;
    }

    public void setTexams(Set<Texam> texams) {
        this.texams = texams;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "torganization")
    public Set<Torganization> getTorganizations() {
        return this.torganizations;
    }

    public void setTorganizations(Set<Torganization> torganizations) {
        this.torganizations = torganizations;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "torganization")
    public Set<Tannouncement> getTannouncements() {
        return tannouncements;
    }

    public void setTannouncements(Set<Tannouncement> tannouncements) {
        this.tannouncements = tannouncements;
    }

    @Column(name = "region_id")
    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Column(name = "linkman_name", length = 50)
    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    @Column(name = "ota_org_id")
    public Integer getOtaOrgId() {
        return otaOrgId;
    }

    public void setOtaOrgId(Integer otaOrgId) {
        this.otaOrgId = otaOrgId;
    }
}