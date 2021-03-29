package com.training.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @User: wong
 * @Date: 2021/3/29
 * @Description: 哈希算法相关方法
 */
public class HashUtils {

  public static final String HASH_MD5 = "MD5";

  public static final String HASH_SHA1 = "SHA-1";

  public static final String HASH_SHA256 = "SHA-256";

  public static final String HASH_SHA384 = "SHA-384";

  public static final String HASH_SHA512 = "SHA-512";

  /**
   * 获取消息摘要
   * @param content 消息内容
   * @param hashType 摘要类型
   * @return 摘要
   * @throws NoSuchAlgorithmException
   */
  public static String hash(String content, String hashType) throws NoSuchAlgorithmException {
    StringBuilder sb = new StringBuilder();
    MessageDigest instance = MessageDigest.getInstance(hashType);
    // 添加数据
    instance.update(content.getBytes(StandardCharsets.UTF_8));
    // 获得摘要信息
    byte[] digest = instance.digest();
    // 转码
    for (byte b : digest) {
      // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
      sb.append(String.format("%02X", b));
    }
    return sb.toString();
  }


  public static void main(String[] args) throws NoSuchAlgorithmException {
    System.out.println(hash("dsaubc0182082dbsjabcjasdasda", HASH_MD5));
  }


}
