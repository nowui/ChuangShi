package com.shanghaichuangshi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDateString(Date dateTime) {
        return dateFormat.format(dateTime);
    }

    public static String getDateTimeString(Date dateTime) {
        return dateTimeFormat.format(dateTime);
    }

    public static Date getDateTime(String dateTime) {
        try {
            return dateTimeFormat.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException("时间格式不正确");
        }
    }

}
