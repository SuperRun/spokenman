package com.zust.itee.otadatadictionary.client.enums;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 区域等级枚举
 *
 * @author pojun
 */
public enum AreaLevelType {
    COUNTRY(0, "国家"),
    PROVINCE(1, "省份"),
    CITY(2, "城市"),
    AREA(3, "区");

    private Integer code;

    private String level;

    private static Map<Integer, AreaLevelType> areaLevelTypeMap = Stream.of(values())
            .collect(toMap(AreaLevelType::getCode, type -> type));

    /**
     * 根据 code 获取 区域等级
     */
    public static AreaLevelType getByCode(Integer code) {
        return areaLevelTypeMap.getOrDefault(code, null);
    }

    AreaLevelType(Integer code, String level) {
        this.code = code;
        this.level = level;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
