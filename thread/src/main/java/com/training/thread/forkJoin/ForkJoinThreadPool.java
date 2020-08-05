package com.training.thread.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @User: wong
 * @Date: 2020/8/4
 * @Description: 并发框架fork-join求和
 * 分治编程模式，适用于计算任务的计算量无法评估，但可以进行层次分解
 */
public class ForkJoinThreadPool extends RecursiveTask<Long> {


  public static void main(String[] args) throws InterruptedException, ExecutionException {
    int start = 1, end = 1000000000;
    ForkJoinPool forkJoinPool = new ForkJoinPool(5);
    ForkJoinThreadPool fork = new ForkJoinThreadPool(start, end);
    ForkJoinTask<Long> submit = forkJoinPool.submit(fork);
    while (!submit.isDone()) {
      System.out.println("等待结果++++++++++++++++++++++++");
      System.out.println("线程池：" + forkJoinPool.getActiveThreadCount());
      Thread.sleep(20);
    }
    System.out.println("输出结果：" + submit.get());
    long sum = 0;
    for (long i = start; i <= end; i++) {
      sum += i;
    }
    System.out.println("直接迭代计算结果：" + sum);
  }

  private long start;

  private long end;

  ForkJoinThreadPool(long s, long e) {
    start = s;
    end = e;
  }

  private static final int threadhold = 1000000;

  @Override
  protected Long compute() {
    long sum = 0;
    if ((end - start) < threadhold) {
      for (long i = start; i <= end; i++) {
        sum += i;
      }
    } else {
      long mid = (end - start) / 2 + start;
      System.out.println("mid：" + mid);
      ForkJoinThreadPool one = new ForkJoinThreadPool(start, mid);
      ForkJoinThreadPool two = new ForkJoinThreadPool(mid + 1, end);
      invokeAll(one, two);
      Long f = one.join();
      Long s = two.join();
      sum = f + s;
    }
    return sum;
  }
}
