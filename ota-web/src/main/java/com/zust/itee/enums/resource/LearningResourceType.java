package com.zust.itee.enums.resource;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 学习资源类型
 *
 * @author pojun
 */
public enum LearningResourceType {

    VIDEO((short) 1, "视频讲座"),
    AUDIO((short) 2, "音频讲座"),
    IMAGE_TEXT((short) 3, "图文");

    private short type;

    private String name;

    private static Map<Short, LearningResourceType> learningResourceTypeMap = Stream.of(values())
            .collect(toMap(LearningResourceType::getType, type -> type));

    public static Optional<LearningResourceType> getByType(Short type) {
        return Optional.ofNullable(learningResourceTypeMap.getOrDefault(type, null));
    }

    LearningResourceType(short type, String name) {
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
