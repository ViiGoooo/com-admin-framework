package com.admin.framework.common.utils;

import com.admin.framework.component.cache.CacheException;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  redis操作工具类
 * @author ZSW
 * @date 2018/11/19
 */
@Component
public class RedisUtil<T> {

    private static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 存储字符串
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    public static boolean setString(String key,String value){
        if(StringUtil.isEmpty(key) || StringUtil.isEmpty(value)){
            return false;
        }
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    /**
     * 存储字符串
     * @param key   key
     * @param value     值
     * @param expire    过期时间
     * @return
     */
    public static boolean setString(String key,String value,Integer expire){
        if(StringUtil.isEmpty(key) || StringUtil.isEmpty(value)){
            return false;
        }
        setString(key,value,expire,DEFAULT_TIME_UNIT);
        return true;
    }

    /**
     * 存储字符串
     * @param key   key
     * @param value     值
     * @param expire    过期时间
     * @return
     */
    public static boolean setString(String key,String value,Integer expire,TimeUnit timeUnit){
        if(StringUtil.isEmpty(key) || StringUtil.isEmpty(value)){
            return false;
        }
        redisTemplate.opsForValue().set(key,value,expire,timeUnit);
        return true;
    }

    /**
     * 设置一个对象
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public static<T> boolean setObj(String key,T t){
        String s = JSONUtil.objToJsonStr(t);
        if(StringUtil.isEmpty(s)){
            return false;
        }
        return setString(key,s);
    }
    /**
     * 设置一个对象
     * @param key
     * @param t
     * @param <T>
     * @param expire
     * @return
     */
    public static<T> boolean setObj(String key,T t,Integer expire){
        String s = JSONUtil.objToJsonStr(t);
        if(StringUtil.isEmpty(s)){
            return false;
        }
        return setString(key,s,expire);

    }
    /**
     * 设置一个对象
     * @param key
     * @param t
     * @param <T>
     * @param expire
     * @return
     */
    public static<T> boolean setObj(String key,T t,Integer expire,TimeUnit timeUnit){
        String s = JSONUtil.objToJsonStr(t);
        if(StringUtil.isEmpty(s)){
            return false;
        }
        return setString(key,s,expire,timeUnit);
    }


    /**
     * 获取字符串
     * @param key
     * @return
     */
    public static String getString(String key){
        Object o = redisTemplate.opsForValue().get(key);
        if(o == null){
            return null;
        }
        return o.toString();
    }

    /**
     * 获取一个对象
     * @param key
     * @param clz
     * @param <T>
     * @return
     */
    public static<T> T getObj(String key,Class<T> clz){
        String string = getString(key);
        if(StringUtil.isEmpty(string)){
            return null;
        }
        return JSONUtil.jsonToObj(string, clz);
    }

    /**
     * 获取list
     * @param key
     * @param clz
     * @param <T>
     * @return
     */
    public static<T> List<T> getList(String key,Class<T> clz){
        String string = getString(key);
        if(StringUtil.isEmpty(string)){
            return null;
        }
        return JSONUtil.jsonToList(string,clz);
    }

    /**
     * 删除key
     * @param key
     */
    public static void remove(String key){
        redisTemplate.delete(key);
    }


}
