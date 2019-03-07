package org.ct.ctTool.util.aop;

import org.ct.ctTool.exception.ParamException;
import org.ct.ctTool.interfaces.Aspect;
import org.ct.ctTool.util.BeanUtil;
import org.ct.ctTool.util.ParamUtil;
import org.ct.ctTool.util.aop.factory.ProxyFactoryUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Think
 * @Title: ProxyUtil
 * @ProjectName easyTool
 * @Description: 代理工具类
 * @date 2019/3/4 9:41
 * @Version 1.0
 */
public abstract class ProxyUtil {


    private static final Map<String,Object> MAP = new ConcurrentHashMap<>(16);

    /**
     * 获取代理对象
     * @param target 代理目标
     * @param aspect 切面代码
     * @return 返回代理对象
     */
    public static <T> T getProxyInstance(Object target, Aspect aspect){
        ParamUtil.checkParam(new Object[]{target,aspect},new String[]{"target","aspect"});
        return (T) ProxyFactoryUtil.getProxyFactory().newProxyInstance(target,aspect);
    }

    /**
     * 获取代理目标对象
     * @param cls
     * @param aspect
     * @param <T>
     * @return
     */
    public static <T> T getProxyInstance(Class<T> cls,Aspect aspect){
        ParamUtil.checkParam(new Object[]{cls,aspect},new String[]{"cls","aspect"});
        return ProxyFactoryUtil.getProxyFactory().newProxyInstance(BeanUtil.newInstance(cls),aspect);
    }



    /**
     * 私有构造函数
     */
    private ProxyUtil() {
    }
}
