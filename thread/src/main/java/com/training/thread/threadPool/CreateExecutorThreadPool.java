package com.training.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @User: wong
 * @Date: 2020/8/4
 * @Description: 创建线程池的3种方式
 */
public class CreateExecutorThreadPool {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newCachedThreadPool();
    System.out.println("创建线程池，将所有线程放入线程池管理+++++++++++++++++++++++++++");
    for (int i = 0; i < 10; i++) {
      executor.execute(new Th1());
    }
    System.out.println("线程创建完毕+++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("释放线程池中的线程++++++++++++++++++++++++++++++++++++++++++");
    // 改方法会等待所有线程执行完毕
    executor.shutdown();
    Thread.sleep(2000);
    System.out.println("强制结束线程池中的线程+++++++++++++++++++++++++++++++++++++++");
    executor.shutdownNow();

    // 并行的定时线程池
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
  }

}


