package com.admin.framework.component.utils;

import java.math.BigDecimal;

/**
 * @Author zsw
 * @Description
 * @Date Create in 14:15 2019\9\16 0016
 */
public class BigDecimalUtil {

    /**
     * 是否相等
     * @param b1
     * @param b2
     * @return
     */
    public static boolean equals(BigDecimal b1,BigDecimal b2){
        if(b1 == null || b2 == null){
            return false;
        }
        return b1.compareTo(b2) == 0;
    }


    public static boolean greatEquals(BigDecimal b1,BigDecimal b2){
        if(b1 == null || b2 == null){
            return false;
        }
        return b1.compareTo(b2) > 0;
    }

    public static boolean lessEquals(BigDecimal b1,BigDecimal b2){
        if(b1 == null || b2 == null){
            return false;
        }
        return b1.compareTo(b2) < 0;
    }


    public static void main(String[] args) {
        boolean b = greatEquals(new BigDecimal("2"), new BigDecimal("1"));
        System.out.println(b);
        boolean b1 = lessEquals(new BigDecimal("2"), new BigDecimal("1"));
        System.out.println(b1);
    }


}
