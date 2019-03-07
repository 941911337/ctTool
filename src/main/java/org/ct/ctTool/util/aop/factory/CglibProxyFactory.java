package org.ct.ctTool.util.aop.factory;

import net.sf.cglib.proxy.Enhancer;
import org.ct.ctTool.interfaces.Aspect;
import org.ct.ctTool.interfaces.ProxyFactory;
import org.ct.ctTool.util.aop.handler.CglibHandler;

/**
 * @author Think
 * @Title: CglibProxyFactory
 * @ProjectName easyTool
 * @Description: cglib代理工厂
 * @date 2019/3/4 14:30
 * @Version 1.0
 */
public  class CglibProxyFactory implements ProxyFactory {

    /**
     *
     * @param target 代理对象
     * @param aspect 切面
     * @return 代理对象
     */
    @Override
    public <T> T newProxyInstance(T target, Aspect aspect){
        Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
        enhancer.setSuperclass(target.getClass());  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(new CglibHandler(aspect,target));
        // 创建动态代理类对象并返回
        return (T) enhancer.create();
    }
}
