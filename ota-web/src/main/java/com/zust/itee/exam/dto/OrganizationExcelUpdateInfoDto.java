package com.zust.itee.exam.dto;

/**
 * 更新散装办信息用的 excel
 * Created by dxb on 2016/12/30.
 */
public class OrganizationExcelUpdateInfoDto {

    private String province;
    private String city;
    private String district;
    private String orgName;
    private String orgShortName;
    private String orgIntro;
    private String managerName;
    private String managerTel;
    private String managerPhone;
    private String managerEmail;
    private String contactName;
    private String contactTel;
    private String contactPhone;
    private String contactEmail;

    public OrganizationExcelUpdateInfoDto(String province, String city, String district, String orgName, String orgShortName, String orgIntro, String managerName, String managerTel, String managerPhone, String managerEmail, String contactName, String contactTel, String contactPhone, String contactEmail) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.orgName = orgName;
        this.orgShortName = orgShortName;
        this.orgIntro = orgIntro;
        this.managerName = managerName;
        this.managerTel = managerTel;
        this.managerPhone = managerPhone;
        this.managerEmail = managerEmail;
        this.contactName = contactName;
        this.contactTel = contactTel;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getOrgIntro() {
        return orgIntro;
    }

    public void setOrgIntro(String orgIntro) {
        this.orgIntro = orgIntro;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
