package org.ct.ctTool.util;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.kryo.Kryo;
import org.ct.ctTool.enums.SizeEnum;
import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * @author Think
 * @Title: DeepCloneUtil
 * @ProjectName easyTool
 * @Description: 深克隆工具类
 * @date 2019/2/21 14:38
 * @Version 1.0
 */
public abstract class DeepCloneUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeepCloneUtil.class);
    

    /**
     *  异常情况下是否使用另一种克隆方式 -1 不使用,1 使用,默认 1;
     */
    private static final int USE_OTHER_FLAG = 1;

    /**
     * 默认使用流克隆 超过10M 使用KRYO
     */
    private static final int USE_KRYO_SIZE = 10;

    /**
     * 深克隆实体类
     * @param t  原对象
     * @param clazz 类型
     * @return 克隆对象
     */
    public static <T> T deepCloneBean(T t,Class<T> clazz){
        if (DataUtil.isEmpty(t) || DataUtil.isEmpty(clazz)) {
            throw new ParamException("参数出错");
        }
        T result = null;
        double size = BeanSizeUtil.getObjectSize(t, SizeEnum.M);
        try{
            if(size > USE_KRYO_SIZE ){
                result = kryoCopy(t);
            } else {
                result = streamCopy(t);
            }
        }catch(Exception e){
            LOGGER.error("第一次深克隆报错,调用补偿机制:{}",e);
            if(USE_OTHER_FLAG >0 ){
                result = fastJsonCopy(t,clazz);
            }
        }
        return result;
    }

    /**
     * fastJson 拷贝
     * @param t 原对象
     * @param clazz 类型
     * @return 克隆对象
     */
    private static <T> T fastJsonCopy(T t,Class<T> clazz){
        String json = JSON.toJSONString(t);
        T result = JSON.parseObject(json, clazz);
        return result;
    }

    /**
     * 流拷贝
     * @param t 原对象
     * @return 克隆对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static <T> T streamCopy(T t) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        T result;
        ObjectInputStream ois;
        try {
            oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(t);
            // 将流序列化成对象
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ois = new ObjectInputStream(byteArrayInputStream);
            result = (T) ois.readObject();
        } catch (IOException e) {
            LOGGER.error("深克隆流操作IO异常:{}",e);
            throw new OperationException(e.getMessage(),e);
        }catch (ClassNotFoundException e) {
            LOGGER.error("深克隆流操作类型异常:{}",e);
            throw new OperationException(e.getMessage(),e);
        }
        return result;
    }

    /**
     * kryo深拷贝
     * @param t 原对象
     * @return 克隆对象
     */
    private static <T> T kryoCopy(T t)  {
        Kryo kryo = new Kryo();
        T x = kryo.copy(t);
        return x ;
    }

    /**
     * 私有化构造函数
     */
    private DeepCloneUtil() {}
}
