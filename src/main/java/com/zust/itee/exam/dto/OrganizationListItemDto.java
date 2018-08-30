package com.zust.itee.exam.dto;

/**
 * 车企列表
 * Created by dxb on 12/14/2016.
 */
public class OrganizationListItemDto {
    private Integer id;

    private String name;

    private String shortName;//简称

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
