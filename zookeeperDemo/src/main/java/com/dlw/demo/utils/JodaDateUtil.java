package com.dlw.demo.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 日期工具类
 * @author diaoliwei
 * @date 2018/9/13.
 */
public class JodaDateUtil {

    private static final Logger log = LoggerFactory.getLogger(JodaDateUtil.class);

    public static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMATE = "yyyy-MM-dd";

    public static final String DATE_FORMATE_1 = "yyyyMMdd";

    public static final String DEFAULT_DATE_HOUR = "HH:mm:ss";

    public static final String DATE_FORMATE_MONTH_V2 = "yyyy-MM-01";

    public static final DateTimeFormatter DEF_FORMATTER = DateTimeFormat.forPattern(DEFAULT_DATE_FORMATE);

    public static final DateTimeFormatter FORMATTER_Y_M_D = DateTimeFormat.forPattern(DATE_FORMATE);

    /**
     * 默认格式 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2Str(DEFAULT_DATE_FORMATE, date);
    }

    /**
     * long格式转string
     * @param date
     * @return
     */
    public static String date2String(long date) {
        return new DateTime(date).toString(DEFAULT_DATE_FORMATE);
    }

    /**
     * 取得某日期时间的特定表示格式的字符串
     * @param format 时间格式
     * @param date 某日期（Date）
     * @return 某日期的字符串
     */
    public static String date2Str(String format, Date date) {
        if (date == null) {
            return "";
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

    /**
     * 将特定格式的时间字符串转化为Date类型
     * @param format 时间格式
     * @param str 某日期的字符串
     * @return 某日期（Date）
     */
    public static Date strToDate(String format, String str) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = DateTime.parse(str, formatter);
        return dateTime.toDate();
    }

    /**
     * 默认格式yyyy-MM-dd HH:mm:ss String转Date类型
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        return strToDate(DEFAULT_DATE_FORMATE, str);
    }

    /**
     * 将特定格式的时间字符串转化为本地时区的Date类型
     * @param format 时间格式
     * @param str    某日期的字符串
     * @return 某日期（Date）
     */
    public static Date strToLocalDate(String format, String str) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        LocalDateTime locaDateTime = DateTime.parse(str, formatter).toLocalDateTime();
        return locaDateTime.toDate();
    }

    /**
     * format传入的string日期
     * @param time 字符串时间
     * @return date
     */
    public static Date formatTimeToDate(String time) {
        DateTime dateTime = DateTime.parse(time, FORMATTER_Y_M_D);
        return dateTime.toDate();
    }

    /**
     * 取得当前时间几天前或几天后的日期
     * 返回一个yyyy-MM-dd 格式的日期字符串
     * @param interval 传入正数，取几天后，负数取几天前
     * @return 结果
     */
    public static String resetNowDay(int interval) {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(interval);
        return dateTime.toString(FORMATTER_Y_M_D);
    }

    /**
     * 获取当前月的第一天
     * @param date 时间
     * @return 时间
     */
    public static Date getFirstDayCurrentMonth(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.dayOfMonth().withMinimumValue().withTimeAtStartOfDay();
        return dateTime.toDate();
    }

    /**
     * 计算指定日期几天前或几天后的日期
     * 返回一个yyyy-MM-dd HH:mm:ss 格式的日期字符串
     *
     * @param date
     * @param interval 传入正数，取几天后，负数取几天前
     * @return
     */
    public static String resetDay(Date date, int interval) {
        if (date == null) {
            log.error("date is null");
            return null;
        }
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusDays(interval);
        return dateTime.toString(FORMATTER_Y_M_D);
    }

    /*public static void main(String [] args){
        System.out.println(date2Str(DATE_FORMATE_1,new Date()));
        System.out.print(resetDay(new Date(), -1) + " 00:00:00");
    }*/
}
