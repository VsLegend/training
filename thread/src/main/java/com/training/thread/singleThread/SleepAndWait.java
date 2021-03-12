package com.training.thread.singleThread;

/**
 * @User: wong
 * @Date: 2020/8/10
 * @Description: sleep 和 wait 区别
 * <p>
 * wait必须在同步代码块中调用，且wait等待的对象是使用该方法的实例，而sleep针对当前线程；
 * 实例对象调用wait时，会释放线程所获得的锁以及系统的监控。
 * 其他线程调用notify唤醒该线程时，wait方法执行完毕并返回，该线程又会重新尝试获取方法的锁（唤醒后线程会从wait之后的代码开始运行）；
 * 使用sleep时，会使使用sleep的线程暂停，但不会释放锁，直至sleep设定的时间到了之后，该线程会继续执行，而不需要重新等待并获取锁。
 */
public class SleepAndWait {

  public synchronized static void getSleepLock(int num) throws InterruptedException {
    System.out.println("线程：" + num + " 已获得锁，当前线程暂停5秒。");
    // 此处sleep让当前占用该锁的线程睡眠，当前线程将会一直持有锁
    Thread.sleep(5000);
    Thread.yield();
    System.out.println("线程：" + num + " 继续执行");
  }

  public transient boolean flag = true;

  public Integer sum = 0;

  public void getWaitLock(String name) throws InterruptedException {
    synchronized (this) {
      System.out.println("线程：" + name + " 已获得锁");
      while (flag) {
        if (sum == 100) {
          System.out.println("线程：" + name + " 进入等待状态");
          sum++;
          this.wait();
        }
        sum++;
//        System.out.print(sum + " ");
      }
      System.out.println("线程：" + name + " 执行完毕");
      this.notifyAll();
    }
  }


  public static void main(String[] args) throws InterruptedException {
    // sleep示例
//    Thread thread1 = new Thread(() -> {
//      System.out.println("当前线程：1");
//      try {
//        getSleepLock(1);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    });
//    Thread thread2 = new Thread(() -> {
//      System.out.println("当前线程：2");
//      try {
//        getSleepLock(2);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    });
//    thread1.start();
//    thread2.start();
//    thread1.join();
//    thread2.join();

    // wait示例，两个线程使用同一个实例
    SleepAndWait sleepAndWait = new SleepAndWait();
    Wait wait1 = new Wait(sleepAndWait, "3");
    Wait wait2 = new Wait(sleepAndWait, "4");
    Thread thread3 = new Thread(wait1);
    Thread thread4 = new Thread(wait2);
    thread3.start();
    thread4.start();
    Thread.sleep(1000);
    Thread thread = new Thread(() -> {
      while (true) {
        if (sleepAndWait.sum > 150) {
          sleepAndWait.flag = false;
//          sleepAndWait.notifyAll();
          break;
        }
      }
    });
    thread.setDaemon(true);
    thread.start();
    String o = new String();
  }

}

class Wait implements Runnable {

  SleepAndWait sleepAndWait;

  String name;

  Wait(SleepAndWait sleepAndWait, String name) {
    this.sleepAndWait = sleepAndWait;
    this.name = name;
  }

  // 抢库存
  @Override
  public void run() {
    try {
      sleepAndWait.getWaitLock(name);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}