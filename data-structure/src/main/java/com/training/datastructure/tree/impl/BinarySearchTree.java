package com.training.datastructure.tree.impl;

/**
 * @User: wong
 * @Date: 2020/7/16
 * @Description: 二叉搜索树
 */
public class BinarySearchTree extends BinaryTree {

  private Tree root;

  private int size = 0;


  public BinarySearchTree() {
  }

  // 插入
  public void insert(int value) {
    if (root == null) {
      root = new Tree(null, value, null, null);
      size++;
      return;
    }
    Tree tree = getTree(value);
    if (tree.value == value)  {
      return;
    }

  }

  // 删除
  public boolean remove(int value) {
    return true;
  }

}
