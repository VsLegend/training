package com.training.datastructure.algorithm;

/**
 * @User: wong
 * @Date: 2020/6/29
 * @Description: 动态规划算法实例
 */
public class DynamicProgramAlgorithm {

  /**
   * 买卖股票的最佳时机
   * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
   * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
   * 注意：你不能在买入股票前卖出股票。
   * 输入: [7,1,5,3,6,4]
   * 输出: 5
   * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
   * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
   * 输入: [7,6,4,3,1]
   * 输出: 0
   * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
   * <p>
   * 动态规划：
   */
  public static int maxProfit(int[] prices) {
    int length = prices.length;
    if (length == 0)
      return 0;
    int maxProfit = 0;
    int minPrice = prices[0];
    for (int x = 1; x < length; x++) {
      minPrice = Math.min(minPrice, prices[x - 1]);
      maxProfit = Math.max(prices[x] - minPrice, maxProfit);
    }
    return maxProfit;
  }


  public static void main(String[] args) {
    System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
  }

  /**
   * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
   * 示例 1:
   * <p>
   * 输入: n = 12
   * 输出: 3
   * 解释: 12 = 4 + 4 + 4.
   * <p>
   * 示例 2:
   * <p>
   * 输入: n = 13
   * 输出: 2
   * 解释: 13 = 4 + 9.
   */
  public static int numSquares(int n) {
    Integer[] squares = new Integer[n];
    if (0 > n)
      return 0;
    for (int i = 1; i * i <= n; i++) {
      int square;
      square = squares[i] == null ? (squares[i] = i * i) : squares[i];
    }
    return 0;
  }

}