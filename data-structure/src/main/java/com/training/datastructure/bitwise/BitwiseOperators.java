package com.training.datastructure.bitwise;

import java.util.Random;

/**
 * @User: wong
 * @Date: 2020/6/10
 * @Description: 位运算符
 */
public class BitwiseOperators {

  public static void main(String[] args) {
    System.out.println(~0b00000000000000000000000000000000);
    System.out.println("----------------------------与运算");
    and();
    System.out.println("----------------------------或运算");
    or();
    System.out.println("----------------------------异或运算");
    xor();
    System.out.println("----------------------------取反运算");
    negation();
    System.out.println("----------------------------左位移");
    left();
    System.out.println("----------------------------右位移");
    right();


    System.out.println("----------------------------位移乘除法");
    multiplication();
    System.out.println("----------------------------通过异或交换两数");
    swap();
    System.out.println("----------------------------通过与运算判断奇偶数");
    judgeOddEven();
    System.out.println("----------------------------交换符号");
    reversal();
    System.out.println("----------------------------通过位移和取反或通过位移与异或求绝对值");
    absolute();
    System.out.println("----------------------------高低位交换");
    exchange();
    System.out.println(Integer.toBinaryString(0b01010011 >>> 4));
    System.out.println(Integer.toBinaryString(0b01010011 ^ 0b00000101));
    System.out.println(Integer.MAX_VALUE);
    System.out.println(0x7FFFFFFF);
  }


  /**
   * 高低位交换，高16位与低16位交换
   * |或运算的性质，
   */
  public static void exchange() {
    Integer num = 34520;
    System.out.println("原数字：" + num + " 二进制：" + Integer.toBinaryString(num));
    num = num >> 8 | num << 8;
    System.out.println("高低位转换后：" + num + " 二进制：" + Integer.toBinaryString(num));
  }


  /**
   * 位移求绝对值
   * 整数的绝对值是其本身，负数的绝对值可以取反+1。
   * int类型，整数右位移31位为0，负数右位移31位为-1
   */
  public static void absolute() {
    Integer num = new Random().nextInt(100);
    System.out.println("原数字：" + num + " 二进制：" + Integer.toBinaryString(num));
    int a = num >> 31;
    System.out.println("右位移后的数字：" + a + " 取反求得绝对值：" + (a == 0 ? num : (~num + 1)));
    System.out.print("异或求绝对值：" + (a ^ num - a)); // a=0, y = (a^num - a), so y = num; a = -1, y = ~num -(-1) = ~num + 1
    System.out.println();
  }


  /**
   * 取反
   * 交换符号将正数变成负数，负数变成正数
   * 整数取反加1，正好变成其对应的负数(补码表示)；负数取反加一，则变为其原码，即正数
   * 同理，可以用取反取得整数的绝对值
   */
  public static void reversal() {
    Integer num = new Random().nextInt(100);
    System.out.println("原数字：" + num + " 二进制：" + Integer.toBinaryString(num));
    Integer reversal = ~num + 1;
    System.out.println("取反：" + reversal + " 二进制：" + Integer.toBinaryString(reversal));
    System.out.println("取反后取反：" + (~reversal + 1) + " 二进制：" + Integer.toBinaryString(~reversal + 1));
  }


  /**
   * 通过与运算判断奇偶数
   */
  public static void judgeOddEven() {
    Integer num = new Random().nextInt(100);
    System.out.println("数字：" + num + " 二进制：" + Integer.toBinaryString(num));
    if (0 == (num & 1)) {
      System.out.println("这个数字是偶数");
    }
  }


  /**
   * 通过异或交换两数
   */
  public static void swap() {
    int a = 8;
    int b = 22;
    // 普通操作
    a = a + b;
    b = a - b;
    a = a - b;
    System.out.println("a: " + a + ", b: " + b);
    // 异或操作
    a ^= b; // a ^ b
    b ^= a; // b ^ (a ^ b) = (b ^ b) ^ a = a
    a ^= b; // (a ^ b) ^ b ^ (a ^ b) = （a ^ a） ^ (b ^ b) ^ b = b
    System.out.println("a: " + a + ", b: " + b);
  }


  /**
   * 左位移1位，相当于乘2
   * 右位移1位，相当于除2
   */
  public static void multiplication() {
    int num = 8;
    System.out.println(8 << 1); // 16
    System.out.println(8 >> 1); // 4
  }


  /** ********************************************************************************
   * 位运算的基本操作
   * 注：int为4字节，每字节8bit(位)，以下的8bit都为简写  00000000 00000000 00000000 00000000
   * a & a = a, a | a = a, a ^ a = 0, ~a = -a - 1
   */

  /**
   * & 与运算 同为1时结果为1，否则为0
   * 即 a & a = a
   */
  public static void and() {
    // & 位与运算，可以用作位与运算符，当&两边的表达式不是Boolean类型的时候，&表示按位操作。
    int a = 15; // 0000 1111
    int b = 1; //  0000 0001
    int c = 44; // 0010 1100
    System.out.println("二进制" + Integer.toBinaryString(a & b)); // 0000 0001
    System.out.println("二进制" + Integer.toBinaryString(a & c)); // 0000 1100
    System.out.println("二进制" + Integer.toBinaryString(b & c)); // 0000 0000
  }

  /**
   * | 或运算 同为0时为0，否则为1
   */
  public static void or() {
    int a = 15; // 0000 1111
    int b = 1; //  0000 0001
    int c = 44; // 0010 1100
    System.out.println("二进制" + Integer.toBinaryString(a | b)); // 0000 1111
    System.out.println("二进制" + Integer.toBinaryString(a | c)); // 0010 1111
    System.out.println("二进制" + Integer.toBinaryString(b | c)); // 0010 1101
  }

  /**
   * ^ 异或运算 两个位相同为0，否则为1
   * 与|或运算不同，|是在两个同时都为0时才为0，^是在两个位相同时才为0
   * 0 ^ x = x
   */
  public static void xor() {
    int a = 15; // 0000 1111
    int b = 1; //  0000 0001
    int c = 44; // 0010 1100
    System.out.println("二进制" + Integer.toBinaryString(a ^ b)); // 0000 1110
    System.out.println("二进制" + Integer.toBinaryString(a ^ c)); // 0010 0011
    System.out.println("二进制" + Integer.toBinaryString(b ^ c)); // 0010 1101
  }

  /**
   * ~ 取反运算 0变1，1变0
   */
  public static void negation() {
    int a = 15; // 0000 1111
    int b = 1; //  0000 0001
    int c = 44; // 0010 1100
    System.out.println("二进制" + Integer.toBinaryString(~a)); // 1111 0000
    System.out.println("二进制" + Integer.toBinaryString(~b)); // 1111 1110
    System.out.println("二进制" + Integer.toBinaryString(~c)); // 1101 0011
  }

  /**
   * << 左移运算，向左进行移位操作，高位丢弃，低位补0
   */
  public static void left() {
    int a = 15; // 0000 1111
    int b = 1; //  0000 0001
    int c = 44; // 0010 1100
    System.out.println("二进制" + Integer.toBinaryString(a << 2)); // 0011 1100
    System.out.println("二进制" + Integer.toBinaryString(b << 3)); // 0000 1000
    System.out.println("二进制" + Integer.toBinaryString(c << 4)); // 1100 0000
  }

  /**
   * >> 右移运算，向右进行移位操作，高位补0，低位舍弃，对于有符号数，高位补符号位
   */
  public static void right() {
    int a = 15; // 0000 1111
    int b = 1; //  0000 0001
    int c = 44; // 0010 1100
    System.out.println("二进制" + Integer.toBinaryString(a >> 2)); // 0000 0011
    System.out.println("二进制" + Integer.toBinaryString(b >> 3)); // 0000 0000
    System.out.println("二进制" + Integer.toBinaryString(c >> 4)); // 0000 0010
  }
}
