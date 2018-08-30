package com.zust.itee.exam.enums;

/**
 * 公告状态枚举类
 *
 * @author liy
 */
public enum AnnouncementTypeEnum {

//    PROVINCE((short) 0, "省水泥办"), CITY((short) 1, "市水泥办"), DISTRICT((short) 2,
//            "区县水泥办"), COMPANY((short) 3, "车企"), DRIVER((short) 4, "驾驶员");

    TRAINNOTICE((short) 3, "培训通知"),
    EXAMNOTICE((short) 2, "考试通知"),
    SAFENOTICE((short) 1, "安全通告"),
    PLATFORMNOTICE((short) 0, "平台通知");

    private Short type;
    private String info;

    private AnnouncementTypeEnum(Short type, String info) {
        this.type = type;
        this.info = info;
    }

    public static AnnouncementTypeEnum stateOf(int index) {
        for (AnnouncementTypeEnum e : values()) {
            if (e.getType() == index) {
                return e;
            }
        }
        return null;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
