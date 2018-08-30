package com.zust.itee.util;

/**
 * 工具类
 *
 * @author pojun
 */
public class FunctionUtil {

    /**
     * 将数字转换成大写字母。 1->A,2->B
     */
    public static String numToChar(Short num) {
        byte[] bytes = String.valueOf(num).getBytes();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append((char) (b + (65 - 49)));
        }
        return stringBuilder.toString();
    }

    public static Integer getDurationHour(Integer duration) {
        if (duration == null || duration == 0) {
            return 0;
        }
        return duration / 3600;
    }

    public static Integer getDurationMinute(Integer duration) {
        if (duration == null || duration == 0) {
            return 0;
        }
        duration -= duration / 3600 * 3600;
        return duration / 60;
    }

    public static Integer getDurationSecond(Integer duration) {
        if (duration == null || duration == 0) {
            return 0;
        }
        duration -= duration / 3600 * 3600;
        duration -= duration / 60 * 60;
        return duration;
    }
}
