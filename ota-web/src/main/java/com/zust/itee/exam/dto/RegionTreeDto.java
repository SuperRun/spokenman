package com.zust.itee.exam.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 地区 ztree dto
 * Created by dxb on 2016/12/15.
 */
public class RegionTreeDto {

    private Long id;

    private Long parentId;//上级

    @JsonProperty("name")
    private String valueStr;

    private List<RegionTreeDto> children;

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

    public List<RegionTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<RegionTreeDto> children) {
        this.children = children;
    }
}
