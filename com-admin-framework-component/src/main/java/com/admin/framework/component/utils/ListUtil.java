package com.admin.framework.component.utils;

import java.util.List;

/**
 *
 * @author ZSW
 * @date 2019/2/26
 */
public class ListUtil<T> {

    /**
     * 是否为空
     * @param target
     * @return
     */
    public static boolean isEmpty(List<?>target){
        return target == null || target.size() <= 0;
    }

    /**
     * 是否为空
     * @param target
     * @return
     */
    public static boolean isNotEmpty(List<?>target){
        return !isEmpty(target);
    }

    /**
     * 将list转为【分隔符】分隔的字符串
     * @param target
     * @param separate
     * @return
     */
    public static String toStr(List<?>target,String separate){
        String str = "";
        for (Object o:target) {
            str += o + separate;
        }
        str = str.substring(0,str.length() - 1);
        return str;
    }

    /**
     * 将list转为【分隔符】分隔的字符串
     * @param target
     * @param separate
     * @return
     */
    public static String joinForSql(List<?>target,String separate){
        String str = "";
        for (Object o:target) {
            str += "'" + o + "'"+ separate;
        }
        str = str.substring(0,str.length() - 1);
        return str;
    }


    public static<T> T[] toArray(List<T> target){
        return (T[]) target.toArray();
    }

}
