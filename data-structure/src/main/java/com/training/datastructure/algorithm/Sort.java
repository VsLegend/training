package com.training.datastructure.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sort {

  public static void main(String[] args) {
    Map<String, String> hashMap = new HashMap<>();
    hashMap.put("S", "1");
    hashMap.put("B", "2");
    System.out.println(hashMap.toString());
    int[] v = {7, 98, 45, 1, 3, 6, 77, 99, 2};
//    Node node = new Node(7);
//    node.succ = new Node(98);
//    node.succ = new Node(45);
//    node.succ = new Node(1);
//    node.succ = new Node(3);
//    node.succ = new Node(6);
//    node.succ = new Node(77);
//    node.succ = new Node(99);
//    node.succ = new Node(2);
//    Node sort = insertionSort(node);
//    for (Node n = sort; n != null; n = n.succ) {
//      System.out.print(n.value + " ");
//    }
    insertionSort(v);
    for (int i : v) {
      System.out.print(" " + i);
    }
  }

  /**
   * 插入排序
   * 新建一个空列表或数组，作为已排序完成的列表或数组，每次从待排序的列表中获取一个元素，并在已排序的元素中对比，并在合适的位置中插入该元素，直至待排序元素为空
   * 在列表中由于插入只需要O(1)的时间，而数组平均需要O(n),因此在最好情况下，列表的时间复杂度为O(n)，数组为O(n^2)
   */
  public static Node insertionSort(Node node) {
    Node first = null;
    Node last = null;
    Node next = null;
    while ((next = node) != null) {

    }
    return first;
  }

  public static void insertionSort(int[] a) {
    int len = a.length - 1;
    int low = 0;
    for (int i = low, j = i; i < len; j = ++i) {
      // 逐一比较插入位置
      int ai = a[i + 1];
      while (ai < a[j]) {
        a[j + 1] = a[j];
        if (j-- == low) {
          break;
        }
      }
      a[j + 1] = ai;
    }
  }


  /**
   * 选择排序
   * 每一次迭代中，选择最大数的下标或值，与最后一位交换，直至只剩下一位数
   * 时间复杂度恒为 O(n^2)
   */
  public static void selectionSort(int[] v) {
    for (int i = 0; i < v.length; i++) {
      int index = 0;
      int n = v.length - i - 1;
      for (int j = 0; j <= n; j++) {
        if (v[j] >= v[index])
          index = j;
      }
      System.out.println("当前最大值下标:" + index + "最大值：" + v[index]);
      int x = v[n];
      v[n] = v[index];
      v[index] = x;
    }
  }


  /**
   * 归并排序 将数组化为递归基，合并递归基同事排序
   */
  public static void mergeSort(int[] v) {
    mergeRec(v, 0, v.length);
  }

  // 遍历递归基
  public static void mergeRec(int[] v, int l, int h) {
    if (h - l < 2) return;
    int mid = (h + l) >> 1;
    mergeRec(v, l, mid);
    mergeRec(v, mid, h);
    merge(v, l, mid, h);
  }

  // 合并两个序列
  public static void merge(int[] v, int l, int mid, int h) {
    System.out.println("低位：" + l + "中位：" + mid + " 高位：" + h);
    int[] r = Arrays.copyOfRange(v, l, h); // [l, h)
    int[] rA = Arrays.copyOfRange(v, l, mid); // [l, mid)
    int a = mid - l;
    int b = h - l;
    for (int i = 0, m = 0, n = mid - l; m < a; ) {
      // 高位取r中mid之后的值，低位取mid之前的值 当高位取完，低位还有值时，将低位剩余的所有值替换至r中；反之低位取完时，高位剩下的值是不变的，即刻退出循环
      if (n < b && r[n] < rA[m])
        r[i++] = r[n++];
      if (n >= b || rA[m] <= r[n])
        r[i++] = rA[m++];
    }
    for (int i = 0; i < h - l; i++) {
      v[i + l] = r[i];
    }
    System.out.print("当前归并列表：");
    for (int i : v) {
      System.out.print(i + " ");
    }
    System.out.println();
  }


  /**
   * 冒泡排序 bubble sort 时间复杂度 O(n^2)
   */
  public static void bubbleSort(int[] m) {
    int l = 0;
    int h = m.length - 1;
    while (l < h--) {
      int f = l;
      while (++f < h) {
        if (m[f] < m[f - 1]) {
          int s = m[f - 1];
          m[f - 1] = m[f];
          m[f] = s;
        }
      }
    }
  }

  /**
   * 冒泡改进 最坏情况依旧是 O(n^2), 但最好情况为O(1)
   */
  public static void nBubbleSort(int[] m) {
    int l = 0;
    int h = m.length - 1;
    int f = h;
    while (l < (h = f)) {
      int l1 = l;
      // 是否有交换
      boolean flag = true;
      while (++l1 < h) {
        if (m[l1] < m[l1 - 1]) {
          int s = m[l1 - 1];
          m[l1 - 1] = m[l1];
          m[l1] = s;
          flag = false;
          f = l1;
        }
      }
      if (flag)
        return;
    }
  }


  /**
   * 节点
   */
  private static class Node {
    private int value;
    private Node prev;
    private Node succ;

    Node(int value) {
      this.value = value;
    }

    Node(int value, Node prev, Node succ) {
      this.value = value;
      this.prev = prev;
      this.succ = succ;
    }
  }
}
