package com.zust.itee.exam.dto;

/**
 * 待审核的机构
 * Created by dxb on 2016/12/9.
 */
public class OrganizationUnverifyTree {
    private Integer id;

    private String name;

    //    private String region;
    private Long regionId;

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

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}
