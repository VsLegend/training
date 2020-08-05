package com.training.thread.syncThread;

import java.util.Arrays;
import java.util.concurrent.locks.StampedLock;

/**
 * @User: wong
 * @Date: 2020/8/3
 * @Description: 乐观锁
 */
public class StampedLockThread {

  private final StampedLock stampedLock = new StampedLock();

  private int x = 2020;

  private int y = 1010;

  public void add(int i, int j) {
    long l = stampedLock.writeLock();
    try {
      x += i;
      y += j;
    } finally {
      stampedLock.unlockWrite(l);
    }
  }

  public int[] get() {
    long l = stampedLock.tryOptimisticRead();
    int i = x;
    int j = y;
    // 除了当前线程获得该锁之外，检测是由有其他写锁发生
    if (!stampedLock.validate(l)) {
      // 获取悲观锁
      l = stampedLock.readLock();
      try {
        i = x;
        j = y;
      } finally {
        stampedLock.unlockRead(l);
      }
    }
    return new int[]{i, j};
  }

  public static void main(String[] args) {
    StampedLockThread lockThread = new StampedLockThread();
    Thread a = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        System.out.print(i + " ");
        lockThread.add(i * 10, i * 10);
      }
    });
    System.out.println();
    Thread b = new Thread(() -> {
      for (int y = 0; y > -100; y--) {
        System.out.println(Arrays.toString(lockThread.get()) + "  b");
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
        System.out.println(Arrays.toString(lockThread.get()) + "  c");
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
