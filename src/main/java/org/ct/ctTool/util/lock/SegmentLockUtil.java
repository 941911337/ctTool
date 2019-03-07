package org.ct.ctTool.util.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Think
 * @Title: SegmentLockUtil
 * @ProjectName easyTool
 * @Description: 分段锁工具类
 * @date 2019/2/28 8:55
 * @Version 1.0
 */
public abstract class SegmentLockUtil {



    private static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash

    private static class SegmentLockUtilHolder{
        private static final Map<Integer, Object> SEGMENT_MAP = new ConcurrentHashMap<>(16);
        static{
            for (int i = 0; i < 16 ; i++) {
                SEGMENT_MAP.put(i,new Object());
            }
        }
    }

    /**
     * 获取对应下标
     * @param h hash值
     * @return 返回下标
     */
    private static int getIndex(int h){
        int hash = (h ^ (h >>> 16)) & HASH_BITS;
        return hash & (16 -1);
    }

    /**
     * 获取分段锁
     * @param key
     * @return
     */
    public static Object getLock(String key){
        return SegmentLockUtilHolder.SEGMENT_MAP.get(getIndex(key.hashCode()));
    }

    private SegmentLockUtil() {
    }
}



