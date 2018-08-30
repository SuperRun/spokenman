package com.zust.itee.exam.dto;

/**
 * member修改资料的模型
 * Created by dxb on 2016/9/27.
 */
public class MemberModifyDto {

    private String name;
    private String phone;
    private String sfzNo;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
