package com.training.thread.lock;


/**
 * @User: wong
 * @Date: 2020/9/7
 * @Description:
 */
public class ObjectAClassLevelLock {


  public static void main(String[] args) throws InterruptedException {
    ObjectAClassLevelLock levelLock = new ObjectAClassLevelLock();
    LockObject lockObjectA = new LockObject("实例A");
    LockObject lockObjectB = new LockObject("实例B");
    Thread a = new Thread(() -> {
      levelLock.getLevelName(lockObjectA);
    });
    Thread b = new Thread(() -> {
//      levelLock.getSecondName(lockObjectB);
      levelLock.getLevelName(lockObjectB);
    });
    a.start();
    b.start();
    a.join();
    b.join();
  }


  void getLevelName(LockObject lockObject) {
//    synchronized (lockObject) {
//    synchronized (LockObject.class) {
    synchronized (this) {
      System.out.println(lockObject.getName() + "获取到锁，开始运行");
      getSecondName(lockObject);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(lockObject.getName() + "运行完毕");
    }
  }

  void getSecondName(LockObject lockObject) {
    synchronized (lockObject) {
      System.out.println(lockObject.getName() + "进入第二层，开始运行");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(lockObject.getName() + "第二层运行完毕");
    }
  }

}


class LockObject {

  String name;

  LockObject(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
