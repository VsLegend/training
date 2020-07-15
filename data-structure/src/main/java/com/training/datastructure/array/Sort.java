package com.training.datastructure.array;

import java.util.Arrays;

public class Sort {

  public static void main(String[] args) {
    int[] v = {7, 98, 45, 1, 3, 6, 77, 99, 2};
    mergeSort(v);
    for (int i : v) {
      System.out.print(i + " ");
    }
  }

  /**
   * 归并排序 将数组化为递归基，合并递归基同事排序
   */
  public static boolean mergeSort(int[] v) {
    mergeRec(v, 0, v.length);
    return true;
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
  public static boolean bubbleSort(int[] m) {
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
    return true;
  }

  /**
   * 冒泡改进 最坏情况依旧是 O(n^2), 但最好情况为O(1)
   */
  public static boolean nBubbleSort(int[] m) {
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
        return true;
    }
    return true;
  }

}
