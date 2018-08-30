package com.zust.itee.exam.dto;

/**
 * Created by liy on 2016/12/10.
 */
public class DriverVue {

    private Integer id;

    private String name;

    public DriverVue() {
    }

    public DriverVue(Integer id, String name) {
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
}
