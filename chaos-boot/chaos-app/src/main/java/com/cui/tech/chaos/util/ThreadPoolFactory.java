package com.cui.tech.chaos.util;

import com.cui.tech.chaos.model.constant.ThreadPoolTypeEnum;

import java.util.concurrent.*;

/**
 * 线程池创建工厂
 * 主要创建两种类型的线程池：
 * 1.IO密集型线程池(IO较为频繁，主要用于文件上传，下载，数据库数据查询)
 * 2.CPU密集型线程池(偏重于计算)
 *
 * @author sangweidong
 * @create 2019-06-15 13:54
 **/
public class ThreadPoolFactory {
    /**
     * CPU核数
     */
    private final static int CPU_CORE_NUMBER = Runtime.getRuntime().availableProcessors();

    /**
     * 线程的空闲时间,默认60秒
     */
    private final static int KEEP_ALIVE_TIME=600;

    public static void main(String[] args) {

        ExecutorService threadPool=ThreadPoolFactory.instance(ThreadPoolTypeEnum.IO_INTENSIVE);
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }


    /**
     * 根据偏重的业务类型创建不同类型的线程池
     *
     * @param threadPoolType
     * @return
     */
    public static ExecutorService instance(ThreadPoolTypeEnum threadPoolType) {
        ExecutorService threadPool=null;
          switch (threadPoolType.CODE){
              case 1:
                  threadPool= createIOIntensiveThreadPool();
                  break;
              case 2:
                  threadPool= createCPUIntensiveThreadPool();
                  break;
          }
          if(threadPool==null){
              threadPool=createCPUIntensiveThreadPool();
          }
      return threadPool;
    }

    /**
     * 创建一个IO密集型线程池
     * 由于IO密集型任务线程并不是一直在执行任务，则应配置尽可能多的线程,如 cpu核数*2
     *
     * @return
     */
    private static ExecutorService createIOIntensiveThreadPool() {
        //1.计算核心线程数的数量
        int corePoolSize = CPU_CORE_NUMBER * 2;
        ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize, corePoolSize + 5, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10), Executors.defaultThreadFactory(),
                //默认抛出异常
                //new ThreadPoolExecutor.AbortPolicy()
                //回退调用者
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //处理不来的不处理
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPool;

    }

    /**
     * 创建一个CPU密集型线程池
     * CPU密集型任务配置尽可能少的线程数量；一般公式 CPU核数+1 个线程
     *
     * @return
     */
    private static ExecutorService createCPUIntensiveThreadPool() {
        //1.计算核心线程数的数量
        int corePoolSize = CPU_CORE_NUMBER +1;
        ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize, corePoolSize + 2,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10), Executors.defaultThreadFactory(),
                //默认抛出异常
                //new ThreadPoolExecutor.AbortPolicy()
                //回退调用者
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //处理不来的不处理
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPool;
    }

}
