package com.zust.itee.enums;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换规则枚举
 *
 * @author pojun
 */
public enum DateConvertRule {
    YYYY_MM("yyyy-MM", "^\\d{4}-\\d{1,2}$"),
    YYYY_MM_DD("yyyy-MM-dd", "^\\d{4}-\\d{1,2}-\\d{1,2}$"),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,"
            + "2}:\\d{1,2}$");

    private String format;
    private String regex;

    public static Date parseDateFromStr(String dateSource) {
        Date res = null;
        for (DateConvertRule rule : DateConvertRule.values()) {
            if (dateSource.matches(rule.getRegex())) {
                res = parseDate(dateSource, rule.getFormat());
            }
        }
        return res;
    }

    public static Date parseDate(String dateStr, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    DateConvertRule(String format, String regex) {
        this.format = format;
        this.regex = regex;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
