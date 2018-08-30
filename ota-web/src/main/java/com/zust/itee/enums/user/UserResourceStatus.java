package com.zust.itee.enums.user;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户学习某个资源的状态
 *
 * @author pojun
 */
public enum UserResourceStatus {
    UNDEFINE((short) 0, "未学习"),
    STUDYING((short) 1, "学习中"),
    FINISHED((short) 2, "学习完成");

    @ApiModelProperty("状态码")
    private short status;

    @ApiModelProperty("状态描述")
    private String description;

    private static final Map<Short, UserResourceStatus> userLearningResourceStatus = Stream.of(
            values())
            .collect(toMap(UserResourceStatus::getStatus, statusType -> statusType));

    public static Optional<UserResourceStatus> getByStatus(Short status) {
        return Optional.ofNullable(userLearningResourceStatus.getOrDefault(status, null));
    }

    UserResourceStatus(short status, String description) {
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
