package com.zust.itee.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * TODO 类描述
 *
 * @author pojun
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return DateConvertRule.parseDateFromStr(s);
    }

    private enum DateConvertRule {
        YYYY_MM("yyyy-MM", "^\\d{4}-\\d{1,2}$"),
        YYYY_MM_DD("yyyy-MM-dd", "^\\d{4}-\\d{1,2}-\\d{1,2}$"),
        YYYY_MM_DD_HH_MM("yyyy-MM-dd hh:mm", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd hh:mm:ss", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,"
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
}
