package com.dlw.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LunarCalendarUtil {

    private static int year;

    private static int month;

    private static int day;

    private static boolean leap;

    final static String chineseNumber[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    static SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd");

   public static final int[] calendar_spring = {01, 02, 03, 04, 05, 06};

    public static final int[] calendar_May = {05, 06, 07};

    public static final int[] calendar_Aug = {15, 16, 17};

    public static final int[] calendar_Jan = {01, 02, 03};

    public static final int[] calendar_May_2 = {01, 02, 03, 04};

    public static final int[] calendar_OCT = {01, 02, 03, 04, 05, 06, 07};

    final static long[] lunarInfo = new long[]{0x04bd8, 0x04ae0, 0x0a570,
            0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0,
            0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50,
            0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566,
            0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0,
            0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4,
            0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550,
            0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950,
            0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260,
            0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0,
            0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40,
            0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3,
            0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960,
            0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0,
            0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9,
            0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0,
            0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65,
            0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0,
            0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2,
            0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};

    /**
     * 农历y年的总天数
     * @param y
     * @return
     */
    private static int yearDays(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[y - 1900] & i) != 0)
                sum += 1;
        }
        return (sum + leapDays(y));
    }

    /**
     * 农历y年闰月的天数
     * @param y
     * @return
     */
    private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            if ((lunarInfo[y - 1900] & 0x10000) != 0) {
                return 30;
            } else {
                return 29;
            }
        } else {
            return 0;
        }
    }

    /**
     * 农历y年闰哪个月1-12 , 没闰传回 0
     * @param y
     * @return
     */
    private static int leapMonth(int y) {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * 农历y年m月的总天数
     * @param y
     * @param m
     * @return
     */
     private static int monthDays(int y, int m) {
        if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
            return 29;
        else
            return 30;
    }


    public static String calendar(Calendar cal) {
        @SuppressWarnings("unused")
        int yearCyl, monCyl, dayCyl;
        int leapMonth = 0;
        Date baseDate = null;
        try {
            baseDate = chineseDateFormat.parse("1900-1-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 求出和1900年1月31日相差的天数
        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        dayCyl = offset + 40;
        monCyl = 14;
        // 用offset减去每农历年的天数
        // 计算当天是农历第几天
        // i最终结果是农历的年份
        // offset是当年的第几天
        int iYear, daysOfYear = 0;
        for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
            daysOfYear = yearDays(iYear);
            offset -= daysOfYear;
            monCyl += 12;
        }
        if (offset < 0) {
            offset += daysOfYear;
            iYear--;
            monCyl -= 12;
        }
        // 农历年份
        year = iYear;
        yearCyl = iYear - 1864;
        leapMonth = leapMonth(iYear); // 闰哪个月,1-12
        leap = false;
        // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
        int iMonth, daysOfMonth = 0;
        for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
            // 闰月
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
                --iMonth;
                leap = true;
                daysOfMonth = leapDays(year);
            } else
                daysOfMonth = monthDays(year, iMonth);
            offset -= daysOfMonth;
            // 解除闰月
            if (leap && iMonth == (leapMonth + 1))
                leap = false;
            if (!leap)
                monCyl++;
        }
        // offset为0时，并且刚才计算的月份是闰月，要校正
        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (leap) {
                leap = false;
            } else {
                leap = true;
                --iMonth;
                --monCyl;
            }
        }
        // offset小于0时，也要校正
        if (offset < 0) {
            offset += daysOfMonth;
            --iMonth;
            --monCyl;
        }
        month = iMonth;
        day = offset + 1;
        return year + "-" + (leap ? "闰" : "") + chineseNumber[month - 1] + "-"+day;
    }

    public static void main(String[] args) {
        try {
            int result = checkDay("2022-01-03");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int checkDay(String date) throws ParseException {
        int ans = 0;  //默认是0，代表非节假日；1代表是节假日
        Calendar today = Calendar.getInstance();
        today.setTime(chineseDateFormat.parse(date));

        if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return 1;
        }

        String yang_time = chineseDateFormat.format(today.getTime());
        int yang_month = Integer.valueOf(yang_time.substring(5,7));
        int yang_day = Integer.valueOf(yang_time.substring(8));

        String yin_time=calendar(today);
        int yin_month= Integer.valueOf(yin_time.substring(5,7));
        int yin_day= Integer.valueOf(yin_time.substring(8));

        if (veryfyYang(yang_month, yang_day)){
            ans = 1;
        } else if (veryfyYin(yin_month, yin_day)){
            ans = 1;
        }
        return ans;
    }

    public static boolean veryfyYang(int month,int day){
        boolean isHoliday = false;
        //判断每个阳历月内的节日
        if (month == 1){
            for (int i = 0; i < calendar_Jan.length; i ++) {
                if (day == calendar_Jan[i]){
                    return true;
                }
            }
        }

        if (month == 5) {
            for (int i = 0; i < calendar_May_2.length; i ++) {
                if (day == calendar_May_2[i]){
                    return true;
                }
            }
        }

        if (month == 10) {
            for (int i = 0; i < calendar_OCT.length; i ++) {
                if (day == calendar_OCT[i]){
                    return true;
                }
            }
        }
        return isHoliday;
    }

    public static boolean veryfyYin(int month,int day){
        boolean isHoliday = false;

        if (month == 1){
            for (int i = 0; i < calendar_spring.length; i ++) {
                if (day == calendar_spring[i]){
                    return true;
                }
            }
        }
        if (month == 5){
            for (int i = 0; i < calendar_May.length; i ++) {
                if (day == calendar_May[i]){
                    return true;
                }
            }
        }
        if (month == 8){
            for (int i = 0; i < calendar_Aug.length; i ++) {
                if (day == calendar_Aug[i]){
                    return true;
                }
            }
        }
        return isHoliday;
    }
}