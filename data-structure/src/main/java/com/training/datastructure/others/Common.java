package com.training.datastructure.others;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @User: wong
 * @Date: 2020/7/22
 * @Description:
 */
public class Common {
  public static void main(String[] args) {
    Character c = '\u262F';
    System.out.println((int) Math.sqrt(12));
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("1", "1");
    hashMap.get("1");
    hashMap.remove("1");
    Hashtable<String, String> hashtable = new Hashtable();
    hashtable.put("1", "");
    hashtable.get("1");
    hashtable.remove("1");
    ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    concurrentHashMap.put("1", "1");
    concurrentHashMap.get("1");
    concurrentHashMap.remove("1");
  }
}
