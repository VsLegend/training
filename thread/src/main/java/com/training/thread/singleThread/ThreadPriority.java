package com.training.thread.singleThread;

/**
 * @User: wong
 * @Date: 2020/10/12
 * @Description: 线程优先级
 */
public class ThreadPriority {

  public static void main(String[] args) {
    // 在当前线程中创建子线程，子线程会复制当前线程的优先级
    // 优先级高的线程不代表一定比优先级低的线程优先执行，代码执行顺序跟线程的优先级无关。但是高优先级线程很大概率比低优先级线程执行，即更容易获得CPU资源。
    Thread threadA = new Thread(() -> {
      System.out.println("线程执行A  >>>>>>>>>>>>>>");
    });
    Thread threadB = new Thread(() -> {
      System.out.println("线程执行B  >>>>>>>>>>>>>>");
      Thread.yield();
      System.out.println("线程放弃执行B  >>>>>>>>>>>>>>");
    });
    Thread threadBB = new Thread(() -> {
      System.out.println("线程执行BB  >>>>>>>>>>>>>>");
      Thread.yield();
      System.out.println("线程放弃执行BB  >>>>>>>>>>>>>>");
    });
    Thread threadC = new Thread(() -> {
      System.out.println("线程执行C  >>>>>>>>>>>>>>");
      Thread.yield();
      System.out.println("线程放弃执行C  >>>>>>>>>>>>>>");
    });
    threadA.setPriority(Thread.MIN_PRIORITY);
    threadC.setPriority(Thread.MAX_PRIORITY);
    threadA.start();
    threadB.start();
    threadBB.start();
    threadC.start();
    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    System.out.println(Thread.currentThread().getPriority());
    System.out.println(threadA.getPriority());
    System.out.println(threadB.getPriority());
    System.out.println(threadC.getPriority());
  }

}
