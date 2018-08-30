package com.zust.itee.enums.user;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.zust.itee.enums.EnumsType;

/**
 * 用户类型枚举
 *
 * @author pojun
 */
public enum UserType {
    ROOT((short) 0, "平台用户"),
    PERSONAL((short) 1, "个人用户"),
    COMPANY((short) 2, "单位用户"),
    LECTURER((short) 3, "讲师");

    private short value;

    private String description;

    private static final Map<Short, UserType> USER_TYPE_MAP = Stream.of(values())
            .collect(toMap(UserType::getValue, userType -> userType));

    public static List<EnumsType> getEnumsTypes() {
        return Arrays.stream(values())
                .map(type -> new EnumsType().builder()
                        .code(type.getValue())
                        .name(type.getDescription())
                        .build())
                .collect(toList());
    }

    UserType(short value, String description) {
        this.value = value;
        this.description = description;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
