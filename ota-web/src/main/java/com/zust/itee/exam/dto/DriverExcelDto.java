package com.zust.itee.exam.dto;

import java.util.Date;

/**
 * Created by liy on 2016/10/27.
 */
public class DriverExcelDto {

    private Integer id;
    //private Torganization torganization;
    private String name;
    private Integer orgId;
    private String orgName;
//    private String birthPlace;
    private Date birth;
    private String mobile;
    private String sfzNo;
    private String trainCentificateNo;
    private String address;

    private String emergencyContact;
    private String emergencyContactMobile;

    private String password;
    private String birthPlace;


    public DriverExcelDto(){}

    public DriverExcelDto(Integer orgId, String orgName, String name, String mobile, String sfzNo, String trainCentificateNo, String address, String emergencyContact, String emergencyContactMobile) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.name = name;
        this.mobile = mobile;
        this.sfzNo = sfzNo;
        this.trainCentificateNo = trainCentificateNo;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.emergencyContactMobile = emergencyContactMobile;
    }

    public DriverExcelDto(Integer id, String name, Integer orgId, String orgName, Date birth, String mobile, String sfzNo, String trainCentificateNo, String address, String emergencyContact, String emergencyContactMobile) {
        this.id = id;
        this.name = name;
        this.orgId = orgId;
        this.orgName = orgName;
        this.birth = birth;
        this.mobile = mobile;
        this.sfzNo = sfzNo;
        this.trainCentificateNo = trainCentificateNo;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.emergencyContactMobile = emergencyContactMobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSfzNo() {
        return sfzNo;
    }

    public void setSfzNo(String sfzNo) {
        this.sfzNo = sfzNo;
    }

    public String getTrainCentificateNo() {
        return trainCentificateNo;
    }

    public void setTrainCentificateNo(String trainCentificateNo) {
        this.trainCentificateNo = trainCentificateNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
