package com.training.datastructure.algorithm;

/**
 * @author Wangjunwei
 * @Date 2020/6/9 15:46
 * @Description 时间复杂度
 */
public class TimeComplexty {

  /**
   * 二分查找 O(logn)
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

}
