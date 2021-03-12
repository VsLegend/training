package com.training.thread.lockAlgorithm;

import java.util.Stack;

/**
 * @User: wong
 * @Date: 2020/9/14
 * @Description: CAS的ABA问题
 */
public class ABAQuestion {

  Stack<Node> stack = new Stack<>();

  ABAQuestion() {
    // 单链表 1 ->  2 -> 3
    Node node = new Node(1, null);
    Node node1 = new Node(2, null);
    node.succ = node1;
    Node node2 = new Node(3, null);
    node1.succ = node2;
    stack.push(node);
    stack.push(node1);
    // 栈顶 3
    stack.push(node2);
  }

  public static void main(String[] args) throws InterruptedException {
    ABAQuestion abaQuestion = new ABAQuestion();
    int size = abaQuestion.stack.size();
    for (int i = size - 1; i >= 0; i--) {
      Node pop = abaQuestion.stack.get(i);
      System.out.println(pop.num + " -> " + (null == pop.succ ? "null" : pop.succ.num));
    }
    Node node = new Node(4, null);
    Thread thread = new Thread(() -> {
      System.out.println("线程1开始执行");
      // 将4插入栈中 理想顺序是 1 -> 2 -> 3 -> 4
      abaQuestion.update(node, true);
      System.out.println("线程1执行完毕");
    });
    Thread thread1 = new Thread(() -> {
      System.out.println("线程2开始执行");
      // 删除节点2 顺序 1 -> 3
      Node pop3 = abaQuestion.stack.pop();
      Node pop2 = abaQuestion.stack.pop();
      pop3.num = 9;
      abaQuestion.stack.peek().succ = pop3;
      abaQuestion.update(pop3, false);
      System.out.println("线程2执行完毕");
    });
    thread.start();
    thread1.start();
    thread.join();
    thread1.join();
    System.out.println();
    for (int i = size - 1; i >= 0; i--) {
      Node pop = abaQuestion.stack.get(i);
      System.out.println(pop.num + " -> " + (null == pop.succ ? "null" : pop.succ.num));
    }
  }

  void update(Node newValue, boolean isFirst) {
    Node oldValue = stack.peek();
    if (isFirst) {
      System.out.println("线程1阻塞");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("线程1唤醒");
    }
    while (true) {
      // 假设这里是原子操作
      if (stack.peek() == oldValue) {
        stack.push(newValue);
        return;
      }
      oldValue = stack.peek();
      System.out.println("自旋");
    }
  }

}

class Node {
  Integer num;
  Node succ;

  Node(){}

  Node(int num, Node succ) {
    this.num = num;
    this.succ = succ;
  }

}
