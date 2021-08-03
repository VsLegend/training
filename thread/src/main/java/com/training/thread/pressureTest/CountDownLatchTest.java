package com.training.thread.pressureTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @User: wong
 * @Date: 2021/3/15
 * @Description: 这个类能使一个线程等待其他线程各自执行完毕后再执行，
 *    它是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，
 *    当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
 * @see java.util.concurrent.CountDownLatch
 */
public class CountDownLatchTest {

  static volatile boolean flag = true;

  // 构造器设置初始计数器
  static CountDownLatch countDownLatch = new CountDownLatch(1);

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      flag = false;
      System.out.println("修改线程执行完毕...");
      // 计数器减一
      countDownLatch.countDown();
    });
    thread1.start();
    Thread thread = new Thread(() -> {
      while (flag) {
        System.out.println("执行中...");
        try {
          System.out.println("当前线程等待其他其他线程执行完毕中...");
          // 等待计数器清零后，就会释放所有调用该方法的线程
          boolean await = countDownLatch.await(2000, TimeUnit.SECONDS);
          countDownLatch.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("计数器清零，当前线程开始执行...");
      }
    });
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
