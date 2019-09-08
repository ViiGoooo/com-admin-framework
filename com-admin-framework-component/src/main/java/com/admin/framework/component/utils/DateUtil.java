package com.admin.framework.component.utils;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
     * @param param 附加参数
     * @return
     */
    public static String genOrderNumber(String param){
        String dateFormat = getDateFormat(new Date(), ORDER_NUMBER_FORMAT);
        return dateFormat+param;
    }


    public static void main(String[] args) {
        String dateFormat = getDateFormat("2018/0912", DateUtil.FILE_FORMAT, CHINESE_FORMAT);
        System.out.println(dateFormat);
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
