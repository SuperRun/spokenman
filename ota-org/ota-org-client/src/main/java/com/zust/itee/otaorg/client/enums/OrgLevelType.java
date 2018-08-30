package com.zust.itee.otaorg.client.enums;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 组织等级枚举
 *
 * @author pojun
 */
public enum OrgLevelType {

    COUNTRY((short) 0, "国家级"),
    PROVINCE((short) 1, "省级"),
    CITY((short) 2, "市级"),
    AREA((short) 3, "区级");
    private short level;

    private String name;

    private static Map<Short, OrgLevelType> orgLevelTypeMap = Stream.of(values())
            .collect(toMap(OrgLevelType::getLevel, type -> type));

    public static OrgLevelType getByLevel(Short level) {
        return orgLevelTypeMap.getOrDefault(level, null);
    }

    OrgLevelType(short level, String name) {
        this.level = level;
        this.name = name;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
