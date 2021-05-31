package com.training.thread.safetyCollection;

import java.util.concurrent.LinkedBlockingQueue;

public class TBlockingQueue {

  public static void main(String[] args) {
    LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(100);
    try {
      blockingQueue.put("产品1");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      String take = blockingQueue.take();
      System.out.println("消费产品：" + take);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
