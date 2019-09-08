package com.admin.framework.component.utils;

import java.security.MessageDigest;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:13 2019\8\13 0013
 */
public class MD5Util {

    /**
     * md5加密
     * @param dataStr
     * @return
     */
    public static String encrypt(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String encrypt = encrypt("aaa@123");
        System.out.println(encrypt);
    }

}
