package com.admin.framework.component.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json操作工具
 * @author ZSW
 * @date 2018/8/11
 */
public class JSONUtil<T> {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String objToJsonStr(Object object){
        if(object == null){
            return null;
        }
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("json转换异常");
        }
    }

    /**
     * json转对象
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static<T> T jsonToObj(String json,Class<T> clz){
        if (StringUtil.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json,clz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json转换异常");
        }
    }

    /**
     * json转list
     * @param json  json字符串
     * @param clz   准备转换的对象
     * @param <T>
     * @return
     */
    public static<T> List<T> jsonToList(String json,Class<T> clz){
        if (StringUtil.isEmpty(json)) {
            return null;
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clz);
            return (List<T>) mapper.readValue(json,javaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json转换异常");
        }
    }

    /**
     * 对象转map
     * @param o
     * @return
     */
    public static Map objToMap(Object o){
        String s = objToJsonStr(o);
        return jsonToObj(s,Map.class);
    }

    /**
     * 对象转map
     * @param map
     * @param clz
     * @return
     */
    public static<T> T mapToObj(Map map,Class<T> clz) {
        String s = objToJsonStr(map);
        try {
            return mapper.readValue(s,clz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
