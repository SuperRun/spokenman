package com.zust.itee.otacore.enums;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 一般 status
 *
 * @author pojun
 */
public enum NormalStatusType {

    DELETE((short) -1, "已删除"),

    NORMAL((short) 1, "正常");

    private short status;

    private String description;

    private static final Map<Short, NormalStatusType> normalStatusTypeMap = Stream.of(
            values()).collect(toMap(NormalStatusType::getStatus, statusType -> statusType));

    public static NormalStatusType getByStatus(Short status) {
        return normalStatusTypeMap.getOrDefault(status, null);
    }

    NormalStatusType(short status, String description) {
        this.status = status;
        this.description = description;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
