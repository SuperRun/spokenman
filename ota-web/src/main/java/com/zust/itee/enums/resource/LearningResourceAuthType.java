package com.zust.itee.enums.resource;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 学习资源权限类型
 *
 * @author pojun
 */
public enum LearningResourceAuthType {

    ALL((short) 1, "所有等级可见"),
    EXCLUDE_SUB((short) 0, "下级不可见");

    private short type;
    private String name;

    private static Map<Short, LearningResourceAuthType> learningResourceAuthTypeMap = Stream.of(
            values())
            .collect(toMap(LearningResourceAuthType::getType, type -> type));

    public static Optional<LearningResourceAuthType> getByType(Short type) {
        return Optional.ofNullable(learningResourceAuthTypeMap.getOrDefault(type, null));
    }

    LearningResourceAuthType(short type, String name) {
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
