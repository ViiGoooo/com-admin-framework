package com.admin.framework.component.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * map操作工具
 * @author ZSW
 * @date 2018/11/19
 */
public class MapUtil {

    public static boolean isEmpty(Map map){
        return map == null || map.size() <= 0;
    }

    /**
     * 是否全部为空
     * @param map
     * @return
     */
    public static boolean isAllEmpty(Map map){
        List l = new ArrayList();
        map.forEach((k,v)->{
            if(v != null && !"".equals(v) ){
              l.add(v);
            }
        });
        return ListUtil.isEmpty(l);
    }

    /**
     * 从map中获取字符串
     * @param map
     * @param key
     * @return
     */
    public static String getString(Map map,Object key){
        return map.get(key) == null ? null : map.get(key).toString();
    }

    /**
     * 从map中获取list
     * @param map
     * @param key
     * @return
     */
    public static<T> List<T> getList(Map map, Object key, Class<T> clz){
        return (List<T>) map.get(key);
    }

    /**
     *  从map中获取int
     * @param map
     * @param key
     * @return
     */
    public static Integer getInteger(Map map,Object key){
        return map.get(key) == null ? null : Integer.valueOf(map.get(key).toString());
    }

    /**
     *  从map中获取int
     * @param map
     * @param key
     * @return
     */
    public static Long getLong(Map map,Object key){
        return map.get(key) == null ? null : Long.valueOf(map.get(key).toString());
    }

    /**
     *  从map中获取布尔值
     * @param map
     * @param key
     * @return
     */
    public static boolean getBoolean(Map map,Object key){
        String value = getString(map, key);
        return StringUtil.isEmpty(value) ? false : Boolean.valueOf(value);
    }

    /**
     * url参数转map
     * @param param
     * @return
     */
    public static Map urlParamToMap(String param){
        String[] split = param.split("&");
        Map map = new HashMap();
        for(String s:split){
            String[] info = s.split("=");
            String key = info[0];
            String value = "";
            if(info.length > 1){
                value = info[1];
            }
            map.put(key,value);
        }
        return map;
    }
}
