package com.zust.itee.enums.org;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zust.itee.enums.EnumsType;

import io.swagger.annotations.ApiModelProperty;

/**
 * 组织等级枚举
 *
 * @author pojun
 */
public enum OrgLevelType {

    COUNTRY((short) 1, "国家级"),
    PROVINCE((short) 2, "省部级"),
    CITY((short) 3, "司厅局级"),
    AREA((short) 4, "县初级"),
    TOWN((short) 5, "乡镇级");

    @ApiModelProperty("等级码")
    private short level;

    @ApiModelProperty("等级描述")
    private String name;

    private static Map<Short, OrgLevelType> orgLevelTypeMap = Stream.of(values())
            .collect(toMap(OrgLevelType::getLevel, type -> type));

    public static OrgLevelType getByLevel(Short level) {
        return orgLevelTypeMap.getOrDefault(level, null);
    }

    public static List<EnumsType> getEnumsTypes() {
        //noinspection AccessStaticViaInstance
        return Arrays.stream(values())
                .map(type -> new EnumsType().builder()
                        .code(type.getLevel())
                        .name(type.getName())
                        .build())
                .collect(Collectors.toList());
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
