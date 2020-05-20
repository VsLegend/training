package com.training.datastructure.hashset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Wangjunwei
 * @Date 2019/11/14 11:18
 * @Description 方法
 */
public class SetSolution {

  // 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
  // 判断数组 / List 是否有重复的值 通过HashSet的方式判断
  public boolean containsDuplicate(int[] nums) {
    Hashtable hashtable = new Hashtable();
    HashSet<Integer> set = new HashSet<Integer>();
    for (int i : nums) {
      if (set.contains(i)) {
        return true;
      }
      set.add(i);
    }
    return false;
  }

  // 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
  // 应该具有线性时间复杂度。 你可以不使用额外空间来
  public int singleNumber(int[] nums) {
    /**
     * 哈希表 不包含任何重复元素的无序集合
     * 添加失败时，即该数重复
     */
//    HashSet hashSet = new HashSet();
//    for (int i : nums) {
//      if (!hashSet.add(i)) {
//        hashSet.remove(i);
//      }
//    }
//    return (int) hashSet.toArray()[0];

    /**
     *1 = a ^ b
     *2 = 1 ^ c
     *2 = a ^ b ^ c
     *如果 a=b
     *a ^ b = 0
     *c ^ 0 = c
     */
    // 异或解法
    int ans = nums[0];
    if (nums.length > 1) {
      for (int i = 1; i < nums.length; i++) {
        ans = ans ^ nums[i];
      }
    }
    return ans;
  }

  /**
   * 给定两个数组，编写一个函数来计算它们的交集 输出结果中的每个元素一定是唯一的。 我们可以不考虑输出结果的顺序。
   */
  public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> hashSet = new HashSet<>();
    HashSet<Integer> result = new HashSet<>();
    for (int x : nums1) {
      hashSet.add(x);
    }
    for (int y : nums2) {
      if (hashSet.contains(y)) {
        result.add(y);
      }
    }
    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  /**
   * 编写一个算法来判断一个数是不是“快乐数”。 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和， 然后重复这个过程直到这个数变为
   * 1，也可能是无限循环但始终变不到 1。 如果可以变为1，那么这个数就是快乐数。
   */
  public int getNumSum(int n) {
    int sum = 0;
    while (n > 0) {
      int num = n % 10;
      sum += num * num;
      n = n / 10;
    }
    return sum;
  }

  public boolean isHappy(int n) {
    int slow = n, fast = n;
    do {
      slow = getNumSum(slow);
      fast = getNumSum(fast);
      fast = getNumSum(fast);
    } while (slow != fast);

    return slow == 1;
  }

  /**
   * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
   */
  // TODO:
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> hashMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (hashMap.containsKey(complement)) {
        return new int[]{hashMap.get(complement), i};
      }
      hashMap.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  /**
   * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。
   * 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
   */
  public String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> map1 = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();
    Map<String, Integer> result = new HashMap<>();
    int min = list1.length + list2.length;
    for (int i = 0; i < list1.length; i++) {
      map1.put(list1[i], i);
    }
    for (int i = 0; i < list2.length; i++) {
      map2.put(list2[i], i);
    }
    for (Map.Entry<String, Integer> entry : map1.entrySet()) {
      if (map2.containsKey(entry.getKey())) {
        int step = entry.getValue() + map2.get(entry.getKey());
        result.put(entry.getKey(), step);
        min = step < min ? step : min;
      }
    }
    map1.clear();
    for (Map.Entry<String, Integer> entry : result.entrySet()) {
      if (min == entry.getValue()) {
        map1.put(entry.getKey(), entry.getValue());
      }
    }
    return map1.keySet().toArray(new String[]{});
  }

  /**
   * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
   */
  public int firstUniqChar(String s) {
    Map<Character, Integer> map = new LinkedHashMap<>();
    int x = s.length();
    char[] chars = s.toCharArray();
    for (int i = 0; i < x; i++) {
      char c = chars[i];
      map.put(c, map.getOrDefault(c, 0) +1);
    }
    for (int i = 0; i < x; i++) {
      if (map.get(chars[i]) == 1) {
        return i;
      }
    }
    return -1;
  }


  public static void main(String[] args) {
    SetSolution solution = new SetSolution();
    int[] ints = {3, 3};
    int ta = 6;
    int[] twoSum = solution.twoSum(ints, ta);
    System.out.println(twoSum[0] + "\\\\\\" + twoSum[1]);
  }

}
