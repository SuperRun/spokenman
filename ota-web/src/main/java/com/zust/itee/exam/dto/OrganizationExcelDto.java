package com.zust.itee.exam.dto;

/**
 * Created by dxb on 2016/12/10.
 */
public class OrganizationExcelDto {

    private String companyName;
    private String superRegion;

    private String address;
    private String region;

    private String legalPerson; //企业法人
    private String legalPersonTel; //企业法人电话
    private String legalPersonEmail; //企业法人email

    private String leaderName; //分管领导
    private String leaderTel; //分管领导电话
    private String leaderEmail; //分管领导Email

    private String driverName;
    private String driverTel;
    private String driverEmail;
    private String driverSfzNo;

    public OrganizationExcelDto(String companyName, String superRegion, String address, String region, String legalPerson, String legalPersonTel, String legalPersonEmail, String leaderName, String leaderTel, String leaderEmail, String driverName, String driverTel, String driverEmail, String driverSfzNo) {
        this.companyName = companyName;
        this.superRegion = superRegion;
        this.address = address;
        this.region = region;
        this.legalPerson = legalPerson;
        this.legalPersonTel = legalPersonTel;
        this.legalPersonEmail = legalPersonEmail;
        this.leaderName = leaderName;
        this.leaderTel = leaderTel;
        this.leaderEmail = leaderEmail;
        this.driverName = driverName;
        this.driverTel = driverTel;
        this.driverEmail = driverEmail;
        this.driverSfzNo = driverSfzNo;
    }

    public OrganizationExcelDto() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getSuperRegion() {
        return superRegion;

    }

    public void setSuperRegion(String superRegion) {
        this.superRegion = superRegion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonTel() {
        return legalPersonTel;
    }

    public void setLegalPersonTel(String legalPersonTel) {
        this.legalPersonTel = legalPersonTel;
    }

    public String getLegalPersonEmail() {
        return legalPersonEmail;
    }

    public void setLegalPersonEmail(String legalPersonEmail) {
        this.legalPersonEmail = legalPersonEmail;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderTel() {
        return leaderTel;
    }

    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel;
    }

    public String getLeaderEmail() {
        return leaderEmail;
    }

    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverSfzNo() {
        return driverSfzNo;
    }

    public void setDriverSfzNo(String driverSfzNo) {
        this.driverSfzNo = driverSfzNo;
    }
}
