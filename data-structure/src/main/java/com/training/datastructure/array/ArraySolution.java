package com.training.datastructure.array;

import org.bouncycastle.util.Arrays;

/**
 * @author Wangjunwei
 * @Date 2020/3/25 14:37
 * @Description
 */
public class ArraySolution {


  public static void main(String[] args) {
    int n = 1804289383;
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
      int mid = (l + h) >> 1;
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
    // 递归版本
//    int mid = (l + h) >> 1;
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

  /**
   * 有序向量唯一化（去重）
   * 思路：以重复区间为单位，批量忽略重复项
   *
   * @param v
   * @return
   */
  public static int[] unique(int[] v) {
    int size = v.length;
    int i = 0;
    int j = 0;
    while ((++j) < size) {
      if (v[i] != v[j]) {
        v[++i] = v[j];
      }
    }
    return Arrays.copyOf(v, ++i);
  }

}
