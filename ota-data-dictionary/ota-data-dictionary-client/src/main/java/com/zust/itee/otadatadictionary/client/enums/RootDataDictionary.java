package com.zust.itee.otadatadictionary.client.enums;

/**
 * 数据字典类型（根节点）
 *
 * @author pojun
 */
public enum RootDataDictionary {

    AREA("区域信息"),

    ORG_TYPE("组织条线");

    private Integer level;

    private String valueStr;

    RootDataDictionary(String valueStr) {
        this.level = 0;
        this.valueStr = valueStr;
    }

    RootDataDictionary(Integer level, String valueStr) {
        this.level = level;
        this.valueStr = valueStr;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }
}
