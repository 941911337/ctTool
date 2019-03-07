package org.ct.ctTool.interfaces;

import java.lang.reflect.Method;

/**
 * @Classname Aspect
 * @Description 切面接口
 * @Date 2019/3/4 9:23
 * @Created by Think
 */
public interface Aspect {

    /**
     * 目标方法执行前的操作
     *
     * @param target 目标对象
     * @param method 目标方法
     * @param args 参数
     * @return 是否继续执行接下来的操作
     */
    boolean before(Object target, Method method, Object[] args);

    /**
     * 目标方法执行后的操作
     *
     * @param target 目标对象
     * @param method 目标方法
     * @param args 参数
     * @return 是否允许返回值（接下来的操作）
     */
    boolean after(Object target, Method method, Object[] args);

    /**
     * 目标方法抛出异常时的操作
     *
     * @param target 目标对象
     * @param method 目标方法
     * @param args 参数
     * @param e 异常
     * @return 是否允许抛出异常
     */
    boolean afterException(Object target, Method method, Object[] args, Throwable e);
}
