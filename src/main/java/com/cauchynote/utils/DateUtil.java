package com.cauchynote.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @Author Cauchy
 * @ClassName DateUtil
 */
public class DateUtil {

    private static final Calendar CALENDAR = Calendar.getInstance();

    /**
     * 本周第一天
     *
     * @param date 日期
     * @return 本周第一天日期
     */
    public static Date startWeek(Date date) {
        CALENDAR.setTime(date);
        // 获取当前日期是一周的第几天，如果是周日则要 -1 否则就会算到下一周
        int dayOfWeek = CALENDAR.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek -= 1;
        }
        // 计算本周开始时间
        CALENDAR.add(Calendar.DAY_OF_MONTH, 1 - dayOfWeek);
        return CALENDAR.getTime();
    }

    /**
     * 本年第一天
     *
     * @param date 日期
     * @return 本年第一天日期
     */
    public static Date startYear(Date date) {
        CALENDAR.setTime(date);
        CALENDAR.set(Calendar.MONTH, 0);
        CALENDAR.set(Calendar.DAY_OF_MONTH, 1);
        return CALENDAR.getTime();
    }

    /**
     * 获取前 N 个月的第一天
     *
     * @param date 待计算日期
     * @param num    数量 例如上个月为 1 本月为 0
     * @return 第一天日期
     */
    public static Date startBeforeNumMonth(Date date, int num) {
        CALENDAR.setTime(date);
        CALENDAR.add(Calendar.MONTH, -num);
        CALENDAR.set(Calendar.DAY_OF_MONTH, 1);
        return CALENDAR.getTime();
    }

    /**
     * 获取 N 天前（后）的日期
     *
     * @param num 向前为 - 向后为 +
     * @return 日期
     */
    public static Date getNumDayDate(int num) {
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DATE, num);
        return CALENDAR.getTime();
    }

    public static void main(String[] args) {
        Date d = startBeforeNumMonth(new Date(),1);
        System.out.println(d);
    }
}
