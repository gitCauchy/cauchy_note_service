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

    private static Calendar calendar = Calendar.getInstance();

    /**
     * 本周第一天
     *
     * @param date 日期
     * @return 本周第一天日期
     */
    public static Date startWeek(Date date) {
        calendar.setTime(date);
        // 获取当前日期是一周的第几天，如果是周日则要 -1 否则就会算到下一周
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek -= 1;
        }
        // 计算本周开始时间
        calendar.add(Calendar.DAY_OF_MONTH, 1 - dayOfWeek);
        return calendar.getTime();
    }

    /**
     * 本年第一天
     *
     * @param date 日期
     * @return 本年第一天日期
     */
    public static Date startYear(Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取前 N 个月的第一天
     *
     * @param date 待计算日期
     * @param N    数量 例如上个月为 1 本月为 0
     * @return 第一天日期
     */
    public static Date startBeforeNMonth(Date date, int N) {
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, -N + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取 N 天前（后）的日期
     *
     * @param N 向前为 - 向后为 +
     * @return 日期
     */
    public static Date getNDayDate(int N) {
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, N);
        return calendar.getTime();
    }
}
