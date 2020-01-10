package org.ct.ctTool.util;

import org.ct.ctTool.exception.ParamException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * description: MapUtil
 * date: 2020/1/9 16:16
 * author: yzw
 * version: 1.0
 */
public class MapUtil {
    /**
     * 默认初始大小
     */
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * 默认增长因子，当Map的size达到 容量*增长因子时，开始扩充Map
     */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public  static Map<Object,Object> of(Object...objects){
        Map<Object,Object> map =  newHashMap();
        if(objects.length % 2 != 0 ){
            throw new ParamException("参数个数应是偶数");
        }
        for (int i = 0; i < objects.length ; i=i+2) {
            map.put(objects[i],objects[i+1]);
        }
        return map;
    }

    /**
     * 生成hashMap对象
     * @return hashMap
     */
    public static <K,V> Map<K,V> newHashMap(){
        return new HashMap<>();
    }

    /**
     * 生成 hashMap 对象
     * @param size 尺寸
     * @param isOrder 是否有序
     * @return
     */
    public static <K,V> Map<K,V> newHashMap(int size, boolean isOrder){
        int initialCapacity = (int) (size / DEFAULT_LOAD_FACTOR) + 1;
        return isOrder ? new LinkedHashMap<>(initialCapacity) : new HashMap<>(initialCapacity);
    }


}
