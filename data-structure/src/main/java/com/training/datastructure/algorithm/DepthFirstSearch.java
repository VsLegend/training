package com.training.datastructure.algorithm;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * @User: wong
 * @Date: 2020/7/7
 * @Description: 深度优先搜索
 */
public class DepthFirstSearch {

  public static void main(String[] args) {
    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(8, 0.75f, true);
    linkedHashMap.put("key", "new Key");
    linkedHashMap.get("key");
    Collection<Object> values = linkedHashMap.values();
    System.out.println(values.toString());
    linkedHashMap.clear();
  }


  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}

