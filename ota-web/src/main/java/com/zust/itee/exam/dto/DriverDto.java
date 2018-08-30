package com.zust.itee.exam.dto;

import java.util.Date;

import com.zust.itee.exam.entity.Torganization;

/**
 * Created by liy on 2016/9/28.
 */
public class DriverDto {

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
    private short status;            //-2 不可用-1->删除；0->待审核；1->可用 (可能会增加状态）

    public DriverDto() {

    }

    public DriverDto(Integer id, Torganization torganization, String name, String birthPlace,
            Date birth, short gender, String sfzNo, String photo, String address, String postcode,
            String mobile, String password, String email, String emergencyContact,
            String emergencyContactMobile, String driveLicenceNo, String driveLincencePhoto,
            Date driveLicenceStartTime, Date driveLicenceEndTime, String safeCentificateNo,
            String safeCentificatePhoto, Date safeCentificateStartTime, Date safeCentificateEndTime,
            short status) {
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

    @Override
    public String toString() {
        return "DriverDto{" +
                "id=" + id +
                ", torganization=" + torganization +
                ", name='" + name + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", birth=" + birth +
                ", gender=" + gender +
                ", sfzNo='" + sfzNo + '\'' +
                ", photo='" + photo + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", emergencyContactMobile='" + emergencyContactMobile + '\'' +
                ", driveLicenceNo='" + driveLicenceNo + '\'' +
                ", driveLincencePhoto='" + driveLincencePhoto + '\'' +
                ", driveLicenceStartTime=" + driveLicenceStartTime +
                ", driveLicenceEndTime=" + driveLicenceEndTime +
                ", safeCentificateNo='" + safeCentificateNo + '\'' +
                ", safeCentificatePhoto='" + safeCentificatePhoto + '\'' +
                ", safeCentificateStartTime=" + safeCentificateStartTime +
                ", safeCentificateEndTime=" + safeCentificateEndTime +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Torganization getTorganization() {
        return torganization;
    }

    public void setTorganization(Torganization torganization) {
        this.torganization = torganization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getSfzNo() {
        return sfzNo;
    }

    public void setSfzNo(String sfzNo) {
        this.sfzNo = sfzNo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactMobile() {
        return emergencyContactMobile;
    }

    public void setEmergencyContactMobile(String emergencyContactMobile) {
        this.emergencyContactMobile = emergencyContactMobile;
    }

    public String getDriveLicenceNo() {
        return driveLicenceNo;
    }

    public void setDriveLicenceNo(String driveLicenceNo) {
        this.driveLicenceNo = driveLicenceNo;
    }

    public String getDriveLincencePhoto() {
        return driveLincencePhoto;
    }

    public void setDriveLincencePhoto(String driveLincencePhoto) {
        this.driveLincencePhoto = driveLincencePhoto;
    }

    public Date getDriveLicenceStartTime() {
        return driveLicenceStartTime;
    }

    public void setDriveLicenceStartTime(Date driveLicenceStartTime) {
        this.driveLicenceStartTime = driveLicenceStartTime;
    }

    public Date getDriveLicenceEndTime() {
        return driveLicenceEndTime;
    }

    public void setDriveLicenceEndTime(Date driveLicenceEndTime) {
        this.driveLicenceEndTime = driveLicenceEndTime;
    }

    public String getSafeCentificateNo() {
        return safeCentificateNo;
    }

    public void setSafeCentificateNo(String safeCentificateNo) {
        this.safeCentificateNo = safeCentificateNo;
    }

    public String getSafeCentificatePhoto() {
        return safeCentificatePhoto;
    }

    public void setSafeCentificatePhoto(String safeCentificatePhoto) {
        this.safeCentificatePhoto = safeCentificatePhoto;
    }

    public Date getSafeCentificateStartTime() {
        return safeCentificateStartTime;
    }

    public void setSafeCentificateStartTime(Date safeCentificateStartTime) {
        this.safeCentificateStartTime = safeCentificateStartTime;
    }

    public Date getSafeCentificateEndTime() {
        return safeCentificateEndTime;
    }

    public void setSafeCentificateEndTime(Date safeCentificateEndTime) {
        this.safeCentificateEndTime = safeCentificateEndTime;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
