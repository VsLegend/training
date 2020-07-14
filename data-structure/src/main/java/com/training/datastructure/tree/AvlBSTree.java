package com.training.datastructure.tree;

/**
 * @User: wong
 * @Date: 2020/7/10
 * @Description: 平衡二叉搜索树
 */
public class AvlBSTree {

  private Tree root;

  private int size;

  public AvlBSTree() {
  }

  // 查找
  public Tree get(int value) {
    return getTree(value);
  }

  protected Tree getTree(int key) {
    if (root == null)
      return null;
    Tree n = root;
    Tree tree = null;
    while (tree != null && n != null) {
      if (key == n.value)
        return n;
      if (key > n.value) {
        n = n.right;
      } else {
        n = n.left;
      }
    }
    return n;
  }

  // 插入
  // zig zag

  // 删除


  public final class Tree {
    int value;
    Tree left;
    Tree right;

    Tree(int value) {
      this.value = value;
    }
  }

}
