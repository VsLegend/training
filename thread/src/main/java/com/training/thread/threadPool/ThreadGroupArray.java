package com.training.thread.threadPool;

import java.util.concurrent.TimeUnit;

/**
 * @User: wong
 * @Date: 2020/8/4
 * @Description: 线程数组管理线程
 */
public class ThreadGroupArray {


  public static void main(String[] args) {
    ThreadGroup threadGroup = new ThreadGroup("Th1");
    for (int i = 0; i < 10; i++) {
      new Thread(threadGroup, new Th1()).start();
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("线程创建完毕+++++++++++++++");
    System.out.println();
    System.out.println("当前线程激活数量：" + threadGroup.activeGroupCount());
    System.out.println("线程展示++++++++++++++++++");
    // 创建一个线程组中激活线程大小的数组
    Thread[] threads = new Thread[threadGroup.activeCount()];
    // 将激活线程复制到数组中
    threadGroup.enumerate(threads);
    for (Thread s : threads) {
      System.out.printf("线程 %s %s \n", s.getName(), s.getState());
    }
    System.out.println("线程展示完毕++++++++++++++++");
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // 中断线程
    threadGroup.interrupt();
  }

}

