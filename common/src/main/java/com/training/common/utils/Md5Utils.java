package com.training.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @User: wong
 * @Date: 2020/10/21
 * @Description: md5 加盐加密
 */
public class Md5Utils {

  private static final String SALT = "MD5test1234.dsacnxjzks.921dksanca.dsnsjaKHKS012.219dJGJ-+2d";

  /**
   * md5 加密
   */
  public static String encrypt(String passWord) {
    passWord = passWord + SALT;
    return DigestUtils.md5DigestAsHex(passWord.getBytes());
  }

  /**
   * md5 匹配
   */
  public static Boolean matches(String passWord, String encodePassWord) {
    return encrypt(passWord).equals(encodePassWord);
  }

}
