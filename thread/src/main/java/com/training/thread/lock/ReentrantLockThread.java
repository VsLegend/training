package com.training.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @User: wong
 * @Date: 2020/7/31
 * @Description:
 */
public class ReentrantLockThread {

  ReentrantLock lock = new ReentrantLock();

  int sorce = 100;

  public void output(int i) {
    // 🔒 可代替synchronized
    try {
      if (lock.tryLock(1, TimeUnit.SECONDS)) {
        try {
          sorce += i;
          System.out.println(sorce);
        } finally {
          lock.unlock();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ReentrantLockThread lockThread = new ReentrantLockThread();
    Thread a = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        System.out.print(i + " ");
        lockThread.output(i);
      }
    });
    System.out.println();
    Thread b = new Thread(() -> {
      for (int y = 0; y > -1000; y--) {
        System.out.print(y + " ");
        lockThread.output(y);
      }
    });
    a.start();
    b.start();
    try {
      a.join();
      b.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
