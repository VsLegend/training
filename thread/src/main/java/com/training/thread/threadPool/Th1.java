package com.training.thread.threadPool;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @User: wong
 * @Date: 2020/8/4
 * @Description:
 */
class Th1 implements Runnable {

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    System.out.printf("Thread %s: 启动\n", name);
    try {
      doTask();
    } catch (InterruptedException e) {
      System.out.printf("Thread %s: 被中断\n", name);
      return;
    }
    System.out.printf("Thread %s: 完成\n", name);
  }

  private void doTask() throws InterruptedException {
    Random random = new Random((new Date()).getTime());
    int value = (int) (random.nextDouble() * 100);
    System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
    TimeUnit.SECONDS.sleep(value);
  }
}
