package com.admin.framework.component.utils;

import java.util.regex.Pattern;

/**
 * @Author zsw
 * @Description
 * @Date Create in 20:06 2019\8\19 0019
 */
public class RegexUtil {

    public static final String MOBILE = "1\\d{10}";

    public static final String ID_CARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";



    public static boolean match(String regex,String str){
        return Pattern.matches(regex, str);
    }

    /**
     * 手机号校验
     * @param phone
     * @return
     */
    public static boolean mobile(String phone){
        return match(MOBILE,phone);
    }


    public static void main(String[] args) {
        boolean mobile = mobile("18637713605");
        System.out.println(mobile);
    }

}
