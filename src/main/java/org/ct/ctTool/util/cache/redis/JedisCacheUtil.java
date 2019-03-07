package org.ct.ctTool.util.cache.redis;

import com.alibaba.fastjson.JSON;
import org.ct.ctTool.interfaces.Cache;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @author Think
 * @Title: JedisCacheUtil
 * @ProjectName easyTool
 * @Description: TODO
 * @date 2019/2/26 12:52
 * @Version 1.0
 */
public class JedisCacheUtil implements Cache {

    @Override
    public Object get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = JedisManager.getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return value;

    }

    @Override
    public Set<Object> getAll(String pattern) {
        return null;
    }

    @Override
    public Set<Object> getAllKeys(String pattern) {
        return null;
    }

    @Override
    public void set(String key, Serializable value, int seconds) {
        Jedis jedis = JedisManager.getJedis();
        try {
            String json = JSON.toJSONString(value);
            jedis.set(key,json);
            jedis.expire(key,seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Override
    public void set(String key, Serializable value) {

    }

    @Override
    public Boolean exists(String key) {
        return null;
    }

    @Override
    public void del(String key) {

    }

    @Override
    public void delAll(String pattern) {

    }

    @Override
    public String type(String key) {
        return null;
    }

    @Override
    public Boolean expire(String key, int seconds) {
        return null;
    }

    @Override
    public Boolean expireAt(String key, long unixTime) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Object getSet(String key, Serializable value) {
        return null;
    }

    @Override
    public boolean lock(String key) {
        return false;
    }

    @Override
    public void unlock(String key) {

    }

    @Override
    public void hset(String key, Serializable field, Serializable value) {

    }

    @Override
    public Object hget(String key, Serializable field) {
        return null;
    }

    @Override
    public void hdel(String key, Serializable field) {

    }

    @Override
    public boolean setnx(String key, long value) {
        return false;
    }

    @Override
    public void sadd(String key, Serializable value) {

    }

    @Override
    public Set<?> sall(String key) {
        return null;
    }

    @Override
    public boolean sdel(String key, Serializable value) {
        return false;
    }

    @Override
    public Map<Object, Object> hashgetAll(String key) {
        return null;
    }

    @Override
    public Long increment(String increaseKey, String incrKey, long num) {
        return null;
    }

    @Override
    public boolean setnx(String key, Serializable value) {
        return false;
    }

    @Override
    public Long incr(String key) {
        return null;
    }
}
