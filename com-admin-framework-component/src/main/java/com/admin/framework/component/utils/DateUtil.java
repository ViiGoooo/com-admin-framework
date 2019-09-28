package com.admin.framework.component.utils;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期操作工具
 * @author ZSW
 * @date 2018/8/11
 */
public class DateUtil {

    /**
     * 生成文件目录
     */
    public static final String FILE_FORMAT = "yyyy/MMdd";

    /**
     * 生成唯一单号
     */
    public static final String ORDER_NUMBER_FORMAT = "yyyyMMddHHmmssSSS";


    /**
     * 中文标准时间
     */
    public static final String CHINESE_FORMAT = "yyyy年MM月dd日";


    /**
     * 通用
     */
    public static final String USUAL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String USUAL_FORMAT_ALL = "yyyy-MM-dd";

    public static final String[] WEEK_CHINESE = {"周日","周一","周二","周三","周四","周五","周六"};


    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_HOUR = "yyyy-MM-dd HH";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String MONTH_FORMAT = "MM";
    public static final String DAY_FORMAT = "dd";
    public static final String MONTH_DAY_FORMAT = "MM-dd";
    public static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";
    public static final String UUID_TIME_FORMAT = "yyyyMMddHHmmssSSS";


    public static String getCurrentDateFormat(String format){
        return getDateFormat(new Date(),format);
    }


    public static String getDateFormat(Date date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String getDateFormat(String dateStr, String originalFormat,String newFormat){
        Date f = format(dateStr, originalFormat);
        return getDateFormat(f,newFormat);
    }

    public static Date format(String dateStr,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取星期的中文
     * @param week
     * @return
     */
    public static String getWeekChinese(Integer week){
        return WEEK_CHINESE[week-1];
    }


    /**
     * 是否为同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1,Date date2){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int y1 = calendar.get(Calendar.YEAR);
        int d1 = calendar.get(Calendar.DAY_OF_YEAR);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int y2 = calendar2.get(Calendar.YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        return y1 == y2 && d1 == d2;
    }
    public static boolean isSameDate(Date date1,String dateStr){
        Date parse = format(dateStr,USUAL_FORMAT);
        return isSameDate(date1,parse);
    }
    public static boolean isSameDate(String dateStr1,String dateStr2){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(USUAL_FORMAT);
            Date date1 = simpleDateFormat.parse(dateStr1);
            Date date2 = simpleDateFormat.parse(dateStr2);
            return isSameDate(date1, date2);
        }catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取时间
     * @param date
     * @return
     */
    /**
     * 获取时间
     * @return
     */
    public static DateBean getDateBean(){
        return getDateBean(new Date());
    }
    public static DateBean getDateBean(Date date){
        //获取默认选中的日期的年月日星期的值，并赋值
        Calendar calendar = Calendar.getInstance();
        //设置当前日期
        calendar.setTime(date);

        //获取年份
        String yearStr = calendar.get(Calendar.YEAR)+"";
        //获取月份
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthStr = month < 10 ? "0" + month : month + "";
        //获取日
        int day = calendar.get(Calendar.DATE);
        String dayStr = day < 10 ? "0" + day : day + "";
        int week = calendar.get(Calendar.DAY_OF_WEEK);

        DateBean dateBean = new DateBean();
        dateBean.setYear(yearStr);
        dateBean.setMonth(monthStr);
        dateBean.setDay(dayStr);
        dateBean.setWeek(week);
        dateBean.setWeekChinese(getWeekChinese(week));
        return dateBean;
    }


    /**
     * 生成唯一单号
     * @return
     */
    public static String genOrderNumber(){
        return getDateFormat(new Date(), ORDER_NUMBER_FORMAT);
    }

    /**
     * 生成唯一单号
     * @param prefix 附加参数
     * @return
     */
    public static String genOrderNumber(String prefix){
        return prefix+genOrderNumber();
    }






    /**
     * 获取当前毫秒值
     */
    public static long getCurrentTimeMillis() {

        return System.currentTimeMillis();
    }

    public static String getCurrentTime(String formatter) {

        SimpleDateFormat format = new SimpleDateFormat(formatter);
        return format.format(new Date());
    }

    public static String getCurrentTime() {

        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        return format.format(new Date());
    }

    /**
     * 获取当前年份
     *
     * @return 时间格式化后的字符串
     */
    public static String getYear() {

        return format(new Date(), YEAR_FORMAT);
    }

    /**
     * 获取当前月份
     *
     * @return 时间格式化后的字符串
     */
    public static String getMonth() {

        return format(new Date(), MONTH_FORMAT);
    }

    /**
     * 获取当前天数
     *
     * @return 时间格式化后的字符串
     */
    public static String getDay() {

        return format(new Date(), DAY_FORMAT);
    }

    /**
     * @param date
     *            日期对象
     * @param format
     *            格式化样式（比如：yyyy-MM-dd HH:mm:ss）
     * @return 格式化的字符串
     */
    public static String format(Date date, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return date == null ? "" : dateFormat.format(date);
    }

    /**
     * 获取当前月份的最大天数
     *
     * @return
     */
    public static int getMonthMaximum() {

        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当月的第一天
     *
     * @return 返回格式：yyyy-MM-01
     */
    public static String getMonthFirstDay() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01");
        return format.format(new Date());
    }

    /**
     * 获取当月的最后一天
     *
     * @return 返回格式：yyyy-MM-31
     */
    public static String getMonthLastDay() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-" + getMonthMaximum());
        return format.format(new Date());
    }

    /**
     * 获取两个日期的天数差
     *
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return 差值
     */
    public static long getDateDiffer(String startTime, String endTime) {

        Date startDate = strDateToDate(startTime);
        Date endDate = strDateToDate(endTime);
        return getDateDiffer(startDate, endDate);
    }

    /**
     * 获取两个日期的天数差
     *
     * @param startDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return 差值
     */
    public static long getDateDiffer(Date startDate, Date endDate) {

        if (startDate == null || endDate == null) {
            return (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
        } else {
            throw new NullPointerException("操作的日期参数为空。");
        }
    }

    /**
     * 将String类型转换为Date类型
     *
     * @param dateStr
     *            预转换的字符串
     * @return 转换后得到的日期对象
     * @throws IllegalArgumentException
     *             传入的字符串不是时间的格式
     */
    public static Date strDateToDate(String dateStr) {

        SimpleDateFormat formatter = null;

        if (!StringUtil.isEmpty(dateStr)) {
            if (dateStr.length() == 10) {

                formatter = new SimpleDateFormat(DATE_FORMAT);
            } else {

                formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
            }

            try {

                return formatter.parse(dateStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException(dateStr + "：格式不正确。");
            }
        } else {
            throw new IllegalArgumentException("参数为空。");
        }
    }

    /**
     * 将Date类型转换为String类型
     *
     * @param date
     *            预转换的字符串
     * @return 转换后得到的日期对象
     * @throws IllegalArgumentException
     *             传入的日期不能对象为空
     */
    public static String dateToString(Date date) {

        if (null != date) {

            SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);

            return formatter.format(date);
        } else {
            throw new IllegalArgumentException("参数为空。");
        }
    }

    /**
     * 获取几个月以后或以前的日期
     *
     * @param month
     *            计算过去的时间用负数，否则用正数
     * @return 计算后的日期
     */
    public static String getMonthDifference(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (month == 0) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else {
            calendar.add(Calendar.MONTH, month);
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(calendar.getTime());
    }

    public static String getMonthDifference(int month) {

        return getMonthDifference(new Date(), month);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 得到指定日期是星期几
     *
     * @param date
     *            指定的日期
     * @return 星期几
     */
    public static String getChineseWeek(Date date) {

        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];
    }

    /**
     * 获得指定日期的下一个星期一的日期
     *
     * @param date
     *            指定日期
     * @return 下一个星期一的日期
     */
    public static Date getNextMonday(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        do {
            calendar.add(Calendar.DATE, 1);
        } while (calendar.get(Calendar.DAY_OF_WEEK) != 2);
        return calendar.getTime();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 功能描述：返回毫秒
     *
     * @param date
     *            日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 功能描述：日期相加
     *
     * @param date
     *            Date 日期
     * @param day
     *            int 天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strDateToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    public static int differentDays(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            return day2-day1;
        }
    }

    public static void main(String[] args) {
        Date date = strDateToDate("2019-08-25 12:31:24");
        int i = differentDays(new Date(), date);
        System.out.println(i);
    }


    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = strDateToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    @Data
    public static class DateBean{
        private String year;
        private String month;
        private String day;
        private Integer week;
        private String weekChinese;
    }

}
