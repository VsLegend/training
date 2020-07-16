package com.training.datastructure.tree;

import java.util.*;

/**
 * @User: wong
 * @Date: 2020/7/1
 * @Description: 二叉树
 */
public class BinaryTreeAlgorithm {

  public class Tree<T> {
    T value;
    Tree left;
    Tree right;

    Tree(T value) {
      this.value = value;
    }
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  /**
   * 1
   * 2 3
   * 45 67
   */
  public Tree<Integer> getRoot() {
//    Tree<Integer> root = new Tree<Integer>(1);
//    Tree<Integer> tree1 = new Tree<Integer>(2);
//    Tree<Integer> tree2 = new Tree<Integer>(3);
//    Tree<Integer> tree3 = new Tree<Integer>(4);
//    Tree<Integer> tree4 = new Tree<Integer>(5);
//    Tree<Integer> tree5 = new Tree<Integer>(6);
//    Tree<Integer> tree6 = new Tree<Integer>(7);
    Tree<Integer> root = new Tree<Integer>(2);
    Tree<Integer> tree1 = new Tree<Integer>(1);
    Tree<Integer> tree2 = new Tree<Integer>(3);
    root.left = tree1;
    root.right = tree2;
    return root;
  }

  /**
   * 1
   * 2 3
   * 45 67
   */
  public TreeNode getTreeRoot() {
    TreeNode root = new TreeNode(1);
    TreeNode tree1 = new TreeNode(2);
    TreeNode tree2 = new TreeNode(3);
    TreeNode tree3 = new TreeNode(4);
    TreeNode tree4 = new TreeNode(5);
    TreeNode tree5 = new TreeNode(6);
    TreeNode tree6 = new TreeNode(7);
    root.left = tree1;
    root.right = tree2;
    tree1.left = tree3;
    tree1.right = tree4;
    tree2.left = tree5;
    tree2.right = tree6;
    return root;
  }

  // 二叉树搜索
  public static void main(String[] args) {
    BinaryTreeAlgorithm binaryTree = new BinaryTreeAlgorithm();
    TreeNode root = binaryTree.getTreeRoot();
    Queue<Integer> queue = new LinkedList<>();
    Stack<Integer> stack = new Stack<>();
    TreeNode hot = root.left;
    String s = "fsafsaaga";
    char[] chars = s.toCharArray();
    int i;
    System.out.println(i = 2 >> 1);
    System.out.println(i = 3 >> 1);
    System.out.println(i = 4 >> 1);
  }

  public int respace(String[] dictionary, String sentence) {
    String result = "";
    for (String s : dictionary) {
      result = sentence.replaceAll(s, " ");
    }
    return 0;
  }


  // 验证是否是二叉搜索树
  public boolean isValidBST(TreeNode root) {
    if (root == null)
      return true;
    //in-order
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;
    TreeNode last = null;
    while (node != null || !stack.empty()) {
      if (null != node) {
        stack.push(node);
        node = node.left;
      } else {
        TreeNode n = stack.pop();
        if (n != null) {
          if (last != null && n.val <= last.val)
            return false;
          last = n;
        }
        node = n.right;
      }
    }
    return true;
  }

  // 由前序、中序或中序后续恢复一颗二叉树

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0)
      return null;
    TreeNode root = new TreeNode(preorder[0]);
    load(root, preorder, inorder);
    return root;
  }

  public void load(TreeNode root, int[] preorder, int[] inorder) {
    boolean b = true;
    int index = 0;
    while (b) {
      if (index < inorder.length && inorder[index] == root.val) {
        b = false;
      } else {
        index++;
      }
    }
    int[] ofRange = Arrays.copyOfRange(preorder, 1, preorder.length);
    // 分别遍历左右子树
    int[] inLeft = Arrays.copyOfRange(inorder, 0, index - 1);
    int[] inRight = Arrays.copyOfRange(inorder, index + 1, inorder.length);
    int[] preLeft = Arrays.copyOf(ofRange, inLeft.length);
    int[] preRight = Arrays.copyOfRange(ofRange, preLeft.length, ofRange.length);
  }


  // 之字形遍历二叉树
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();
    if (root == null)
      return result;
    LinkedList<TreeNode> queue = new LinkedList<>();
    TreeNode node;
    queue.add(root);
    int i = 0;
    while (!queue.isEmpty()) {
      List<Integer> nums = new LinkedList<>();
      int size = queue.size();
      for (int j = 0; j < size; j++) {
        if ((i & 1) != 0) {
          node = queue.removeLast();
        } else {
          node = queue.removeFirst();
        }
        if (node == null)
          break;
        if ((i & 1) != 0) {
          queue.addFirst(node.right);
          queue.addFirst(node.left);
        } else {
          queue.add(node.left);
          queue.add(node.right);
        }
        nums.add(node.val);
      }
      i++;
      result.add(nums);
    }
    return result;
  }


  /**
   * BFS 广度优先搜索 基于队列的层次搜索
   */

  /**
   * DFS 深度优先搜索 基于二叉树的前序遍历
   */


  //二叉树遍历

  /**
   * 前序遍历
   * 顺序：根节点  ->  左节点  ->  右节点
   */
  public void preOderTraversal(Tree<Integer> root) {
    LinkedList<Tree<Integer>> stack = new LinkedList<>();
    Tree<Integer> node = root;
    while (node != null || stack.size() > 0) {
      if (node != null) {
        // 访问
        System.out.println("Node:" + node.value);
        // 入栈
        stack.add(node);
        node = node.left;
      } else {
        // 出栈
        Tree<Integer> last = stack.removeLast();
        node = last.right;
      }
    }
  }

  // 前序遍历 法二 ，仅适用于前序遍历
  public void preOderTraversal2(Tree<Integer> root) {
    LinkedList<Tree<Integer>> stack = new LinkedList<>();
    Tree<Integer> node;
    stack.add(root);
    while (stack.size() > 0) {
      // 先访问根节点
      node = stack.removeLast();
      System.out.println("Node:" + node.value);
      // 右节点入栈
      if (null != node.right) {
        stack.add(node.right);
      }
      // 左节点入栈
      if (null != node.left) {
        stack.add(node.left);
      }
    }
  }

  // 前序遍历 法三
  public void preOderTraversal3(Tree<Integer> root) {
    LinkedList<Tree<Integer>> stack = new LinkedList<>();
    Tree<Integer> node = root;
    stack.add(root);
    while (stack.size() > 0) {
      if (node != null) {
        // 先访问根节点
        System.out.println("Node:" + node.value);
        stack.add(node.right);
        node = node.left;
      } else {
        node = stack.removeLast();
      }
    }
  }


  /**
   * 中序遍历
   * 顺序：左节点  ->  根节点  ->  右节点
   */
  public void inOderTraversal(Tree<Integer> root) {
    LinkedList<Tree<Integer>> stack = new LinkedList<>();
    Tree<Integer> node = root;
    while (node != null || stack.size() > 0) {
      if (node != null) {
        // 入栈
        stack.add(node);
        node = node.left;
      } else {
        // 出栈
        Tree<Integer> last = stack.removeLast();
        // 访问
        System.out.println("Node:" + last.value);
        node = last.right;
      }
    }
  }

  /**
   * 后序遍历
   * 顺序：左节点  ->  右节点  ->  根节点
   */
  public void postOderTraversal(Tree<Integer> root) {
    LinkedList<Tree<Integer>> stack = new LinkedList<>();
    Tree<Integer> node = root;
    Tree<Integer> visit = new Tree<Integer>(-1);
    // 入栈规则： 左节点不为空
    // 出栈规则： 当前节点为空，或者右节点为已出栈节点
    while (node != null || stack.size() > 0) {
      // 入栈
      while (node != null) {
        stack.add(node);
        node = node.left;
      }
      Tree<Integer> last = stack.getLast();
      // 出栈
      if (last.right == null || visit == last.right) {
        // 访问
        visit = last;
        System.out.println("Node:" + last.value);
        stack.removeLast();
      } else {
        node = stack.getLast().right;
      }
    }
  }

}
