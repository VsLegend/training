package com.training.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @User: wong
 * @Date: 2020/11/2
 * @Description: base64转码
 */
public class Base64Utils {

  // 字符串转Base64编码
  public static String str2Base64(String str) {
    return byte2Base64(str.getBytes());
  }

  // Base64编码转字符串
  public static String base642Str(String base64Key) throws IOException {
    byte[] bytes = base642Byte(base64Key);
    return new String(bytes);
  }


  // 字节数组转Base64编码
  public static String byte2Base64(byte[] bytes) {
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(bytes);
  }

  // Base64编码转字节数组
  public static byte[] base642Byte(String base64Key) throws IOException {
    BASE64Decoder decoder = new BASE64Decoder();
    return decoder.decodeBuffer(base64Key);
  }

}
