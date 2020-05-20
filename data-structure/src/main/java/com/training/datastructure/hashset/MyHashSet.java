package com.training.datastructure.hashset;

import java.util.Map.Entry;

/**
 * @author Wangjunwei
 * @Date 2019/11/14 10:28
 * @Description
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 */
public class MyHashSet {

  private Entry[] table;

  /** Initialize your data structure here. */
  public MyHashSet() {
    table = new Entry[1000];
  }

  public void add(int key) {
    int calculate = calculate(key);
  }

  public void remove(int key) {

  }

  private int calculate(int key) {
    return key % 1000;
  }

  /** Returns true if this set contains the specified element */
  public boolean contains(int key) {
    int calculate = calculate(key);
    return true;
  }

}
