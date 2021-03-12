package com.training.thread.lockAlgorithm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @User: wong
 * @Date: 2020/9/14
 * @Description: CAS算法 即比较和交换
 */
public class CASAlgorithm {

  AtomicInteger num = new AtomicInteger(0);

  public int getNum() {
    return num.get();
  }

  public static void main(String[] args) throws InterruptedException {
    CASAlgorithm casAlgorithm = new CASAlgorithm();
    for (int i = 1; i <= 1000; i++) {
      new Thread(casAlgorithm::update).start();
    }
    Thread.sleep(2000);
    System.out.println("最终结果： " + casAlgorithm.getNum());
  }

  void update() {
    int oldValue = num.get();
    // 此时可能其他线程以及修改了num的值
    while (true) {
      // 使用原子类的cas来保证对比和赋值操作的原子性
      if (num.compareAndSet(oldValue, oldValue + 1)) {
        return;
      }
      oldValue = num.get();
      System.out.println("自旋");
    }
  }

}
