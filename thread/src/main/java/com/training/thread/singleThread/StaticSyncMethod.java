package com.training.thread.singleThread;

/**
 * @User: wong
 * @Date: 2021/1/28
 * @Description: 访问类锁的非同步方法 | 访问对象锁的非同步方法
 */
public class StaticSyncMethod {

  public static void main(String[] args) throws InterruptedException {
    StaticSyncMethod staticSyncMethod = new StaticSyncMethod();
    Thread a = new Thread(() -> {
      System.out.println("线程A访问对象锁》》》》》》");
      staticSyncMethod.syncMethod();
      System.out.println("线程A退出对象锁》》》》》》");
    });
    Thread b = new Thread(() -> {
      System.out.println("线程B访问类锁的普通方法》》》》》》");
      staticSyncMethod.normalMethod();
      System.out.println("线程B退出类锁的普通方法》》》》》》");
    });
    a.start();
    b.start();
    a.join();
    b.join();
  }


  static synchronized void staticMethod() {
    System.out.println("访问类锁：准备休眠》》》》》》");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("访问类锁：休眠结束》》》》》》");
  }

  synchronized void syncMethod() {
    System.out.println("访问对象锁：准备休眠》》》》》》");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("访问对象锁：休眠结束》》》》》》");
  }

  void normalMethod() {
    System.out.println("非同步方法访问：》》》》》》");
  }

}
