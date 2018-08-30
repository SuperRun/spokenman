package com.zust.itee.exam.dto;

/**
 * Tmember的数据传输对象
 * Created by dxb on 2016/8/24.
 */
public class MemberListDto {
    private Integer id;
    private String name;
    private String sfzNo;
    private String loginName;
    private String phone;

    public MemberListDto() {
    }

    public MemberListDto(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getSfzNo() {
        return sfzNo;
    }

    public void setSfzNo(String sfzNo) {
        this.sfzNo = sfzNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
