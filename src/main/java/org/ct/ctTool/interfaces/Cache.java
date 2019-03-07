package org.ct.ctTool.interfaces;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @Classname Cache
 * @Description TODO
 * @Date 2019/2/26 12:43
 * @Created by Think
 */

public interface Cache {

    Object get(final String key);

    Set<Object> getAll(final String pattern);

    Set<Object> getAllKeys(final String pattern);

    void set(final String key, final Serializable value, int seconds);

    void set(final String key, final Serializable value);

    Boolean exists(final String key);

    void del(final String key);

    void delAll(final String pattern);

    String type(final String key);

    Boolean expire(final String key, final int seconds);

    Boolean expireAt(final String key, final long unixTime);

    Long ttl(final String key);

    Object getSet(final String key, final Serializable value);

    boolean lock(String key);

    void unlock(String key);

    void hset(String key, Serializable field, Serializable value);

    Object hget(String key, Serializable field);

    void hdel(String key, Serializable field);

    boolean setnx(String key, long value);

    void sadd(String key, Serializable value);

    Set<?> sall(String key);

    boolean sdel(String key, Serializable value);

    Map<Object, Object> hashgetAll(String key);

    public Long increment(String increaseKey, String incrKey, long num);

    boolean setnx(String key, Serializable value);

    Long incr(String key);
}
