package com.zust.itee.exam.dto;

/**
 * 基本dto 包含id和name两个属性 方便关联属性的显示
 * Created by dxb on 2016/9/13.
 */
public class BaseDto {

    private Integer id;

    private String name;

    public BaseDto() {
    }

    public BaseDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
