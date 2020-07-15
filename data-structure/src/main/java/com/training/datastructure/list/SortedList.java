package com.training.datastructure.list;

/**
 * @User: wong
 * @Date: 2020/7/14
 * @Description: 有序列表
 */
public class SortedList {

  private Node first;

  private Node last;

  private int size;

  SortedList(){
    first = null;
    last = null;
    size = 0;
  }

  public void judgeIndex(int index) {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException();
  }

  // 查找
  public int get(int index) {
    judgeIndex(index);
    if (index < size >> 1) {
      Node node = first;
      for (int i = 0; i < index; i++) {
        node = node.succ;
      }
      return node.num;
    } else {
      Node node = last;
      for (int i = 0; i < size-index; i++) {
        node = node.prev;
      }
      return node.num;
    }
  }



  // 新增



  // 删除




  class Node {
    Integer num;
    Node prev;
    Node succ;

    Node(){}

    Node(int num, Node prev, Node succ) {
      this.num = num;
      this.prev = prev;
      this.succ = succ;
    }

  }

}
