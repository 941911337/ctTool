package org.ct.ctTool.util.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Think
 * @Title: GlobalThread
 * @ProjectName easyTool
 * @Description: 全局线程池
 * @date 2019/2/27 10:18
 * @Version 1.0
 */
public abstract class GlobalThread {

    /**
     * 默认拒绝策略
     */
    private static final RejectedExecutionHandler DEFAULT_HANDLER = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 全局拒绝策略 初始化可接受最大线程数, 执行拒绝策略时判断是否小于初始max ,小于则扩容,大于执行默认拒绝策略
     */
    private static class GlobalPolicy implements RejectedExecutionHandler {
        int max ;
        public GlobalPolicy(int max) {
            this.max = max;
        }
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            int currentPoolSize = e.getPoolSize();
            if(currentPoolSize < max){
                e.setMaximumPoolSize(currentPoolSize  << 1);
                e.execute(r);
            }else{
                DEFAULT_HANDLER.rejectedExecution(r,e);
            }
        }
    }

    /**
     * 单例
     */
    private static class GlobalThreadHolder{
        private static final int PROCESSORS_NUM = Runtime.getRuntime().availableProcessors();
        private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("easyTool-global-pool-%d").build();
        private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(PROCESSORS_NUM, PROCESSORS_NUM << 1,
                60L, TimeUnit.MINUTES,
                new SynchronousQueue<>(), DEFAULT_THREAD_FACTORY, new GlobalPolicy(256));
    }

    /**
     * 获取全局默认线程池
     * @return
     */
    public static ExecutorService getExecutorService(){
        return GlobalThreadHolder.THREAD_POOL;
    }

    private GlobalThread() {
    }
}
