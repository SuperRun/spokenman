package com.zust.itee.exam.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrganizationTypeEnum {

    PROVINCE((short) 0, "考试办"), CITY((short) 1, "考试办"), DISTRICT((short) 2,
            "考试办"), COMPANY((short) 3, "考生办");

    private short type;

    private String info;

    private OrganizationTypeEnum(short type, String info) {
        this.type = type;
        this.info = info;
    }

    public static OrganizationTypeEnum stateOf(int index) {
        for (OrganizationTypeEnum e : values()) {
            if (e.getType() == index) {
                return e;
            }
        }
        return null;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @JsonValue
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
