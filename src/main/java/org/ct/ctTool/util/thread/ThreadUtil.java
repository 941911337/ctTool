package org.ct.ctTool.util.thread;

import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Think
 * @Title: ThreadUtil
 * @ProjectName easyTool
 * @Description: TODO
 * @date 2019/2/27 9:10
 * @Version 1.0
 */
public abstract class ThreadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtil.class);

    /**
     * 执行任务 无返回值
     * @param runnable 任务
     */
    public static void execute(Runnable runnable) {
        try {
            GlobalThread.getExecutorService().execute(runnable);
        } catch (Exception e) {
            LOGGER.error("执行任务报错:{}",e);
            throw new OperationException(e.getMessage(),e);
        }
    }

    /**
     * 执行任务 有返回值
     * @param callable 任务
     * @param <T> 返回值
     * @return
     */
    public static <T> Future<T> execute(Callable callable) {
        try {
            return GlobalThread.getExecutorService().submit(callable);
        } catch (Exception e) {
            LOGGER.error("执行任务报错:{}",e);
            throw new OperationException(e.getMessage(),e);
        }
    }

    /**
     * 指定线程池 执行任务 无返回值
     * @param name 线程池名称
     * @param runnable 任务
     */
    public static void execute(String name,Runnable runnable) {
        try {
            getThreadPool(name).execute(runnable);
        } catch (Exception e) {
            LOGGER.error("执行任务报错:{}",e);
            throw new OperationException(e.getMessage(),e);
        }
    }

    /**
     * 指定线程池名称 执行任务 有返回值
     * @param name 线程池名称
     * @param callable 任务
     * @return 返回值
     */
    public static <T> Future<T> execute(String name,Callable callable) {
        try {
            return getThreadPool(name).submit(callable);
        } catch (Exception e) {
            LOGGER.error("执行任务报错:{}",e);
            throw new OperationException(e.getMessage(),e);
        }
    }

    /**
     * 初始化 定长线程池
     * @param name 线程池名称
     * @param poolSize 初始化池大小
     */
    public static void initThreadPool(String name,int poolSize) {
        EasyThreadFactory.initPoolCore(name,poolSize,poolSize,0, TimeUnit.MILLISECONDS,null,1, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     *  初始化线程池
     * @param name 线程池名称
     * @param corePoolSize 初始化线程池长度
     * @param maximumPoolSize 最大线程池长度
     * @param keepAliveTimeMs 保活毫秒数
     * @param queueSize 队列长度
     */
    public static void initPool(String name,int corePoolSize,int maximumPoolSize,long keepAliveTimeMs,int queueSize){
        EasyThreadFactory.initPoolCore(name,corePoolSize,maximumPoolSize,keepAliveTimeMs,TimeUnit.MILLISECONDS,null,queueSize, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 初始化线程池
     * @param name 线程池名称
     * @param corePoolSize 线程池长度
     * @param maximumPoolSize 最大线程池长度
     * @param keepAliveTimeMs 保活毫秒数
     * @param queueSize 队列长度
     * @param handler 拒绝策略
     */
    public static void initPool(String name,int corePoolSize,int maximumPoolSize,long keepAliveTimeMs,int queueSize, RejectedExecutionHandler handler){
        EasyThreadFactory.initPoolCore(name,corePoolSize,maximumPoolSize,keepAliveTimeMs,TimeUnit.MILLISECONDS,null,queueSize, handler);
    }

    /**
     * 获取线程池
     * @param name 线程池名称 不存在初始化一个长度为3的线程池
     * @return
     */
    private static ExecutorService getThreadPool(String name){
        if(!EasyThreadFactory.existPool(name)){
            initThreadPool(name,3);
        }
        return EasyThreadFactory.getPool(name);
    }

    /**
     * 关闭线程池
     * @param name
     */
    public static void ShowDown(String name){
        ExecutorService es = EasyThreadFactory.removePool(name);
        if(es != null){
            es.shutdown();
        }else{
            String message = String.format("线程池-%s-线程池不存在",name);
            throw new ParamException(message);
        }
    }

    /**
     * 立即关闭线程池
     * @param name
     * @return
     */
    public static List<Runnable> showDownNow(String name){
        ExecutorService es = EasyThreadFactory.removePool(name);
        if(es != null){
            return es.shutdownNow();
        }else{
            String message = String.format("线程池-%s-线程池不存在",name);
            throw new ParamException(message);
        }
    }

    /**
     * 私有化 禁止构造
     */
    private ThreadUtil() {
    }
}
