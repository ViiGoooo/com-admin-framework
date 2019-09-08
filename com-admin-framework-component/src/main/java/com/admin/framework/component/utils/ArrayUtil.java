package com.admin.framework.component.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组操作工具
 * @author ZSW
 * @date 2019/1/13
 */
public class ArrayUtil {

    /**
     * 数组转对象
     * @param target
     * @return
     */
    public static<T> List<T> toList(T[] target){
        List<T> result = new ArrayList<>();
        for(Object obj:target){
            result.add((T) obj);
        }
        return result;
    }

    /**
     * 数组转为用指定分隔符分隔的字符串
     * @param target
     * @param separate
     * @return
     */
    public static String toStr(Object[] target,String separate){
        String str = "";
        for (Object o:target) {
            str += o + separate;
        }
        str = str.substring(0,str.length() - 1);
        return str;
    }

    public static boolean isEmpty(Object[] arr){
        if(arr != null && arr.length > 0){
            boolean f = true;
            for(Object o:arr){
                if(o != null){
                    f = false;
                }
            }
            return f;
        }else{
            return true;
        }
    }

    /**
     * 将list转为【分隔符】分隔的字符串
     * @param target
     * @param separate
     * @return
     */
    public static<T> String joinForSql(T[] target,String separate){
        String str = "";
        for (T o:target) {
            str += "'" + o + "'"+ separate;
        }
        str = str.substring(0,str.length() - 1);
        return str;
    }


}
