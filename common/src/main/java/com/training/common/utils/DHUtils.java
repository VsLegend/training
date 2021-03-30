package com.training.common.utils;

import java.math.BigInteger;
import java.util.Random;

/**
 * @User: wong
 * @Date: 2021/3/29
 * @Description: DH算法
 */
public class DHUtils {


  /**
   * 计算步骤
   *
   * 1.
   * 甲乙双方设定g和p得值，甲随机设置a，将求模的计算结果A发送给乙
   * 甲: A = g^a mod p
   *
   *
   * 2.
   * 甲将数据传输给乙
   *         A
   * 甲    ----->    乙
   *
   *
   * 3.
   * 设置随机值b，求模，将结果发送给甲，同时自己得到这个密钥
   * 乙: B = g^b mod p
   * 乙：K = A^b mod p
   *
   *
   * 4.
   * 甲将数据传输给乙
   *         B
   * 乙    ----->    甲
   *
   * 5.
   * 甲根据乙的返回值B，得到密钥，这个密钥与乙得到K的相同，因为在模p下g^a*b和g^b*a相等
   * 甲：K = B^a mod p
   *
   *
   */


  /**
   * 交换者 甲
   */
  public static int aSharedSecret(int g, int p, int a) {
    return Math.floorMod(g ^ a, p);
  }


  /**
   * 交换者 乙
   */
  public static int bSharedSecret(int g, int p, int b, int aA) {
    // 密钥
    int k = Math.floorMod(aA ^ b, p);
    System.out.println("乙获得密钥：" + k);
    return Math.floorMod(g ^ b, p);
  }

  /**
   * 交换者 甲
   */
  public static void getSecretKey(int g, int p, int a, int bB) {
    int k = Math.floorMod(bB ^ a, p);
    System.out.println("甲获得密钥：" + k);
  }

  public static void main(String[] args) {
    long sT = System.currentTimeMillis();
    int g = RandomUtils.getRandomNum(2, 8); // 3-5 最合适
    int p = BigInteger.probablePrime(88, new Random()).intValue(); // 随机一个正质数
    p = p < 0 ? ~p + 1: p;
    int a =  RandomUtils.getRandomNum(10000, 1000000);
    int b =  RandomUtils.getRandomNum(10000, 1000000);
    System.out.println("g:" + g + " p:" + p + " a:" + a + " b:" + b);
    int aA = aSharedSecret(g, p, a);
    int bB = bSharedSecret(g, p, b, aA);
    getSecretKey(g, p, a, bB);
    long eT = System.currentTimeMillis();
    System.out.println("使用时间：" + (eT - sT));
  }


}
