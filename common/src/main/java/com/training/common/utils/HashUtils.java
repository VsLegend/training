package com.training.common.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @User: wong
 * @Date: 2021/3/29
 * @Description: 哈希算法相关方法
 */
public class HashUtils {

  public static final String Hmac = "Hmac";

  public static final String HASH_MD5 = "MD5";

  public static final String HASH_SHA1 = "SHA-1";

  public static final String HASH_SHA256 = "SHA-256";

  public static final String HASH_SHA384 = "SHA-384";

  public static final String HASH_SHA512 = "SHA-512";

  public static final String HASH_SALT = "salt.8757.AMD.yes";


  /**
   * 获取消息摘要
   * @param content 消息内容
   * @param hashType 摘要类型
   * @return 摘要
   * @throws NoSuchAlgorithmException
   */
  public static String hash(String content, String hashType) throws NoSuchAlgorithmException {
    MessageDigest instance = MessageDigest.getInstance(hashType);
    // 添加数据
    instance.update(content.getBytes(StandardCharsets.UTF_8));
    // 添加摘要
    instance.update(HASH_SALT.getBytes(StandardCharsets.UTF_8));
    // 获得摘要信息
    byte[] digest = instance.digest();
    return byte2Str(digest);
  }

  /**
   * Hmac 带有key的安全哈希算法
   * @param message
   * @param hashType
   * @return
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   */
  public static String hmac(String message, String hashType) throws NoSuchAlgorithmException, InvalidKeyException {
    String type = Hmac + hashType;
    KeyGenerator keyGen = KeyGenerator.getInstance(type);
    SecretKey key = keyGen.generateKey();
    // 打印随机生成的key:
    byte[] keyBytes = key.getEncoded();
    System.out.println("key：" + new BigInteger(1, keyBytes).toString(16));
    Mac mac = Mac.getInstance(type);
    // 生产密钥 作用类似与MD5加盐
    mac.init(key);
    mac.update(message.getBytes(StandardCharsets.UTF_8));
    // 获得摘要信息
    byte[] bytes = mac.doFinal();
    return byte2Str(bytes);
  }

  /**
   * 二进制转码
   * @param bytes
   * @return
   */
  private static String byte2Str(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
      sb.append(String.format("%02X", b));
    }
    return sb.toString();
  }


  public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
    System.out.println(hmac("099999", HASH_MD5));
    System.out.println(hash("099999", HASH_MD5));
  }


}
