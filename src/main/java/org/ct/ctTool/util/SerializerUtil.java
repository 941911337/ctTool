package org.ct.ctTool.util;

import org.ct.ctTool.util.serializer.ProtostuffSerializer;

import java.util.Collection;
import java.util.List;

/**
 * @author Think
 * @Title: SerializerUtil
 * @ProjectName easyTool
 * @Description: 序列化工具类
 * @date 2019/2/2315:19
 * @Version 1.0
 */
public abstract class SerializerUtil {

    /**
     * 序列化
     * @param obj 对象
     * @param cls 类型
     * @return 序列化byte数组
     */
    public static <T> byte[] serialize(Object obj,Class<T> cls) {
        ParamUtil.checkParam(new Object[]{obj});
        byte[] bytes;
        if(obj instanceof Collection<?> ){
            List<T> list = (List<T>) obj;
            bytes = ProtostuffSerializer.serializeList(list,cls);
        }else{
            T t = (T) obj;
            bytes = ProtostuffSerializer.serialize(t,cls);
        }
        return bytes;
    }

    /**
     * 反序列化对象
     * @param bytes byte数组
     * @param cls 类型
     * @return 对象
     */
    public static <T> T deserialize(byte[] bytes, Class<T> cls) {
            return ProtostuffSerializer.deserialize(bytes,cls);
    }

    /**
     * 反序列化对象集合
     * @param bytes byte数组
     * @param cls 类型
     * @return 集合
     */
    public static <T> List<T> deserializeList(byte[] bytes, Class<T> cls) {
        return ProtostuffSerializer.deserializeList(bytes,cls);
    }

    /**
     * 私有化构造函数
     */
    private SerializerUtil() {
    }
}
