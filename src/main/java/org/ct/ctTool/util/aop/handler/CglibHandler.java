package org.ct.ctTool.util.aop.handler;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.interfaces.Aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Think
 * @Title: CglibHandler
 * @ProjectName easyTool
 * @Description: cglib代理 逻辑增强处理
 * @date 2019/3/4 13:43
 * @Version 1.0
 */
public class CglibHandler implements MethodInterceptor {

    private Aspect aspect;

    private Object target;

    public CglibHandler(Aspect aspect, Object target) {
        this.aspect = aspect;
        this.target = target;
    }

    /**
     *  增强实现逻辑
     * @param o 目标对象
     * @param method 方法对象
     * @param args 参数
     * @param proxy 代理
     * @return 返回结果
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        if(aspect.before(target,method,args)){
            try {
                result = proxy.invokeSuper(o,args);
            } catch (Exception e) {
                if(e instanceof InvocationTargetException){
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
