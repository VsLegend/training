package com.training.datastructure.algorithm;

/**
 * @author Wangjunwei
 * @Date 2020/6/9 10:24
 * @Description 动态规划算法
 */
public class Dp {


  public static void main(String[] args) {
  }

  /**
   * 自底向上 o(n)
   */
  public static int fibonacciTop(int n) {
    int[] m = new int[n + 1];
    m[0] = 0;
    m[1] = 1;
    for (int i = 2; i <= n; i++) {
      m[i] = m[i - 1] + m[i - 2];
    }
    return m[n];
  }


  /**
   * 自顶向下备忘录法
   */
  public static int fibonacciBottom(int n) {
    int[] m = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      m[i] = -1;
    }
    return fibonacciBottom(n, m);
  }

  public static int fibonacciBottom(int n, int[] m) {
    if (m[n] != -1) {
      return m[n];
    } else if (n == 0) {
      m[n] = 0;
    } else if (n < 2) {
      m[n] = 1;
    } else {
      m[n] = fibonacciBottom(n - 1, m) + fibonacciBottom(n - 2, m);
    }
    return m[n];
  }

  /**
   * 递归 斐波那契数列 o(2^n)
   */
  public static int fibonacci(int n) {
    if (n == 0) {
      return 0;
    } else if (n < 2) {
      return 1;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

}
