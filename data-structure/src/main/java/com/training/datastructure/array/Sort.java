package com.training.datastructure.array;

public class Sort {

  // 改进二分查找 时间复杂度更为稳定 最坏为 O(logn)
  public static int binSearch(int[] m, int l, int h, int t) {
    while (l < h) {
      int mid = (l + h) >> 1;
      if (t < m[mid]) h = mid;
      else l = mid + 1;
      System.out.println("mid: " + mid + " l: " + l + " h: " + h);
    }
    return --l;
  }

  public static void main(String[] args) {
    int[] v = {7, 98, 45, 1, 3, 6, 77, 99};
    nBubbleSort(v);
    for (int i : v) {
      System.out.print(i + " ");
    }
  }

  // 冒泡排序 bubble sort 时间复杂度 O(n^2)
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

  // 冒泡改进 最坏情况依旧是 O(n^2), 但最好情况为O(1)
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
