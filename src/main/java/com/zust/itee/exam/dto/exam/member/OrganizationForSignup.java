package com.zust.itee.exam.dto.exam.member;

import java.util.List;

import com.zust.itee.exam.dto.exam.DriverDetailInfo;
import com.zust.itee.exam.entity.Torganization;

/**
 * @author terry 批量导入报名时按散装办显示驾驶员
 */
public class OrganizationForSignup {

    //id
    private Integer id;

    //全称
    private String name;

    //简称
    private String shortName;

    //驾驶员
    private List<DriverDetailInfo> drivers;

    public OrganizationForSignup() {

    }

    public OrganizationForSignup(Torganization torganization) {
        super();
        this.id = torganization.getId();
        this.name = torganization.getName();
        this.shortName = torganization.getShortName();
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<DriverDetailInfo> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverDetailInfo> drivers) {
        this.drivers = drivers;
    }
}
