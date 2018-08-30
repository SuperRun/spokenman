package com.zust.itee.enums.training;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 培训状态枚举
 *
 * @author pojun
 */
public enum TrainingStatus {

    CANCELED((short) -1, "已取消"),
    RELEASED((short) 1, "已发布"),
    SIGN_UP((short) 2, "报名中"),
    WAITING_TRAINING((short) 3, "待培训"),
    TRAINING((short) 4, "培训中"),
    CONFIRMING((short) 5, "确认中"),
    COMPLETED((short) 6, "已完成");

    private short status;

    private String name;

    private static Map<Short, TrainingStatus> trainingStatusMap = Stream.of(values()).collect(
            Collectors.toMap(TrainingStatus::getStatus, status -> status));

    public static Optional<TrainingStatus> getByStatus(Short status) {
        return Optional.ofNullable(trainingStatusMap.getOrDefault(status, null));
    }

    TrainingStatus(short status, String name) {
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
