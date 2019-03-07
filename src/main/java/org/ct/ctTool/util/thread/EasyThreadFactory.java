package org.ct.ctTool.util.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.ct.ctTool.constant.Constants;
import org.ct.ctTool.exception.ParamException;
import org.ct.ctTool.util.DataUtil;
import org.ct.ctTool.util.lock.SegmentLockUtil;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Think
 * @Title: EasyThreadFactory
 * @ProjectName easyTool
 * @Description: 线程工厂
 * @date 2019/2/27 16:57
 * @Version 1.0
 */
public abstract class EasyThreadFactory {

    /**
     * 线程池集合
     */
    private static final Map<String, ThreadPoolWrapper> POOL_MAP = new ConcurrentHashMap<>();

    /**
     * 名称名格式
     */
    private static final String NAME_FORMAT =  "easyTool-%s-pool";

    /**
     * 获取线程池格式
     * @param name 线程池名
     * @return 格式
     */
    private static String getNameFormat(String name){
        String nameFormat = String.format(NAME_FORMAT,name);
        nameFormat = nameFormat + "-%d";
        return nameFormat;
    }

    /**
     * 初始化线程工厂
     * @param name 线程池名
     * @param daemon 是否是守护线程
     * @param priority 优先级
     * @param uncaughtExceptionHandler 不捕获异常处理
     * @return 线程工厂
     */
    private static ThreadFactory initFactory(String name, Boolean daemon, Integer priority, Thread.UncaughtExceptionHandler uncaughtExceptionHandler){
        if(DataUtil.isEmpty(name)){
            throw new ParamException("name不能为空");
        }
        if(POOL_MAP.containsKey(name)){
            throw new ParamException("线程池已存在");
        }
        String nameFormat = getNameFormat(name);
        ThreadFactoryBuilder tfb = new ThreadFactoryBuilder().setNameFormat(nameFormat);
        if (daemon != null){
            tfb.setDaemon(daemon);
        }
        if (priority != null){
            tfb.setPriority(priority);
        }
        if (uncaughtExceptionHandler != null){
            tfb.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        return tfb.build();
    }

    /**
     * 获取线程池
     * @param name
     * @return
     */
    public static ExecutorService getPool(String name){
        return POOL_MAP.get(name).getPool();
    }

    /**
     * 判断线程池是否存在
     * @param name
     * @return
     */
    public static boolean existPool(String name){
        return POOL_MAP.containsKey(name);
    }

    /**
     * 移除线程池
     * @param name
     * @return
     */
    public static ExecutorService removePool(String name){
        return POOL_MAP.remove(name).getPool();
    }

    /**
     * 穿件线程池
     * @param name 线程池名称
     * @param corePoolSize 初始线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 保活时间
     * @param unit 保活时间单位
     * @param factory 线程工厂
     * @param queueSize 队列长度
     * @param handler 拒绝策略
     */
    public static void initPoolCore(String name,int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     ThreadFactory factory,
                                     int queueSize,
                                     RejectedExecutionHandler handler){
        if(Constants.DEFAULT_THREAD_NAME.equals(name)){
            String message = String.format("禁止创建-%s-线程池",name);
            throw new ParamException(message);
        }
        if(factory == null){
            factory = initFactory(name,null,null,(thread, throwable) ->{});
        }
        if(POOL_MAP.containsKey(name)){
            String message = String.format("线程池-%s-线程池已存在",name);
            throw new ParamException(message);
        }
        synchronized (SegmentLockUtil.getLock(name)){
            if(POOL_MAP.containsKey(name)){
                String message = String.format("线程池-%s-线程池已存在",name);
                throw new ParamException(message);
            }else {
                ThreadPoolWrapper threadPoolWrapper = new ThreadPoolWrapper(name,corePoolSize,maximumPoolSize,keepAliveTime,unit,factory,queueSize,handler);
                POOL_MAP.put(name,threadPoolWrapper);
                POOL_MAP.get(name).init();
            }
        }
    }

    private EasyThreadFactory() {
    }
}
