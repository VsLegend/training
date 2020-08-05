package com.training.thread.syncThread;

/**
 * @User: wong
 * @Date: 2020/7/31
 * @Description:
 */
public class SyncThread {

  public static int sum = 20;

  // 对于static方法，是没有this实例的，因为static方法是针对类而不是实例。
  // 修饰静态方法，相当于🔒住该类的实例，多线程共享该实例
  public static void operation(int i) {
    synchronized (SyncThread.class) {
      sum = sum + i;
    }
  }

  public int getSum() {
    return sum;
  }

  // 在非静态方法中，对this进行加锁时，相当于锁住当前实例，相当于给当前方法加锁，不同的实例之前互不影响
  public void add(int i) {
    if (i < 0) {
      // 对同一线程来说，获取到锁后可以继续获取同一个🔒，即可重复锁
      System.out.print(" " + "I" + " ");
      sub(i);
    } else {
      synchronized (this) {
        sum = sum + i;
      }
    }
  }

  // 给方法加🔒
  public synchronized void sub(int i) {
    sum = sum - i;
  }


  public static void main(String[] args) {
    SyncThread thread = new SyncThread();
    Thread a = new Thread(() -> {
      System.out.println("进入线程A---------------------");
      for (int i = 5000; i > -5000; i--) {
        thread.add(2 * (0 == (i & 1) ? 1 : -1));
        System.out.print(thread.getSum() + " ");
      }
      System.out.println();
      System.out.println("线程A结束---------------------");
    });
    Thread b = new Thread(() -> {
      System.out.println("进入线程B---------------------");
      for (int i = 0; i < 10000; i++) {
        thread.sub(10);
        System.out.print(thread.getSum() + " ");
      }
      System.out.println();
      System.out.println("线程B结束---------------------");
    });
    // 此时a b相互抢锁
    a.start();
    b.start();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println();
    System.out.println( "访问不需要获取锁的方法" + thread.getSum());
    System.out.println();
    try {
      a.join();
      b.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

class ThreadA extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      SyncThread.operation(i);
      // 多线程直接操作一个对象时，无法保证原子性，多线程会导致读写不同步，即线程A读取数据为100，此时线程A阻塞，线程B读取也为100且增加为200，此时原数为200
      // 当A线程重新获取资源时，由于以及读取了原数，且为100，此时增加50，并将原数200覆盖为150。（线程同步时，原数应为250）
//      SafeThread.sum = SafeThread.sum + 1;
      System.out.println(SyncThread.sum);
    }
  }
}

class ThreadB extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      SyncThread.operation(-i);
//      SafeThread.sum = SafeThread.sum + 1;
      System.out.println(SyncThread.sum);
    }
  }
}