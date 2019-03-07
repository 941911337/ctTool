package org.ct.ctTool.util;

import org.ct.ctTool.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Think
 * @Title: BeanUtil
 * @ProjectName ctTool
 * @Description: 实体类工具类
 * @date 2019/3/7 9:33
 * @Version 1.0
 */
public abstract class BeanUtil {

     private static final Logger LOGGER = LoggerFactory.getLogger(DeepCloneUtil.class);

    /**
     * 创建实体类
     * @param clazz 类
     * @return 生成实体类
     */
     public static <T> T newInstance(Class<T> clazz){
        T result;
        String className = clazz.getName();
        try {
            result =  clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error("创建实体类报错:{}",e);
            throw new ParamException(className+":不能构建");
        }
        return  result;
     }


}
