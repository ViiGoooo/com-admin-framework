package com.admin.framework.component.utils;

import java.util.Random;

/**
 * 生成随机字符的工具
 * @Author zsw
 * @Description
 * @Date Create in 11:14 2019\8\13 0013
 */
public class RandomUtil {
    public static final String LOWERCASE_STR = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBER = "0123456789";

    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    public static String getAllRandomStr(Integer length){
        String temp = LOWERCASE_STR + UPPERCASE_STR +NUMBER;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++) {
            char ch=temp.charAt(new Random().nextInt(temp.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    public static String getAllRandomStr(Integer length,String temp){
        StringBuilder sb = new StringBuilder(length);
        for(int i=0;i<length;i++) {
            char ch=temp.charAt(new Random().nextInt(temp.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 获取指定范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static Long nextLong(Long min,Long max){
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static void main(String[] args) {
        long l = RandomUtil.nextLong(100L, 999L);
        System.out.println(l);
    }


}
