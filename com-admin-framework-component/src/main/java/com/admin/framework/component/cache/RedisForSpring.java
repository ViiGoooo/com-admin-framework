package com.admin.framework.component.cache;

import com.admin.framework.component.exception.CacheException;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.StringUtil;
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
public class RedisForSpring<T> {
    private static RedisTemplate<String, Object> redisTemplate;
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisForSpring.redisTemplate = redisTemplate;
    }

    /**
     * 存储字符串
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    public static boolean setString(String key,String value) throws CacheException {
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
     * @throws CacheException
     */
    public static boolean setString(String key,String value,Integer expire) throws CacheException{
        if(StringUtil.isEmpty(key) || StringUtil.isEmpty(value)){
            return false;
        }
        redisTemplate.opsForValue().set(key,value,expire,TimeUnit.SECONDS);
        return true;
    }

    /**
     * 设置一个对象
     * @param key
     * @param t
     * @param <T>
     * @return
     * @throws CacheException
     */
    public static<T> boolean setObj(String key,T t) throws CacheException{
        String s = JSONUtil.objToJsonStr(t.getClass());
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
     * @throws CacheException
     */
    public static<T> boolean setObj(String key,T t,Integer expire) throws CacheException{
        String s = JSONUtil.objToJsonStr(t.getClass());
        if(StringUtil.isEmpty(s)){
            return false;
        }
        return setString(key,s,expire);
    }


    /**
     * 获取字符串
     * @param key
     * @return
     */
    public static String getString(String key) throws CacheException{
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
    public static<T> T getObj(String key,Class<T> clz) throws CacheException{
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
    public static<T> List<T> getList(String key,Class<T> clz) throws CacheException{
        String string = getString(key);
        if(StringUtil.isEmpty(string)){
            return null;
        }
        return JSONUtil.jsonToList(string,clz);
    }


}
