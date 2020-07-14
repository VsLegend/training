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
    mergeRec(v, 0, v.length - 1);
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
    int[] rA = Arrays.copyOfRange(v, l, mid);
    int[] rB = Arrays.copyOfRange(v, mid, h);
    int a = mid - l;
    int b = h - mid;
    for (int i = 0, m = 0, n = 0; (m < a) || (n < b); ) {
      if (m < a && (b <= n || rA[m] <= rB[n]))
        v[i++] = rA[m++];
      if (n < b && (a <= m || rB[n] < rA[m]))
        v[i++] = rB[n++];
    }
    System.out.print("当前合并的数组：");
    for (int i = l; i < h; i++)
      System.out.print(v[i] + " ");
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
