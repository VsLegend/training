package com.training.thread.concurrentCollection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @User: wong
 * @Date: 2021/3/16
 * @Description:
 */
public class ConcurrentHashMapTest {

  public static void main(String[] args) {
    ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>(2);
    concurrentHashMap.put("star", "【⭐⭐⭐⭐⭐】");
    concurrentHashMap.put("love", "【♥♥♥♥♥♥】");
    System.out.println(concurrentHashMap.get("star"));
  }

}
