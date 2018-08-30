package com.zust.itee.exam.dto;

/**
 * 创建员工的模型
 * Created by dxb on 2016/9/27.
 */
public class MemberCreateDto {
    private String loginName;
    private String name;
    private String phone;
    private String sfzNo;


    public MemberCreateDto() {
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSfzNo() {
        return sfzNo;
    }

    public void setSfzNo(String sfzNo) {
        this.sfzNo = sfzNo;
    }

}
