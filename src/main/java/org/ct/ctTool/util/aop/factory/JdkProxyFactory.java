package org.ct.ctTool.util.aop.factory;

import org.ct.ctTool.interfaces.Aspect;
import org.ct.ctTool.interfaces.ProxyFactory;
import org.ct.ctTool.util.aop.handler.JdkHandler;

/**
 * @author Think
 * @Title: JdkProxyFactory
 * @ProjectName easyTool
 * @Description: JDK代理工厂
 * @date 2019/3/4 14:27
 * @Version 1.0
 */
public class JdkProxyFactory implements ProxyFactory {

    /**
     * 生成jdk代理对象
     * @param target 目标对象
     * @param aspect 切面
     * @return 代理对象
     */
    @Override
    public <T> T newProxyInstance(T target, Aspect aspect){
        T result =  (T) java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), new JdkHandler(aspect,target));
        return result;
    }
}
