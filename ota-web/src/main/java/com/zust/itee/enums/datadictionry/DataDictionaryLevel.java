package com.zust.itee.enums.datadictionry;

/**
 * 数据字典level
 *
 * @author pojun
 */
public enum DataDictionaryLevel {

    ROOT(0, "根节点"),
    SUB(1, "子节点");
    private Integer level;

    private String description;

    DataDictionaryLevel(Integer level, String description) {
        this.level = level;
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
