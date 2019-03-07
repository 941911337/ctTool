package org.ct.ctTool.util.aop;

import org.ct.ctTool.interfaces.Aspect;

import java.lang.reflect.Method;

/**
 * @author Think
 * @Title: TimeAspect
 * @ProjectName easyTool
 * @Description: TODO
 * @date 2019/3/4 19:54
 * @Version 1.0
 */
public class TimeAspect implements Aspect {

   private long time = 0;


    @Override
    public boolean before(Object target, Method method, Object[] args) {
        time = System.nanoTime();
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args) {
        System.out.println("方法耗时:"+(System.nanoTime() - time)/1000/1000+"ms");
        return true;
    }

    @Override
    public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
        return false;
    }
}
