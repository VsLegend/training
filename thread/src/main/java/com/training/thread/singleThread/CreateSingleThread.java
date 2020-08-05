package com.training.thread.singleThread;

/**
 * @User: wong
 * @Date: 2020/7/30
 * @Description: 单线程的创建方式
 */
public class CreateSingleThread {

  /**
   * 线程的生命周期
   * 见java-thread.jpg
   *
   * 新建状态:
   * 使用 new 关键字和 Thread 类或其子类建立一个线程对象后，该线程对象就处于新建状态。它保持这个状态直到程序 start() 这个线程。
   *
   * 就绪状态:
   * 当线程对象调用了start()方法之后，该线程就进入就绪状态。就绪状态的线程处于就绪队列中，要等待JVM里线程调度器的调度。
   * ThreadGroup 就绪时，加入线程队列，排队等待 private native void start0();
   *
   * 运行状态:
   * 如果就绪状态的线程获取 CPU 资源，就可以执行 run()，此时线程便处于运行状态。处于运行状态的线程最为复杂，它可以变为阻塞状态、就绪状态和死亡状态。
   *
   * 阻塞状态:
   * 如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。可以分为三种：
   *
   * 等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态。
   *
   * 同步阻塞：线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用)。
   *
   * 其他阻塞：通过调用线程的 sleep() 或 join() 发出了 I/O 请求时，线程就会进入到阻塞状态。当sleep() 状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态。
   *
   * 死亡状态:
   * 一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。
   */

  public static void main(String[] args) {
    // 在main中启动其他线程后，就独立与当前线程独自运行了，因此“main结束”的打印位置是未知的。
    // 并且由操作系统调度，程序本身无法确定线程的调度顺序。
    System.out.println("主线程main运行中------------------");
    // 继承Thread 设置未守护线程
    Thread threadA = new ThreadA();
    threadA.setDaemon(true);
    threadA.start();
    // 实现Runnable
    Thread threadB = new Thread(new ThreadB());
    threadB.start();
    // 直接用lambda表达式创建线程 相当于创建一个匿名内部类
    Thread threadC = new Thread(() -> {
      System.out.println("线程C运行中----------------------");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("线程C结束----------------------");
    });
    threadC.start();
    try {
      // 等待线程A结束后继续往后走，也可以指定等待的时间，时间到了不管有没有执行完毕都往后走
      threadA.join(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    synchronized (threadA) {
      threadA.notify();
    }
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("主线程main结束---------------------");
  }

}

class ThreadA extends Thread {
  @Override
  public void run() {
    System.out.println("线程A运行中--------------------");
    try {
      synchronized (this) {
        this.wait();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("线程A结束----------------------");
  }
}

class ThreadB implements Runnable {
  @Override
  public void run() {
    System.out.println("线程B运行中--------------------");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("线程B结束----------------------");
  }
}
