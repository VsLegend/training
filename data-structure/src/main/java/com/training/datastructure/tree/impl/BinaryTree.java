package com.training.datastructure.tree.impl;

/**
 * @User: wong
 * @Date: 2020/7/16
 * @Description: 二叉树
 */
public class BinaryTree {

  private Tree root;

  private int size = 0;


  /**
   * 查找
   *
   * @param value
   * @return
   */
  public Tree search(int value) {
    Tree tree = getTree(value);
    return tree.value == value ? tree : null;
  }

  /**
   * 插入
   *
   * @param value
   */
  public void insert(int value) {
    if (root == null) {
      root = new Tree(null, value, null, null);
      size++;
      return;
    }
    Tree tree = getTree(value);
    // 判断三种情况，插入元素存在
    if (value == tree.value)
      return;
    Tree n = new Tree(null, value, null, tree);
    // 插入元素不存在，判断插入到父节点的左还是右节点
    if (value < tree.value) {
      tree.left = n;
    } else {
      tree.right = n;
    }
    size++;
  }

  /**
   * 删除
   *
   * @param value
   * @return
   */
  public boolean remove(int value) {
    if (root == null)
      return true;
    Tree tree = getTree(value);
    if (value != tree.value)
      return false;
    // 三种情况，删除节点时，左孩子不存在，或者右孩子不存在，或者左右孩子都存在的情况
    Tree parent = tree.parent;
    if (parent.left == tree) {
      if (null == tree.left) {
        parent.left = tree.right;
        tree.right.parent = parent;
      } else if (parent.right == null) {
        parent.left = tree.left;
        tree.left.parent = parent;
      } else {
        // 获取直接后继，并与之交换位置，然后再对交换后的节点删除（须知：其直接后继是至多只有一个孩子存在的，也是就是右孩子存在
        Tree succ = getSucc(tree);
        exchange(tree, succ);
        if (succ.right == null) {
          succ.parent = null;
          succ = null;
        } else {
          Tree r = succ.right;
          exchange(succ, r);
          r.right = null;
          succ = null;
        }
      }
    } else {
      if (null == tree.left) {
        parent.right = tree.right;
        tree.right.parent = parent;
      } else if (parent.right == null) {
        parent.right = tree.left;
        tree.left.parent = parent;
      } else {
        Tree succ = getSucc(tree);
        exchange(tree, succ);
        if (succ.right == null) {
          succ.parent = null;
          succ = null;
        } else {
          Tree r = succ.right;
          exchange(succ, r);
          r.right = null;
          succ = null;
        }
      }
    }
    size++;
    return true;
  }


  protected Tree exchange(Tree t, Tree s) {
    Integer v = s.value;
    s.value = t.value;
    t.value = v;
    return s;
  }

  /**
   * 获取中序遍历下的直接后继
   *
   * @param tree
   * @return
   */
  protected Tree getSucc(Tree tree) {
    if (tree == null)
      return null;
    Tree n = tree.right;
    while (n != null) {
      Tree x = n.left;
      if (x == null) {
        return n;
      }
      n = x;
    }
    return tree.right;
  }

  /**
   * 获取中序遍历下的直接前驱
   *
   * @param tree
   * @return
   */
  protected Tree getPrev(Tree tree) {
    if (tree == null)
      return null;
    Tree n = tree.left;
    while (n != null) {
      Tree x = n.right;
      if (x == null) {
        return n;
      }
      n = x;
    }
    return tree.left;
  }


  /**
   * 获取指定值的节点
   *
   * @param key
   * @return
   */
  protected Tree getTree(int key) {
    if (root == null)
      return null;
    Tree n = root;
    Tree tree = null;
    while (n != null) {
      if (key == n.value)
        return n;
      tree = n;
      if (key > n.value) {
        n = n.right;
      } else {
        n = n.left;
      }
    }
    // 如果没有找到该节点，返回父节点
    return tree;
  }

  public int getSize() {
    return size;
  }

  public final class Tree {
    Integer value;
    Tree left;
    Tree right;

    Tree parent;

    Tree() {
    }

    Tree(int value) {
      this.value = value;
    }

    Tree(Tree left, int value, Tree right, Tree parent) {
      this.left = left;
      this.value = value;
      this.right = right;
      this.parent = parent;
    }

  }
}
