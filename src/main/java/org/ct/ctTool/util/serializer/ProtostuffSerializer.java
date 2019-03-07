package org.ct.ctTool.util.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Think
 * @Title: ProtostuffSerializer
 * @ProjectName easyTool
 * @Description: Protostuff 序列化工具类
 * @date 2019/2/23 15:20
 * @Version 1.0
 */
public abstract class ProtostuffSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtostuffSerializer.class);

    /**
     * 序列化对象
     * @param obj 需要序列化的对象
     * @param cls 对象的类信息
     * @return byte数组
     */
    public static <T> byte[] serialize(T obj,Class<T> cls) {
        ParamUtil.checkParam(new Object[]{obj,cls});
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = RuntimeSchema.getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            LOGGER.error("protostuff序列化对象出错:{}",e);
            throw new OperationException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 序列化对象集合
     * @param list 需要序列化的对象集合
     * @param cls 集合中对象的类信息
     * @return byte数组
     */
    public static <T> byte[] serializeList(List<T> list, Class<T> cls) {
        ParamUtil.checkParam(new Object[]{list,cls});
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        ByteArrayOutputStream bos;
        byte[] protostuff;
        try {
            Schema<T> schema = RuntimeSchema.getSchema(cls);
            bos = new ByteArrayOutputStream();
            ProtostuffIOUtil.writeListTo(bos, list, schema, buffer);
            protostuff = bos.toByteArray();
            return protostuff;
        } catch (Exception e) {
            LOGGER.error("protostuff序列化集合出错:{}",e);
            throw new OperationException(e.getMessage(), e);
        }
    }

    /**
     *  反序列化对象集合
     * @param data 需要反序列化的byte数组
     * @param cls  对象的类信息
     * @return 对象
     */
    public static <T> T deserialize(byte[] data, Class<T> cls) {
        ParamUtil.checkParam(new Object[]{data,cls});
        try {
            Schema<T> schema = RuntimeSchema.getSchema(cls);
            T message = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            LOGGER.error("protostuff反序列化对象出错:{}",e);
            throw new OperationException(e.getMessage(), e);
        }
    }

    /**
     *  反序列化对象结婚
     * @param bytes 需要反序列化的byte数组
     * @param cls 对象的类信息
     * @return 集合
     */
    public static <T> List<T> deserializeList(byte[] bytes, Class<T> cls) {
        ParamUtil.checkParam(new Object[]{bytes,cls});
        Schema<T> schema = RuntimeSchema.getSchema(cls);
        List<T> result;
        try {
            result = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(bytes), schema);
        } catch (IOException e) {
            LOGGER.error("protostuff反序列化集合出错:{}",e);
            throw new OperationException(e.getMessage(),e);
        }
        return result;
    }

    /**
     * 私有化构造函数
     */
    private ProtostuffSerializer() {
    }
}
