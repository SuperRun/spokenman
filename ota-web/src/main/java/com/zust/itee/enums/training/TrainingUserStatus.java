package com.zust.itee.enums.training;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户培训状态枚举
 *
 * @author pojun
 */
public enum TrainingUserStatus {

    CANCELED((short) -1, "已取消"),

    SIGNED_UP((short) 0, "已报名"),

    PASS((short) 1, "通过"),

    FAILED((short) 2, "不通过");

    // ABSENT((short) 3, "缺席"),;

    private short status;
    private String name;

    private static Map<Short, TrainingUserStatus> trainingUserStatusMap = Arrays.stream(
            TrainingUserStatus.values())
            .collect(Collectors.toMap(TrainingUserStatus::getStatus, status -> status));

    public static Optional<TrainingUserStatus> getByStatus(Short status) {
        return Optional.ofNullable(trainingUserStatusMap.getOrDefault(status, null));
    }

    TrainingUserStatus(short status, String name) {
        this.status = status;
        this.name = name;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
