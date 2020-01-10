package org.ct.ctTool.domain.cache;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description: SimpleCache
 * date: 2020/1/10 16:02
 * author: yzw
 * version: 1.0
 */
public class SimpleCache<K,V> implements Cache<K,V> {

    private static final long serialVersionUID = 7848316234487201117L;

    /**
     * 内部缓存
     */
    private final Map<K, V> CACHE = new WeakHashMap<>();

    /**
     * 锁对象
     */
    private final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock READ_LOCK = LOCK.readLock();
    private final ReentrantReadWriteLock.WriteLock WRITE_LOCK = LOCK.writeLock();

    @Override
    public V get(K key) {
        V value;
        READ_LOCK.lock();
        try {
            value = CACHE.get(key);
        } finally {
            READ_LOCK.unlock();
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        WRITE_LOCK.lock();
        try {
            CACHE.put(key, value);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    @Override
    public V remove(K key) {
        WRITE_LOCK.lock();
        try {
            return CACHE.remove(key);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    @Override
    public void clear() {
        WRITE_LOCK.lock();
        try {
             CACHE.clear();
        } finally {
            WRITE_LOCK.unlock();
        }
    }
}
