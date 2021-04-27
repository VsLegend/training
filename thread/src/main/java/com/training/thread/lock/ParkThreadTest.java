package com.training.thread.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

/**
 * @User: wong
 * @Date: 2021/3/18
 * @Description:  LockSupport.unpark()测试
 */
public class ParkThreadTest {

  static volatile boolean flag = true;

  public static void main(String[] args) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        while (flag) {
          Thread thread = Thread.currentThread();
          String name = thread.getName();
          System.out.println(name + "开始执行");
          LockSupport.park(this);
          Object blocker = LockSupport.getBlocker(Thread.currentThread());
          System.out.println(blocker);
          boolean interrupted = Thread.interrupted();
          System.out.println("被唤醒时线程的状态：" + interrupted);
          System.out.println(name + "被唤醒重新执行");
        }
      }
    };
    Set<Thread> set = new HashSet<>();
    for (int i = 0; i < 20; i++) {
      Thread thread = new Thread(runnable);
      thread.start();
      set.add(thread);
    }
    try {
      Thread.sleep(1000);
      System.out.println("主线程休眠中");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    flag  = false;
    set.forEach(s -> {
      System.out.println("调用唤醒方法的状态" + s.isInterrupted());
      LockSupport.unpark(s);
      LockSupport.unpark(s);
//      s.interrupt();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(s.isInterrupted());
    });
  }


}
