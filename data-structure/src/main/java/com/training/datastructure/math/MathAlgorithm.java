package com.training.datastructure.math;

/**
 * @User: wong
 * @Date: 2020/6/29
 * @Description: 数学公式
 */
public class MathAlgorithm {

  public static void main(String[] args) {
    System.out.println("1-100:" + as(1, 1, 100));
    System.out.println("1-100:" + gauss(1, 100, 100));
  }


  // 求和

  /**
   * 高斯求和公式
   * S =  (f + l) * n / 2
   * @param f 首项
   * @param l 尾项
   * @param n 项数
   * @return
   */
  public static int gauss(int f, int l, int n) {
    return (f + l) * n  / 2;
  }

  /**
   * 等差数列求和
   * Sn = n*a1 + d*n*(n-1) / 2
   * @param a 首项
   * @param d 差
   * @param n 尾项
   */
  public static int as(int a, int d, int n) {
    return n * a + d * n * (n - 1) / 2;
  }

}
