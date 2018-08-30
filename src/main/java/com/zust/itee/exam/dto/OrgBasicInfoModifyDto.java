package com.zust.itee.exam.dto;

/**
 * 创建车企的时候需要用到的车企基本信息模型
 * Created by dxb on 2016/9/27.
 */
public class OrgBasicInfoModifyDto {
    //实体属性
    private String legalPerson; //企业法人
    private String legalPersonTel; //企业法人电话
    private String legalPersonEmail; //企业法人email

    private String leaderName; //分管领导
    private String leaderTel; //分管领导电话
    private String leaderEmail; //分管领导Email


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

}
