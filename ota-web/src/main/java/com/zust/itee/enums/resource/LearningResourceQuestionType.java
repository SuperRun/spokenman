package com.zust.itee.enums.resource;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum LearningResourceQuestionType {
    SINGLE((short) 1, "单选题"),
    MULTIPLE((short) 2, "多选题");

    private short type;

    private String name;

    LearningResourceQuestionType(short type, String name) {
        this.type = type;
        this.name = name;
    }

    private static Map<Short, LearningResourceQuestionType> learningResourceQuestionTypeMap = Stream
            .of(values())
            .collect(toMap(LearningResourceQuestionType::getType, type -> type));

    public static Optional<LearningResourceQuestionType> getByType(short type) {
        return Optional.ofNullable(learningResourceQuestionTypeMap.getOrDefault(type, null));
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
