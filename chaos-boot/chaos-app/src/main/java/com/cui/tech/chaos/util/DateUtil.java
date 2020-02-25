package com.cui.tech.chaos.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间处理工具类
 *
 * @author xiaoshiyilang
 * @date 2018/11/13
 */
public class DateUtil {

    public static String FORMAT_SHORT = "yyyy-MM-dd";
    public static String FORMAT_MM = "yyyy-MM-dd HH:mm";
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    public static String FORMAT_DATE_CN = "yyMMdd";
    public static String FORMAT_DATE_LONG = "yyyyMMdd";
    public static String START_TIME = " 00:00:00";
    public static String END_TIME = " 23:59:59";

    /**
     * 查询当前月的天数
     *
     * @return
     */
    public static int getDaysOfMonths() {
        Date date1 = getFirstDayOfMonth(getCurrentStartTime());
        Date date2 = addMonth(date1, 1);
        int days = compareDate(date1, date2, 0);
        return days;
    }

    /**
     * 前一天的开始时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getYesterdayStartTime(Date date) {

        String str = parseDate(date, FORMAT_SHORT);

        Date parse = parse(str + START_TIME);
        Date yesterDay = addDay(parse, -1);

        return yesterDay;
    }

    /**
     * 前一天的结束时间
     *
     * @param date
     * @return
     */
    public static Date getYesterdayEndTime(Date date) {
        String str = parseDate(date, FORMAT_SHORT);

        Date parse = parse(str + END_TIME);
        Date yesterDay = addDay(parse, -1);

        return yesterDay;
    }

    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date getCurrentStartTime() {
        String dateStr = getCurrentDate();
        return parse(dateStr + " 00:00:00");
    }

    /**
     * 获取当天的结束时间
     */
    public static Date getCurrentEndTime() {
        String dateStr = getCurrentDate();
        return parse(dateStr + " 23:59:59");
    }

    /**
     * 在日期上增加年
     *
     * @param date 日期
     * @param n    要增加的年数
     * @return
     */
    public static Date addYear(Date date, int n) {
        if (date == null || n == 0) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据预设格式返回当前日期
     *
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据用户格式返回当前日期
     *
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null) {
            return "";
        }
        return format(date, getDatePattern());
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * @param startDate
     * @param endDate
     * @param pattern   yyyy-MM-dd or yyyy-MM or yyyy
     * @return
     */
    public static List<String> getBetweenTime(Date startDate, Date endDate, String pattern) {
        int type = 0;
        if (pattern.contains("dd")) {
            type = Calendar.DATE;
        } else if (pattern.contains("MM")) {
            type = Calendar.MONTH;
        } else if (pattern.contains("yyyy")) {
            type = Calendar.YEAR;
        }
        String strStartDate = format(startDate, pattern);
        String strEndDate = format(endDate, pattern);
        Date theEndDate = parse(strEndDate, pattern);

        Date currDate = parse(strStartDate, pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        List<String> ret = new ArrayList<String>();
        while (!cal.getTime().after(theEndDate)) {
            ret.add(format(cal.getTime(), pattern));
            cal.add(type, 1);
        }
        return ret;
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     * @throws ParseException
     */
    public static Date parse(Date date, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(df.format(date));
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上减去天数
     *
     * @param date 日期
     * @param n    要减去的天数
     * @return
     */
    public static Date subDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        if (date == null || n == 0) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加小时
     *
     * @param date 日期
     * @param n    要增加的小时
     * @return
     */
    public static Date addHour(Date date, int n) {
        if (date == null || n == 0) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, n);
        return cal.getTime();
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 获取季度(中文)
     *
     * @param date
     * @return
     */
    public static String getSeasonCn(Date date) {
        String season = null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = "一";
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = "二";
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = "三";
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = "四";
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 获取季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * countDays(计算连个时间点相隔多少天)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int countDays(Date date1, Date date2) {

        Date dateA, dateB, temp;

        try {
            dateA = parse(date1, "yyyy-MM-dd");
            dateB = parse(date2, "yyyy-MM-dd");
        } catch (ParseException e) {
            return 0;
        }

        if (dateA.getTime() == dateB.getTime()) {
            return 0;
        }

        if (dateA.getTime() < dateB.getTime()) {
            temp = dateA;
            dateA = dateB;
            dateB = temp;
        }

        Calendar cA = Calendar.getInstance();
        Calendar cB = Calendar.getInstance();
        cA.setTime(dateA);
        cB.setTime(dateB);

        long t1 = dateA.getTime();
        long t2 = dateB.getTime();

        return (int) (t1 / 1000 - t2 / 1000) / 3600 / 24;
    }

    /**
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
     * @param date2 被比较的时间 为
     * @param stype 返回值类型 0为多少天，1为多少个月，2为多少年
     * @return
     */
    public static int compareDate(Date date1, Date date2, int stype) {
        int n = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
            n++;
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
            } else {
                c1.add(Calendar.DATE, 1); // 比较天数，日期+1
            }
        }
        n = n - 1;
        if (stype == 2) {
            n = (int) n / 365;
        }
        return n;
    }

    /**
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
     * @param date2 被比较的时间 为
     * @param stype 返回值类型 0为多少天，1为多少个月，2为多少年
     * @return
     */
    public static int compareDate(String date1, String date2, int stype) {
        int n = 0;
        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e3) {
            // log.error("时间比较错误：",e3);
        }

        while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
            n++;
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
            } else {
                c1.add(Calendar.DATE, 1); // 比较天数，日期+1
            }
        }

        n = n - 1;

        if (stype == 2) {
            n = (int) n / 365;
        }

        return n;
    }

    /**
     * compareDate(比较时间大小)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer compareDate(Date date1, Date date2) {
        if (date1 == null || null == date2) {
            return 0;
        }
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }

    /**
     * 得到当前日期 yyMMDD
     *
     * @return
     */
    public static String getCurrentDateyyMMDD() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyMMdd");
        return simple.format(date);
    }

    public static int getMonths(String date1, String date2) {
        int year, month;
        Date dateA, dateB, temp;

        dateA = parse(date1, "yyyy-MM-dd");
        dateB = parse(date2, "yyyy-MM-dd");

        if (dateA.getTime() == dateB.getTime()) {
            return 0;
        }

        if (dateA.getTime() < dateB.getTime()) {
            temp = dateA;
            dateA = dateB;
            dateB = temp;
        }

        Calendar cA = Calendar.getInstance();
        Calendar cB = Calendar.getInstance();
        cA.setTime(dateA);
        cB.setTime(dateB);

        year = cA.get(Calendar.YEAR) - cB.get(Calendar.YEAR);// 年
        month = cA.get(Calendar.MONTH) - cB.get(Calendar.MONTH);// 月

        if (year > 0) {
            if (month > 0) {
                if (cB.get(Calendar.DATE) > cA.get(Calendar.DATE)) {
                    month--;
                } else if (cB.get(Calendar.DATE) == cA.get(Calendar.DATE)) {

                } else {

                }
            } else if (month == 0) {
                if (cB.get(Calendar.DATE) > cA.get(Calendar.DATE)) {
                    month = -1;
                } else if (cB.get(Calendar.DATE) == cA.get(Calendar.DATE)) {

                } else {

                }
            } else {
                if (cA.get(Calendar.DATE) > cB.get(Calendar.DATE)) {

                } else if (cA.get(Calendar.DATE) == cB.get(Calendar.DATE)) {

                } else {
                    month--;
                }
            }
        } else {
            if (month > 0) {
                if (cB.get(Calendar.DATE) > cA.get(Calendar.DATE)) {
                    month--;
                } else if (cB.get(Calendar.DATE) == cA.get(Calendar.DATE)) {

                } else {

                }
            }
        }

        return year * 12 + month;
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 获取下季度第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfNextQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTime(getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date)));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 获取下季度最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfNextQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 3);
        calendar.setTime(getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(calendar.getTime())));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 获取当前时间之前或之后几小时 hour
     *
     * @param hour    小时
     * @param date    时间
     * @param pattern 时间格式
     * @return
     * @throws ParseException
     */
    public static String getTimeByHour(int hour, Date date, String pattern) {

        Calendar calendar = Calendar.getInstance();
        if (pattern == null) {
            pattern = FORMAT_LONG;
        }
        try {
            calendar.setTime(parse(date, pattern));
        } catch (ParseException e) {
            // log.error("获取当前时间之前或之后几小时错误：",e);
        }
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return format(calendar.getTime());
    }

    /**
     * countHours(计算连个时间点相隔多少小时)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int countHours(Date date1, Date date2) {

        Date dateA, dateB, temp;

        try {
            dateA = parse(date1, "yyyy-MM-dd HH");
            dateB = parse(date2, "yyyy-MM-dd HH");
        } catch (ParseException e) {
            return 0;
        }

        if (dateA.getTime() == dateB.getTime()) {
            return 0;
        }

        if (dateA.getTime() < dateB.getTime()) {
            temp = dateA;
            dateA = dateB;
            dateB = temp;
        }

        Calendar cA = Calendar.getInstance();
        Calendar cB = Calendar.getInstance();
        cA.setTime(dateA);
        cB.setTime(dateB);

        long t1 = dateA.getTime();
        long t2 = dateB.getTime();

        return (int) (t1 / 1000 - t2 / 1000) / 3600;
    }

    /**
     * countMinutes(计算连个时间点相隔多少分钟)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int countMinutes(Date date1, Date date2) {

        Date dateA, dateB, temp;

        try {
            dateA = parse(date1, "yyyy-MM-dd HH:mm");
            dateB = parse(date2, "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            return 0;
        }

        if (dateA.getTime() == dateB.getTime()) {
            return 0;
        }

        if (dateA.getTime() < dateB.getTime()) {
            temp = dateA;
            dateA = dateB;
            dateB = temp;
        }

        Calendar cA = Calendar.getInstance();
        Calendar cB = Calendar.getInstance();
        cA.setTime(dateA);
        cB.setTime(dateB);

        long t1 = dateA.getTime();
        long t2 = dateB.getTime();

        return (int) (t1 / 1000 - t2 / 1000) / 60;
    }

    /**
     * 计算两个时间相隔的秒数
     *
     * @param date1
     * @param date2
     * @return
     * @author xiaoshiyilang
     * @date 2018/10/25
     */
    public static Long countSeconds(Date date1, Date date2) {
        Date dateA, dateB, temp;

        try {
            dateA = parse(date1, "yyyy-MM-dd HH:mm");
            dateB = parse(date2, "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            return 0L;
        }

        if (dateA.getTime() == dateB.getTime()) {
            return 0L;
        }

        if (dateA.getTime() < dateB.getTime()) {
            temp = dateA;
            dateA = dateB;
            dateB = temp;
        }

        Calendar cA = Calendar.getInstance();
        Calendar cB = Calendar.getInstance();
        cA.setTime(dateA);
        cB.setTime(dateB);

        long t1 = dateA.getTime();
        long t2 = dateB.getTime();

        return (Long) (t1 - t2) / 1000;
    }

    /**
     * 将秒数解析为天、时、分、秒
     *
     * @param second
     * @return
     */
    public static String parseSeconds(Long second) {
        StringBuffer strbuf = new StringBuffer();
        // 计算总天数
        int days = (int) (second / (24 * 60 * 60));
        // 计算剩余小时数
        int hours = (int) (second % (24 * 60 * 60) / (60 * 60));
        // 计算剩余分钟数
        int minutes = (int) (second % (24 * 60 * 60) % (60 * 60) / 60);
        // 计算剩余秒数
        int seconds = (int) (second % (24 * 60 * 60) % (60 * 60) % 60);
        if (days > 0) {
            strbuf.append(days + "天");
        }
        if (hours > 0) {
            strbuf.append(hours + "时");
        }
        if (minutes > 0) {
            strbuf.append(minutes + "分");
        }
        if (seconds > 0) {
            strbuf.append(seconds + "秒");
        }
        return strbuf.toString();
    }

    /**
     * 转换时间格式 将Date类型转换为字符串类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String parseDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formateDate = sdf.format(date);
        return formateDate;
    }

    /**
     * 获取当前日期 yyyyyMMdd
     *
     * @return
     */
    public static String getCurrentData() {
        return parseDate(new Date(), FORMAT_DATE_LONG);
    }

    /**
     * 转换时间格式 将字符串类型转换为Date类型
     *
     * @param date
     * @param format
     * @return
     */
    public static Date parseDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date formateDate = null;
        try {
            formateDate = sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return formateDate;
    }

    /**
     * 日期加钟
     *
     * @param date
     * @param n
     * @return
     */
    public static Date addMinute(Date date, int n) {
        if (date != null && n != 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(12, n);
            return cal.getTime();
        } else {
            return date;
        }
    }

}
