package com.training.thread.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @User: wong
 * @Date: 2020/8/5
 * @Description: 多线程返回结果
 */
public class CallableThread implements Callable<String> {

  private String content;

  CallableThread() {
  }

  CallableThread(String content) {
    this.content = content;
  }

  @Override
  public String call() throws Exception {
    return this.content;
  }

  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(4);
    int sum = 0;
    List<Future<String>> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      CallableThread actualRunThread1 = new CallableThread("STRING IS : " + Integer.toBinaryString(i));
      Future<String> submit = threadPool.submit(actualRunThread1);
      list.add(submit);
    }
    System.out.println("线程池创建完毕：等待执行++++++++++++++++++");
    new Thread(() -> {
      int time = 0;
      while (!list.isEmpty()) {
        System.out.println("第" + ++time + "次查看结果++++++++++++++++++++");
        for (int i = 0; i < list.size(); i++) {
          Future<String> future;
          if ((future = list.get(i)).isDone()) {
            try {
              String s = future.get();
              System.out.println("标准输出：" + s);
            } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
            }
            list.remove(i);
          }
          System.out.println("---");
        }
      }
    }).start();
    System.out.println("主线程执行完毕，等待结果输出+++++++++++++++++");
//    threadPool.shutdownNow() // 立即关闭线程池
    threadPool.shutdown(); // 等待线程全部执行完毕后，关闭线程池，此时线程池无法创建新任务
    System.out.println("等待线程池关闭+++++++++++++++++++++++++++++");
  }
}
