package com.zust.itee.otauser.client.enums;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 用户状态枚举
 *
 * @author pojun
 */
public enum UserStatusType {
    DELETE((short) -1, "删除"),

    NORMAL((short) 1, "正常"),

    REJECTED((short) 0, "审核拒绝"),

    APPLYING((short) 2, "申请中"),

    FREEZE((short) -2, "账号冻结");

    private short status;

    private String description;

    private static Map<Short, UserStatusType> userStatusTypeMap = Stream.of(values())
            .collect(toMap(UserStatusType::getStatus, type -> type
            ));

    public static UserStatusType getByStatus(Short status) {
        return userStatusTypeMap.getOrDefault(status, null);
    }

    UserStatusType(short status, String description) {
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
