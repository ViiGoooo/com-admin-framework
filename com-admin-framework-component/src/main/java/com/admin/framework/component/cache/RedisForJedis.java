package com.admin.framework.component.cache;

import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by ZSW on 2019/1/17.
 */
public class RedisForJedis<T> {
    private Jedis jedis = null;
    private JedisPool pool = null;

    public RedisForJedis(CacheParam cacheParam){
        String host = cacheParam.getHost();
        Integer port = cacheParam.getPort();
        String auth = cacheParam.getAuth();
        Integer db = cacheParam.getDb();
        Integer maxTotal = cacheParam.getMaxTotal();
        Integer maxIdle = cacheParam.getMaxIdle();
        Integer minIdle = cacheParam.getMinIdle();

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数，连接全部用完，进行等待
        if(maxTotal != null){
            poolConfig.setMaxTotal(50);
        }
        // 最小空余数
        if(maxIdle != null){
            poolConfig.setMinIdle(10);
        }
        // 最大空余数
        if(minIdle != null){
            poolConfig.setMaxIdle(30);
        }
        pool = new JedisPool(poolConfig, host,port);
        // 从jedis中获取连接资源，并进行权限认证
        jedis = pool.getResource();
        if(!StringUtil.isEmpty(auth)){
            jedis.auth(auth);
        }
    }

    /**
     * 保存数据到redis
     * @param key
     * @param value
     */
    public void save(String key,String value) throws CacheException {
        jedis.set(key,value);
        close();
    }

    /**
     *
     * @param key
     * @param value
     */
    public void save(String key,Object value) throws CacheException{
        String s = JSONUtil.objToJsonStr(value);
        save(key,s);
        close();
    }

    /**
     *
     * @param key
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T getObject(String key,Class<T> clz) throws CacheException{
        String s = getString(key);
        T t = JSONUtil.jsonToObj(s, clz);
        return t;
    }

    public String getString(String key) throws CacheException{
        String s = jedis.get(key);
        return s;
    }


    public<T> List<T> getList(String key,Class<T> clz) throws CacheException{
        String s = getString(key);
        List<T> ts = JSONUtil.jsonToList(s, clz);
        return ts;
    }



    public void del(String key) throws CacheException{
        jedis.del(key);
        close();
    }

    public void del(List<String> keys) throws CacheException{
        for (String key:keys) {
            del(key);
        }
    }

    /**
     *  关闭资源放回到连接池中
     */
    private void close(){
        jedis.close();
        pool.close();
    }

}
