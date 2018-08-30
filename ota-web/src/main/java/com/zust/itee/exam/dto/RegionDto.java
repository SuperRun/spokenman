package com.zust.itee.exam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 地区dto
 * Created by dxb on 2016/12/15.
 */
public class RegionDto {

    private Long id;

    private Long parentId;//上级

    @JsonProperty("name")
    private String valueStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }
}
