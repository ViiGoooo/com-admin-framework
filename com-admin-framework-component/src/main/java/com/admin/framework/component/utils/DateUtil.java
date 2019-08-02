package com.admin.framework.component.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作工具
 * @author ZSW
 * @date 2018/8/11
 */
public class DateUtil {

    public static final String FILE_FORMAT = "YYYY/MMdd";

    public static final String USUAL_FORAMT = "YYYY-MM-dd HH:mm:ss";

    public static String getCurrentDateFormat(String format){
        return getDateFormat(new Date(),format);
    }

    public static String getDateFormat(Date date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        String currentDateFormat = getCurrentDateFormat(USUAL_FORAMT);
        System.out.print(currentDateFormat);
    }


}
