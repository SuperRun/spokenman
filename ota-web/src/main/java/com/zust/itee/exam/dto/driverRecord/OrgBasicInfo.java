package com.zust.itee.exam.dto.driverRecord;

/**
 * Created by liy on 2016/12/15.
 */
public class OrgBasicInfo {

    private Integer orgCityId;
    private String orgCityName;
    private Integer orgDistrictId;
    private String orgDistrictName;
    private Integer orgCompanyId;
    private String orgCompanyName;

    private Integer driverId;
    private String driverName;
    private String driverTel;

    private String legalPerson; //企业法人
    private String legalPersonTel; //企业法人电话

    private String leaderName; //分管领导
    private String leaderTel; //分管领导电话

    public OrgBasicInfo() {
    }

    public OrgBasicInfo(Integer orgCompanyId, String orgCompanyName, Integer driverId, String driverName, String driverTel, String legalPerson, String legalPersonTel, String leaderName, String leaderTel) {
        this.orgCompanyId = orgCompanyId;
        this.orgCompanyName = orgCompanyName;
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverTel = driverTel;
        this.legalPerson = legalPerson;
        this.legalPersonTel = legalPersonTel;
        this.leaderName = leaderName;
        this.leaderTel = leaderTel;
    }

    public Integer getOrgCityId() {
        return orgCityId;
    }

    public void setOrgCityId(Integer orgCityId) {
        this.orgCityId = orgCityId;
    }

    public String getOrgCityName() {
        return orgCityName;
    }

    public void setOrgCityName(String orgCityName) {
        this.orgCityName = orgCityName;
    }

    public Integer getOrgDistrictId() {
        return orgDistrictId;
    }

    public void setOrgDistrictId(Integer orgDistrictId) {
        this.orgDistrictId = orgDistrictId;
    }

    public String getOrgDistrictName() {
        return orgDistrictName;
    }

    public void setOrgDistrictName(String orgDistrictName) {
        this.orgDistrictName = orgDistrictName;
    }

    public Integer getOrgCompanyId() {
        return orgCompanyId;
    }

    public void setOrgCompanyId(Integer orgCompanyId) {
        this.orgCompanyId = orgCompanyId;
    }

    public String getOrgCompanyName() {
        return orgCompanyName;
    }

    public void setOrgCompanyName(String orgCompanyName) {
        this.orgCompanyName = orgCompanyName;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
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
}
