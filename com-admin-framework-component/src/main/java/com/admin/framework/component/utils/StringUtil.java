package com.admin.framework.component.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ZSW
 * @date 2018/11/18
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    /**
     * @Title: humpToUnderline
     * @Description: 驼峰转下划线
     * @param str
     * @return
     * @return: String
     */
    public static String humpToUnderline(String str){
        char[] cs = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for(char c:cs){
            String s = String.valueOf(c);
            if(!Character.isLowerCase(c) && !"_".equals(s) && !Character.isDigit(c)){
                String lower = String.valueOf(c).toLowerCase();
                result.append("_" + lower);
            }else{
                result.append(c);
            }
        }
        return isEmpty(result.toString()) ? null:result.toString();
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String underToHump(String str){
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int x = 0 ; x < chars.length ; x++){
            char c = chars[x];
            if(c == '_'){
                String s = String.valueOf(chars[x+1]);
                sb.append(s.toUpperCase());
                x++;
            }else{
                sb.append(c);
            }
        }
        return sb == null ? "":isEmpty(sb.toString()) ? "" : sb.toString();
    }

    /**
     * 去头掐尾
     * @param str
     * @return
     */
    public static String subHeadAndEnd(String str){
        if(isEmpty(str)){
            return "";
        }
        str = str.substring(1,str.length());
        str = str.substring(0,str.length() - 1);
        return str;
    }

    /**
     * string字符串数组转string
     * @param strings
     * @param separate
     * @return
     */
    public static List<String> join(String strings,String separate){
        List<String> result = new ArrayList<>();
        for(String str:strings.split(separate)){
            result.add(str);
        }
        return result;
    }



    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText 源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 获取字符串中指定字符第N次出现的位置
     * @param source    源字符
     * @param target    目标字符
     * @param time      出现的次数
     * @return
     */
    public static int locatedStr(String source,String target,int time){
        Pattern pattern = Pattern.compile(target);
        Matcher findMatcher = pattern.matcher(source);
        int number = 0;
        while(findMatcher.find()) {
            number++;
            if(number == time){
                break;
            }
        }
        return findMatcher.start();
    }

    /**
     * 替换指定位置的字符串
     * @param source
     * @param replacedStr
     * @param target
     * @param index
     * @return
     */
    public static String replacePosition(String source,String replacedStr,String target,int index){
        StringBuilder stringBuilder = new StringBuilder(source);
        stringBuilder.replace(index,index+replacedStr.length(),target);
        return stringBuilder.toString();
    }


}
