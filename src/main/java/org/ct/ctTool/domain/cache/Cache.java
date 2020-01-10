package org.ct.ctTool.domain.cache;

import java.io.Serializable;

/**
 * description: Cache
 * date: 2020/1/10 16:06
 * author: yzw
 * version: 1.0
 */
public interface Cache<K,V> extends Serializable {

    /**
     * 获取值
     * @param key 键
     * @return 值
     */
     V get(K key);

    /**
     * 存数据
     * @param key 键
     * @param value 值
     */
     void put(K key ,V value);

    /**
     *删除数据
     * @param key 键
     * @return 值
     */
     V remove(K key);

    /**
     * 清除数据
     */
    void clear();

}
