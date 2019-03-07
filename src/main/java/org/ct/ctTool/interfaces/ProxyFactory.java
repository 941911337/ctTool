package org.ct.ctTool.interfaces;

/**
 * @Classname ProxyFactory
 * @Description 代理工厂接口
 * @Date 2019/3/4 14:36
 * @Created by Think
 */
public interface ProxyFactory {

     /**
      * 获取代理方法
      * @param target 目标对象
      * @param aspect 切面
      * @return 代理
      */
     <T> T newProxyInstance(T target, Aspect aspect);
}
