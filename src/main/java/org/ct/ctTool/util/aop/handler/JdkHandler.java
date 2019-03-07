package org.ct.ctTool.util.aop.handler;

import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.interfaces.Aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Think
 * @Title: JdkHandler
 * @ProjectName easyTool
 * @Description: jdk代理 逻辑增强处理
 * @date 2019/3/4 9:49
 * @Version 1.0
 */
public class JdkHandler implements InvocationHandler {

    private Aspect aspect;

    private Object target;

    public JdkHandler(Aspect aspect, Object target) {
        this.aspect = aspect;
        this.target = target;
    }

    /**
     * 增强逻辑实现
     * @param proxy 目标对象
     * @param method 方法对象
     * @param args 参数
     * @return 执行结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  {
        Object result = null ;
        if(aspect.before(target,method,args)){
            try {
                result = method.invoke(proxy,args);
            } catch (Exception e) {
                if(e instanceof InvocationTargetException ){
                    aspect.afterException(target,method,args,e);
                }else{
                    throw new OperationException(e.getMessage(),e);
                }

            }
        }
        if(aspect.after(target,method,args)){
            return result;
        }
        return null;
    }
}
