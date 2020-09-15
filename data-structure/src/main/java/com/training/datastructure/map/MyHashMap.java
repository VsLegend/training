package com.training.datastructure.map;

import java.io.Serializable;

/**
 * @author Wangjunwei
 * @Date 2020/6/4 16:43
 * @Description
 */

/**
 * put存放键值时，调用putVal
 * putVal
 * 1. 检查hashmap是否为空，为空时调用resize()
 * 2. 通过&位运算((n - 1) & hash) 找到当前key的插槽(slot)（不同的键，hash地址是有可能相同的，指向的插槽也会相同。这时候根据Node的hashCode判断）
 * 3. 该插槽中，如果没有Node，直接新增Node然后退出。 （tab[i] = newNode(hash, key, value, null);）
 * 4. 该插槽中存在Node。如果新增的键与此相同，更新键值对的值，并返回原来的值
 * 4. 该插槽中存在Node。判断是否为红黑树
 * 4. 该插槽中存在Node。该插槽中的Node或Node.next不存在该新增的键时，即在该插槽的最后一个Node处新增一个Node（链表）
 * 5. 最后判断map的size是否大于当前容量cap。大于便扩容。(oldCap << 1) * loadFactor
 * newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?(int)ft : Integer.MAX_VALUE);
 */


public class MyHashMap<K, V> implements Serializable {

  transient Node<K, V>[] map;

  int size;

  int cap;

  public static void main(String[] args) {
  }


  public MyHashMap(int cap) {
    if (cap <= 0) {
      this.cap = 8;
    } else {
      this.cap = cap;
    }
    this.size = 0;
  }

  public <K, V> V put(K key, V value) {
    Node<K, V>[] tab;
    Node<K, V> p;
    int slot;
    int n;
    if ((tab = (Node<K, V>[]) map) == null) {

    }
    if ((p = (Node<K, V>) map[slot = hashcode(hash(key))]) == null || (n = this.size) < 0) {
      p = new Node<>(hash(key), key, value, null);
    } else {
      // 插槽数据不为空

    }
    V old;
    return value;
  }

  // 值得hash值
  // (h = key.hashCode()) ^ (h >>> 16) 高16位不变 低16位与右位移16位的h做异或运算
  public int hash(Object key) {
    return key.hashCode();
  }

  // 值的存放位置
  public int hashcode(int hash) {
    return hash & (cap - 1);
  }


  static class Node<K, V> {

    final int hash;
    final K key;
    private V value;
    private Node<K, V> next;

    Node(int hash, K key, V value, Node<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    void setNext(Node<K, V> next) {
      this.next = next;
    }

  }
}
