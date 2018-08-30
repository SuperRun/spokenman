package com.zust.itee.exam.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tdriver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tdriver")
public class Tdriver implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 7472347764539108875L;
    private Integer id;
    private Torganization torganization;
    private String name;
    private String birthPlace;
    private Date birth;
    private short gender;
    private String sfzNo;
    private String photo;
    private String address;
    private String postcode;
    private String mobile;
    private String password;
    private String email;
    private String emergencyContact;
    private String emergencyContactMobile;
    private String driveLicenceNo;
    private String driveLincencePhoto;
    private Date driveLicenceStartTime;
    private Date driveLicenceEndTime;
    private String safeCentificateNo;
    private String safeCentificatePhoto;
    private Date safeCentificateStartTime;
    private Date safeCentificateEndTime;

    private String trainCentificateNo;
    private String trainCentificatePhoto;

    private short status; // -2 不可用-1->删除；0->待审核；1->可用 (可能会增加状态）2->培训完成需入职考试
    private Timestamp starTime;

    private Set<TexamResult> texamResults = new HashSet<TexamResult>(0);
    private Set<TexamSignup> texamSignups = new HashSet<TexamSignup>(0);

    private Integer otaUserId;

    // Constructors

    /**
     * default constructor
     */
    public Tdriver() {
    }

    /**
     * minimal constructor
     */
    public Tdriver(Torganization torganization, String name, String birthPlace,
            String sfzNo, short status) {
        this.torganization = torganization;
        this.name = name;
        this.birthPlace = birthPlace;
        this.sfzNo = sfzNo;
        this.status = status;
    }

    public Tdriver(String name, String birthPlace, Date birth, short gender,
            String sfzNo, String address, String postcode, String mobile,
            String password, String email, String emergencyContact,
            String emergencyContactMobile, String driveLicenceNo,
            Date driveLicenceStartTime, Date driveLicenceEndTime,
            String safeCentificateNo, Date safeCentificateStartTime,
            Date safeCentificateEndTime) {
        this.name = name;
        this.birthPlace = birthPlace;
        this.birth = birth;
        this.gender = gender;
        this.sfzNo = sfzNo;
        this.address = address;
        this.postcode = postcode;
        this.mobile = mobile;
        this.password = password;
        this.email = email;
        this.emergencyContact = emergencyContact;
        this.emergencyContactMobile = emergencyContactMobile;
        this.driveLicenceNo = driveLicenceNo;
        this.driveLicenceStartTime = driveLicenceStartTime;
        this.driveLicenceEndTime = driveLicenceEndTime;
        this.safeCentificateNo = safeCentificateNo;
        this.safeCentificateStartTime = safeCentificateStartTime;
        this.safeCentificateEndTime = safeCentificateEndTime;
    }

    // Dto
    public Tdriver(Integer id, Torganization torganization, String name,
            String birthPlace, Date birth, short gender, String sfzNo,
            String photo, String address, String postcode, String mobile,
            String password, String email, String emergencyContact,
            String emergencyContactMobile, String driveLicenceNo,
            String driveLincencePhoto, Date driveLicenceStartTime,
            Date driveLicenceEndTime, String safeCentificateNo,
            String safeCentificatePhoto, Date safeCentificateStartTime,
            Date safeCentificateEndTime, short status) {
        this.id = id;
        this.torganization = torganization;
        this.name = name;
        this.birthPlace = birthPlace;
        this.birth = birth;
        this.gender = gender;
        this.sfzNo = sfzNo;
        this.photo = photo;
        this.address = address;
        this.postcode = postcode;
        this.mobile = mobile;
        this.password = password;
        this.email = email;
        this.emergencyContact = emergencyContact;
        this.emergencyContactMobile = emergencyContactMobile;
        this.driveLicenceNo = driveLicenceNo;
        this.driveLincencePhoto = driveLincencePhoto;
        this.driveLicenceStartTime = driveLicenceStartTime;
        this.driveLicenceEndTime = driveLicenceEndTime;
        this.safeCentificateNo = safeCentificateNo;
        this.safeCentificatePhoto = safeCentificatePhoto;
        this.safeCentificateStartTime = safeCentificateStartTime;
        this.safeCentificateEndTime = safeCentificateEndTime;
        this.status = status;
    }

    /**
     * full constructor
     */
    public Tdriver(Torganization torganization, String name, String birthPlace,
            Date birth, short gender, String sfzNo, String photo,
            String address, String postcode, String mobile, String email,
            String emergencyContact, String emergencyContactMobile,
            String driveLicenceNo, String driveLincencePhoto,
            Date driveLicenceStartTime, Date driveLicenceEndTime,
            String safeCentificateNo, String safeCentificatePhoto,
            Date safeCentificateStartTime, Date safeCentificateEndTime,
            short status, Set<TexamResult> texamResults) {
        this.torganization = torganization;
        this.name = name;
        this.birthPlace = birthPlace;
        this.birth = birth;
        this.gender = gender;
        this.sfzNo = sfzNo;
        this.photo = photo;
        this.address = address;
        this.postcode = postcode;
        this.mobile = mobile;
        this.email = email;
        this.emergencyContact = emergencyContact;
        this.emergencyContactMobile = emergencyContactMobile;
        this.driveLicenceNo = driveLicenceNo;
        this.driveLincencePhoto = driveLincencePhoto;
        this.driveLicenceStartTime = driveLicenceStartTime;
        this.driveLicenceEndTime = driveLicenceEndTime;
        this.safeCentificateNo = safeCentificateNo;
        this.safeCentificatePhoto = safeCentificatePhoto;
        this.safeCentificateStartTime = safeCentificateStartTime;
        this.safeCentificateEndTime = safeCentificateEndTime;
        this.status = status;
        this.texamResults = texamResults;

        this.texamSignups = texamSignups;
    }

    @Override
    public String toString() {
        return "Tdriver-- [id=" + id + ", name=" + name + "]";
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

    @Column(name = "birth_place", nullable = false, length = 50)
    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birth", length = 10)
    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Column(name = "gender")
    public short getGender() {
        return this.gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    @Column(name = "sfz_no", nullable = false, length = 20, unique = true)
    public String getSfzNo() {
        return this.sfzNo;
    }

    public void setSfzNo(String sfzNo) {
        this.sfzNo = sfzNo;
    }

    @Column(name = "photo")
    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "postcode", length = 10)
    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Column(name = "mobile", length = 50)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "password", length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "emergency_contact", length = 50)
    public String getEmergencyContact() {
        return this.emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Column(name = "emergency_contact_mobile", length = 50)
    public String getEmergencyContactMobile() {
        return this.emergencyContactMobile;
    }

    public void setEmergencyContactMobile(String emergencyContactMobile) {
        this.emergencyContactMobile = emergencyContactMobile;
    }

    @Column(name = "drive_licence_no", length = 20)
    public String getDriveLicenceNo() {
        return this.driveLicenceNo;
    }

    public void setDriveLicenceNo(String driveLicenceNo) {
        this.driveLicenceNo = driveLicenceNo;
    }

    @Column(name = "drive_lincence_photo")
    public String getDriveLincencePhoto() {
        return this.driveLincencePhoto;
    }

    public void setDriveLincencePhoto(String driveLincencePhoto) {
        this.driveLincencePhoto = driveLincencePhoto;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "drive_licence_start_time", length = 10)
    public Date getDriveLicenceStartTime() {
        return this.driveLicenceStartTime;
    }

    public void setDriveLicenceStartTime(Date driveLicenceStartTime) {
        this.driveLicenceStartTime = driveLicenceStartTime;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "drive_licence_end_time", length = 10)
    public Date getDriveLicenceEndTime() {
        return this.driveLicenceEndTime;
    }

    public void setDriveLicenceEndTime(Date driveLicenceEndTime) {
        this.driveLicenceEndTime = driveLicenceEndTime;
    }

    @Column(name = "safe_centificate_no", length = 20)
    public String getSafeCentificateNo() {
        return this.safeCentificateNo;
    }

    public void setSafeCentificateNo(String safeCentificateNo) {
        this.safeCentificateNo = safeCentificateNo;
    }

    @Column(name = "safe_centificate_photo")
    public String getSafeCentificatePhoto() {
        return this.safeCentificatePhoto;
    }

    public void setSafeCentificatePhoto(String safeCentificatePhoto) {
        this.safeCentificatePhoto = safeCentificatePhoto;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "safe_centificate_start_time", length = 10)
    public Date getSafeCentificateStartTime() {
        return this.safeCentificateStartTime;
    }

    public void setSafeCentificateStartTime(Date safeCentificateStartTime) {
        this.safeCentificateStartTime = safeCentificateStartTime;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "safe_centificate_end_time", length = 10)
    public Date getSafeCentificateEndTime() {
        return this.safeCentificateEndTime;
    }

    public void setSafeCentificateEndTime(Date safeCentificateEndTime) {
        this.safeCentificateEndTime = safeCentificateEndTime;
    }

    @Column(name = "status", nullable = false)
    public short getStatus() {
        return this.status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Column(name = "star_time", length = 19)
    public Timestamp getStarTime() {
        return this.starTime;
    }

    public void setStarTime(Timestamp starTime) {
        this.starTime = starTime;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tdriver")
    public Set<TexamResult> getTexamResults() {
        return this.texamResults;
    }

    public void setTexamResults(Set<TexamResult> texamResults) {
        this.texamResults = texamResults;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tdriver")
    public Set<TexamSignup> getTexamSignups() {
        return this.texamSignups;
    }

    public void setTexamSignups(Set<TexamSignup> texamSignups) {
        this.texamSignups = texamSignups;
    }

    @Column(name = "train_centificate_no")
    public String getTrainCentificateNo() {
        return trainCentificateNo;
    }

    public void setTrainCentificateNo(String trainCentificateNo) {
        this.trainCentificateNo = trainCentificateNo;
    }

    @Column(name = "train_centificate_photo")
    public String getTrainCentificatePhoto() {
        return trainCentificatePhoto;
    }

    public void setTrainCentificatePhoto(String trainCentificatePhoto) {
        this.trainCentificatePhoto = trainCentificatePhoto;
    }

    @Column(name = "ota_user_id")
    public Integer getOtaUserId() {
        return otaUserId;
    }

    public void setOtaUserId(Integer otaUserId) {
        this.otaUserId = otaUserId;
    }
}