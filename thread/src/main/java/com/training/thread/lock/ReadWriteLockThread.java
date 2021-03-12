package com.training.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @User: wong
 * @Date: 2020/8/3
 * @Description: 读写锁
 */
public class ReadWriteLockThread {

  private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

  // 读锁
  private final Lock rLock = rwLock.readLock();

  // 写锁
  private final Lock wLock = rwLock.writeLock();

  // 惰性生成
  ReadWriteLockThread() {

  }

  private int sum = 2020;

  public void add(int i) {
    wLock.lock();
    try {
      sum += i;
    } finally {
      wLock.unlock();
    }
  }

  public int getSum() {
    rLock.lock();
    try {
      return sum;
    } finally {
      rLock.unlock();
    }
  }

  public static void main(String[] args) {
    ReadWriteLockThread lockThread = new ReadWriteLockThread();
    Thread a = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        System.out.print(i + " ");
        lockThread.add(i);
      }
    });
    System.out.println();
    Thread b = new Thread(() -> {
      for (int y = 0; y > -100; y--) {
        System.out.println(lockThread.getSum() + "  b");
        System.out.println();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    System.out.println();
    Thread c = new Thread(() -> {
      for (int y = 0; y > -100; y--) {
        System.out.println(lockThread.getSum() + "  c");
        System.out.println();
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    a.start();
    b.start();
    c.start();
    try {
      a.join();
      b.join();
      c.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
