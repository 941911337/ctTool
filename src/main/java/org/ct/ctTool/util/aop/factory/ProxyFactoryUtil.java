package org.ct.ctTool.util.aop.factory;

import org.ct.ctTool.interfaces.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Think
 * @Title: ProxyFactory
 * @ProjectName easyTool
 * @Description: 代理工厂工具类
 * @date 2019/3/4 14:38
 * @Version 1.0
 */
public abstract class ProxyFactoryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyFactoryUtil.class);


    private static class ProxyHolder{
        private static final ProxyFactory DEFAULT_FACTORY =  getProxyInstance();
    }

    /**
     * 根据用户引入Cglib与否创建代理工厂
     *
     * @return 代理工厂
     */
    private static ProxyFactory getProxyInstance() {
        try {
            return new CglibProxyFactory();
        } catch (NoClassDefFoundError e) {
            LOGGER.error("未引入Cglib");
        }
        return new JdkProxyFactory();
    }

    /**
     * 获取代理工厂
     * @return
     */
    public static ProxyFactory getProxyFactory(){
        return ProxyHolder.DEFAULT_FACTORY;
    }

    /**
     * 私有化构造
     */
    private ProxyFactoryUtil() {
    }
}
