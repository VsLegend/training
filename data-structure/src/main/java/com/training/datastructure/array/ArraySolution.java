package com.training.datastructure.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Wangjunwei
 * @Date 2020/3/25 14:37
 * @Description
 */
public class ArraySolution {


  public static void main(String[] args) {
    int[] nums = {0, 1, 3, 4, 5};
    System.out.println(missingNumber(nums));
  }


  public int[] nextGreaterElements(int[] nums) {
    int length = nums.length;
    // 循环数组扩大两倍转普通数组
    int[] arr = new int[2 * length];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = i >= length ? nums[i - length] : nums[i];
    }
    // 单调递减，当删除某个元素时，即代表新插入的元素是最后一个出栈元素的右边第一个大于他的数
    Stack<Integer> stack = new Stack<>();
    int index = 0;
    while (index < length) {

    }
    return arr;
  }


  // 剑指offer
  public static int missingNumber(int[] nums) {
    int l = 0;
    int h = nums.length;
    while (l < h) {
      int i = (l + h) >> 1;
      if (i == nums[i]) {
        l = i + 1;
      } else {
        h = i;
      }
    }
    return (l == nums.length - 1) && l == nums[l] ? l + 1 : l;
  }

  // 441 硬币
  public static int arrangeCoins(int n) {
    if (n == 1)
      return n;
    int l = 1;
    int h = n;
    while (l < h) {
      long mid = (long) h + (long) l >> 1;
      long e = (long) (1 + mid) * mid / 2;
      System.out.println("中界： " + mid + "中值： " + e);
      if (n < e) h = (int) mid;
      else l = (int) mid + 1;
      System.out.println("上界：" + h + "下界：" + l);
    }
    return --l;
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
