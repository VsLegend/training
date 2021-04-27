package com.training.thread.lock;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @User: wong
 * @Date: 2021/4/22
 * @Description: RateLimiter
 */
public class RateLimiterTest implements Callable<Integer> {

  private RateLimiter rateLimiter;

  RateLimiterTest(RateLimiter rateLimiter) {
    this.rateLimiter = rateLimiter;
  }

  public static void main(String[] args) {
    RateLimiter rateLimiter = RateLimiter.create(3);
    final ExecutorService executorService = Executors.newFixedThreadPool(10);
    List<Future<Integer>> list = new ArrayList<>();
    for (int i = 0; i < 10; i ++) {
      Future<Integer> submit = executorService.submit(new RateLimiterTest(rateLimiter));
      list.add(submit);
    }
  }


  @Override
  public Integer call() {
    double acquire = rateLimiter.acquire(1);
    System.out.println(Thread.currentThread().getName() + "获取了一个令牌，时间：" + new Date());
    System.out.println("等待时间：" + acquire);
    return 1;
  }
}
