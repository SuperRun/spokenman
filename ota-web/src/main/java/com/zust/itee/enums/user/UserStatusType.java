package com.zust.itee.enums.user;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.enums.EnumsType;

import io.swagger.annotations.ApiModelProperty;

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

    REGISTER((short) 3, "新注册"),

    FREEZE((short) -2, "账号冻结");

    @ApiModelProperty("状态码")
    private short status;

    @ApiModelProperty("状态描述")
    private String description;

    private static Map<Short, UserStatusType> userStatusTypeMap = Stream.of(values())
            .collect(toMap(UserStatusType::getStatus, type -> type));

    public static UserStatusType getByStatus(Short status) {
        return userStatusTypeMap.getOrDefault(status, null);
    }

    public static List<EnumsType> getEnumsTypes() {
        return Arrays.stream(values())
                .map(type -> new EnumsType().builder()
                        .code(type.getStatus())
                        .name(type.getDescription())
                        .build())
                .collect(Collectors.toList());
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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
