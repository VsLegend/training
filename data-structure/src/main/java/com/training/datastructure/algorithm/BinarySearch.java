package com.training.datastructure.algorithm;

/**
 * @User: wong
 * @Date: 2021/3/5
 * @Description: 二分查找
 */
public class BinarySearch {


  public static void main(String[] args) {
    int[] m = {1, 4, 6, 7, 9, 12, 24, 45, 66, 78, 90, 99};
    int bs = bs(m, 1);
    System.out.println(bs);
  }


  public static int bs(int[] m, int t) {
    int length = m.length;
    if (length == 0)
      return -1;
    int left = 0, right = length;
    while (left < right) {
      int mid = left + (right - left)/2;
      if (t < m[mid])
        right = mid;
      if (t > m[mid])
        left = mid + 1;
      if (t == m[mid])
        return mid;
    }
    return -1;
  }


  /**
   * 二分查找 O(logn) 递归方式
   */
  public int ef(int[] m, int left, int right, int target) {
    if (left > right) {
      return -1;
    }
    int mid = (right - left) + 1;
    if (m[mid] == target)
      return m[mid];
    else if (m[mid] > target) {
      return ef(m, left, mid, target);
    } else {
      return ef(m, mid, right, target);
    }
  }

  /**
   * 二分查找 Binary search
   * 和斐波那契查找的区别在于中值的获取
   * 二分的中值为当前查找区间的一半。时间复杂度最坏情况大约为 1.5*log2^n
   * 斐波那契的中值为。时间复杂度最坏情况也不超过 1.44*log2^n
   */
  public static int binSearch(int[] v, int l, int h, int e) {
    // 迭代版本
    while (l < h) {
      int mid = l + (h - l)/2;
      if (v[mid] == e) {
        System.out.println("成功找到");
        return mid;
      }
      if (v[mid] > e) {
        h = mid;
      } else {
        l = mid + 1;
      }
    }
    // 递归版本
//    int mid = l + (h - l)/2;
//    if (v[mid] == e) {
//      return mid;
//    }
//    if (l >= h)
//      return -1;
//    if (e < v[mid]) {
//      binSearch(v, mid + 1, h, e);
//    } else {
//      binSearch(v, l, mid, e);
//    }
    System.out.println("未找到：前一个元素为：" + v[--l]);
    return l;
  }

  // 改进二分查找 时间复杂度更为稳定 最坏为 O(logn)
  public static int improveBinSearch(int[] m, int l, int h, int t) {
    while (l < h) {
      int mid = (l + h) >> 1;
      if (t < m[mid]) h = mid;
      else l = mid + 1;
      System.out.println("mid: " + mid + " l: " + l + " h: " + h);
    }
    return --l;
  }


  /**
   * 斐波那契查找 Fibonacci search
   * 黄金分割比例：0.6180339……
   */
  public static int fibSearch(int[] v, int l, int h, int e) {
    // 获取Fibonacci字典
    int i = h - l;
    int[] fib = fibonacciTop(i);
    // 迭代版本
    while (l < h) {
      while ((h - l) < fib[i]) {
        i--;
      }
      int mid = l + fib[i] - 1;
      if (v[mid] == e) {
        System.out.println("成功找到");
        return mid;
      }
      if (e < v[mid]) {
        h = mid;
      } else {
        l = mid + 1;
      }
    }
    System.out.println("未找到：前一个元素为：" + v[--l]);
    return l;
  }

  public static int[] fibonacciTop(int n) {
    int[] m = new int[n + 1];
    m[0] = 0;
    m[1] = 1;
    for (int i = 2; i <= n; i++) {
      m[i] = m[i - 1] + m[i - 2];
    }
    return m;
  }


}
