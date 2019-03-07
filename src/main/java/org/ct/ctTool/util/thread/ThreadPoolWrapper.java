package org.ct.ctTool.util.thread;

import java.util.concurrent.*;

/**
 * @author Think
 * @Title: ThreadWrapper
 * @ProjectName easyTool
 * @Description: 线程池包装类
 * @date 2019/2/28 18:32
 * @Version 1.0
 */
public class ThreadPoolWrapper {

    /**
     * 线程池名称
     */
    private String name;

    /**
     * 锁
     */
    private Object lock;

    /**
     * 线程池大小
     */
    private int corePoolSize;

    /**
     * 最大线程池大小
     */
    private int maximumPoolSize;

    /**
     * 保活时间
     */
    private long keepAliveTime;

    /**
     * 保活时间单位
     */
    private TimeUnit unit;

    /**
     * 线程工厂
     */
    private  ThreadFactory factory;

    /**
     * 队列长度
     */
    private  int queueSize;

    /**
     * 线程池
     */
    private ExecutorService pool;

    /**
     * 拒绝策略
     */
    private RejectedExecutionHandler handler;

    /**
     * 等待队列
     */
    private BlockingQueue<Runnable> workQueue;

    public ExecutorService getPool() {
        return pool;
    }

    public ThreadPoolWrapper(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory factory, int queueSize, RejectedExecutionHandler handler) {
        this.lock = new Object();
        this.name = name;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.factory = factory;
        this.queueSize = queueSize;
        this.handler = handler;
        this.workQueue = this.queueSize >0 ? new LinkedBlockingQueue<>(queueSize) : new SynchronousQueue<>();
    }

    /**
     * 初始化线程池
     */
    public void init(){
        if(this.pool == null){
            this.pool =  new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolSize,
                    this.keepAliveTime, this.unit, this.workQueue, this.factory, this.handler);
        }
    }
}
