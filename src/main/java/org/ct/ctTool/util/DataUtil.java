package org.ct.ctTool.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author Think
 * @Title: DataUtil
 * @ProjectName easyTool
 * @Description: 数据工具类
 * @date 2019/2/21 14:55
 * @Version 1.0
 */
public abstract class DataUtil {

    /**
     * 判断是否为空
     * @param pObj
     * @return 标识
     */
    public static final  boolean isEmpty(Object pObj) {
        if (pObj == null || "".equals(pObj)){
            return true;
        }
        if (pObj instanceof String) {
            if (((String) pObj).trim().length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection<?>) {
            if (((Collection<?>) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map<?, ?>) {
            if (((Map<?, ?>) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为空
     * @param args
     * @return 标识
     */
    public static final  boolean isEmpty(Object ... args) {
        for (Object arg : args) {
            if(isEmpty(arg)){
                return true;
            }
        }
        return false;
    }


    /**
     * 私有化构造
     */
    private DataUtil() {
    }
}
