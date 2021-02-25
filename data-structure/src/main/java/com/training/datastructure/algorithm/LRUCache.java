package com.training.datastructure.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @User: wong
 * @Date: 2021/1/20
 * @Description: LAU 最近最少访问
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {

  int capacity = 0;

  public LRUCache(int capacity) {
    super(capacity, 0.75f, true);
    this.capacity = capacity;
  }

  @Override
  public V get(Object key) {
    return super.get(key);
  }

  @Override
  public V put(K key, V value) {
    return super.put(key, value);
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return size() > capacity;
  }

}