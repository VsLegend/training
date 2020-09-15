package com.training.thread.singleThread;

/**
 * @User: wong
 * @Date: 2020/8/3
 * @Description:
 */
public class VolatileThread extends Thread {

  int i = 0;

  // boolean flag = true; //当主线程在内存中修改了flag的值，子线程也不会跳出循环，因为子线程是将内存中的flag复制了一份到自己线程的工作缓存中。
  volatile boolean flag = true; // 使用volatile时，线程修改内存中的值时，会强迫其他线程重新在内存中读取更新后的变量值

  @Override
  public void run() {
    while (flag) {
      i++;
    }
    System.out.println("i: " + i + "  线程结束");
  }

  public static void main(String[] args) throws Exception {

    VolatileThread thread = new VolatileThread();
    thread.start();
    Thread.sleep(2000);
    thread.flag = false;
    System.out.println("主线程结束");
  }
}
