package com.training.thread.pressureTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @User: wong
 * @Date: 2021/3/15
 * @Description: 同步屏障 使用线程屏障可以达到模拟线程同步执行的效果
 *  让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * @see java.util.concurrent.CyclicBarrier
 */
public class CyclicBarrierTest {

  // 构造器设置 屏障线程数
  static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      Thread thread = new Thread(() -> {
        String name = Thread.currentThread().getName();
        try {
          System.out.println("线程" + name + "：进入屏障等待其他线程进入...");
          // 表示线程已到达屏障，等待指定数量的线程到达屏障后，才会继续执行
          // 需要注意的是，到达屏障指定的数量后，屏障计数器会清零，继续拦截下一波线程
          cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          e.printStackTrace();
        }
        System.out.println("线程" + name + "：通过屏障继续执行...");
      });
      thread.start();
    }
    while (Thread.activeCount() > 1) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
