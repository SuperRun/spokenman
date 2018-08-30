package com.zust.itee.enums.user;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zust.itee.enums.EnumsType;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户审批状态枚举
 *
 * @author pojun
 */
public enum UserApplyStatusType {

    APPLYING((short) 1, "申请中"),
    REJECT((short) 0, "拒绝"),
    APPROVE((short) 2, "通过");

    @ApiModelProperty("状态码")
    private short status;

    @ApiModelProperty("状态描述")
    private String description;

    private static final Map<Short, UserApplyStatusType> USER_APPLY_STATUS_TYPE_MAP = Stream.of(
            values()).collect(toMap(UserApplyStatusType::getStatus, statusType -> statusType));

    public static UserApplyStatusType getByStatus(Short status) {
        return USER_APPLY_STATUS_TYPE_MAP.getOrDefault(status, null);
    }

    public static List<EnumsType> getEnumsTypes() {
        //noinspection AccessStaticViaInstance
        return Arrays.stream(values())
                .map(type -> new EnumsType().builder()
                        .code(type.getStatus())
                        .name(type.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    UserApplyStatusType(short status, String description) {
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
