package com.zust.itee.exam.dto;

import com.zust.itee.exam.enums.OrganizationTypeEnum;

/**
 * org的model
 * Created by dxb on 2016/8/23.
 */
public class OrganizationDto {

    private Integer id;

    //上级
    private BaseDto parentOrg;

    //散装办信息
    private String name;
    private String shortName;
    private String description;
    private String region;
    private Long regionId;

    //负责人
    private Integer memberId;
    private String memberTel;
    private String memberName;

    //联系人
    private String linkmanName;//联系人
    private String tel;//联系电话
    private String email;//电子邮箱

    //散装办类型
    private OrganizationTypeEnum type;

    public String getMemberTel() {
        return memberTel;
    }

    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public BaseDto getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(BaseDto parentOrg) {
        this.parentOrg = parentOrg;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public OrganizationTypeEnum getType() {
        return type;
    }

    public void setType(OrganizationTypeEnum type) {
        this.type = type;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }
}
