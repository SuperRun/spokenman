package com.zust.itee.exam.dto;

/**
 * 创建机构的时候主管的模型
 * Created by dxb on 12/8/2016.
 */
public class LinkPersonCreateDto {

    private String lpName;
    private String lpTel;

    private String loginName;

    private String password;

    public String getLpTel() {
        return lpTel;
    }

    public void setLpTel(String lpTel) {
        this.lpTel = lpTel;
    }

    public String getLpName() {
        return lpName;
    }

    public void setLpName(String lpName) {
        this.lpName = lpName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
