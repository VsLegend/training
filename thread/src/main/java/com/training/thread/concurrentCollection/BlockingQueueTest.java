package com.training.thread.concurrentCollection;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @User: wong
 * @Date: 2021/4/1
 * @Description:
 */
public class BlockingQueueTest {

  public static void main(String[] args) {
    BlockingQueueTest blockingQueueTest  = new BlockingQueueTest();
    blockingQueueTest.startM();
    for (int i = 0; i < 200; i ++) {
      blockingQueueTest.productSocks();
      blockingQueueTest.consumeSocks();
    }
    blockingQueueTest.endM();
  }

  // 生产队列 队列允许100个同时排队
  private final LinkedBlockingQueue<Future<Integer>> blockingQueue = new LinkedBlockingQueue<>(2);

  // 线程池 并发10
  private final ExecutorService executorService = new ThreadPoolExecutor(10, 20,
          60L, TimeUnit.SECONDS,
          new LinkedBlockingQueue<Runnable>(2));

  // 产品数量
  private final AtomicInteger product = new AtomicInteger(0);

  // 操作树
  private final AtomicInteger mod = new AtomicInteger(0);

  // 市场关闭
  private final AtomicBoolean flag = new AtomicBoolean(false);

  //
  private final Runnable manager = () -> {
    while (!flag.get() || blockingQueue.size() != 0) {
      Future<Integer> future;
      try {
        // 生产或消费结果 这里如果队列为空，会主动等待入队
        future = blockingQueue.peek();
        if (null != future && future.isDone()) {
          Integer r = future.get();
          int i = product.get();
          product.compareAndSet( i, i + r);
          blockingQueue.take();
        }
        mod.getAndIncrement();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    System.out.println("操作数：" + mod.get() + " 产品数量：" + product.get());
  };

  public void startM() {
    Thread thread = new Thread(manager);
    thread.start();
  }

  public void endM() {
    flag.compareAndSet(false, true);
    executorService.shutdown();
  }

  // 生产袜子 执行成功+1 尝试不控制生产
  public void productSocks() {
    Produce produce = new Produce();
    Future<Integer> submit = executorService.submit(produce);
    boolean enqueueu = true;
    while (enqueueu) {
      try {
        blockingQueue.put(submit);
        enqueueu = false;
      } catch (InterruptedException e) {
        System.out.println("生产失败，将会继续尝试入队生产");
      }
    }
  }

  // 市场消费袜子
  public void consumeSocks() {
    Consume consume = new Consume();
    Future<Integer> submit = executorService.submit(consume);
    boolean enqueueu = true;
    while (enqueueu) {
      try {
        blockingQueue.put(submit);
        enqueueu = false;
      } catch (InterruptedException e) {
        System.out.println("消费失败，将会继续尝试入队消费");
      }
    }
  }


  class Produce implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
      System.out.println("线程" + Thread.currentThread().getName() + "生产产品----------------------");
      Thread.sleep(1000);
      System.out.println("线程" + Thread.currentThread().getName() + "生产成功----------------------");
      return 1;
    }
  }

  class Consume implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
      System.out.println("线程" + Thread.currentThread().getName() + "消费产品----------------------");
      LockSupport.parkNanos(1000);
      System.out.println("线程" + Thread.currentThread().getName() + "消费成功----------------------");
      return -1;
    }
  }
}
