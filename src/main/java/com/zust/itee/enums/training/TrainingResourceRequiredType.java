package com.zust.itee.enums.training;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 培训资源必修类型
 *
 * @author pojun
 */
public enum TrainingResourceRequiredType {

    REQUIRED((short) 1, "必修"),
    OPTIONAL((short) 0, "非必修");

    private short type;

    private String name;

    private static final Map<Short, TrainingResourceRequiredType> requiredTypeMap = Stream.of(
            values())
            .collect(Collectors.toMap(TrainingResourceRequiredType::getType, type -> type));

    public static Optional<TrainingResourceRequiredType> getByType(Short type) {
        return Optional.ofNullable(requiredTypeMap.getOrDefault(type, null));
    }

    TrainingResourceRequiredType(short type, String name) {
        this.type = type;
        this.name = name;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
