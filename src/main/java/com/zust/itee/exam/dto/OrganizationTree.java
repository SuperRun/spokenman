package com.zust.itee.exam.dto;

import java.util.List;

/**
 * 机构树
 * <p/>
 * dxb
 * <p/>
 * 2016年12月7日 16:36:57
 */
public class OrganizationTree {

    private Integer id;

    private String name;

    private String shortName;

    //    private String region;
    private Long regionId;

    private List<OrganizationTree> children;

    public List<OrganizationTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationTree> children) {
        this.children = children;
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

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
