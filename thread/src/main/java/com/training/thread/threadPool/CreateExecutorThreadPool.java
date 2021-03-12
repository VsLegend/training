package com.training.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @User: wong
 * @Date: 2020/8/4
 * @Description: 创建线程池的6种方式
 * Executors.newSingleThreadExecutor()
 * Executors.newFixedThreadPool(10)
 * Executors.newCachedThreadPool()
 * Executors.newScheduledThreadPool(1)
 * Executors.newSingleThreadScheduledExecutor()
 * @see com.training.thread.forkJoin.ForkJoinThreadPool
 */
public class CreateExecutorThreadPool {

  public static void main(String[] args) throws InterruptedException {
    // 线程数固定的线程池
    ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
    // 固定长度的线程池 核心线程和最大线程一样
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
    // 可缓存的线程池，如果线程池的规模超过了处理需求，将自动回收空闲线程
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    // 并行的定时线程池
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
    // 单核心定时线程，实际上就是ScheduledExecutorService(1)
    ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    System.out.println("创建线程池，将所有线程放入线程池管理+++++++++++++++++++++++++++");
    for (int i = 0; i < 10; i++) {
      cachedThreadPool.execute(new Th1());
    }
    System.out.println("线程创建完毕+++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("释放线程池中的线程++++++++++++++++++++++++++++++++++++++++++");
    // 该方法会等待所有线程执行完毕
    cachedThreadPool.shutdown();
    Thread.sleep(2000);
    System.out.println("强制结束线程池中的线程+++++++++++++++++++++++++++++++++++++++");
    cachedThreadPool.shutdownNow();

  }

}


