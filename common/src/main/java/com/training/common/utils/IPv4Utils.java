package com.training.common.utils;

/**
 * @User: wong
 * @Date: 2021/4/19
 * @Description: ipv4 转换工具
 */
public class IPv4Utils {

//  public static void main(String[] args) {
//    String ip = "10.108.149.219";
//    // step1: 分解IP字符串，并对应写对字节数组
//    byte[] ip1 = ipToBytes(ip);
//    // step2: 对字节数组里的每个字节进行左移位处理，分别对应到整型变量的4个字节
//    int ip2 = bytesToInt(ip1);
//    System.out.println("整型ip ----> " + ip2);
//    // step3: 对整型变量进行右位移处理，恢复IP字符串
//    String ip3 = intToIp(ip2);
//    System.out.println("字符串ip---->" + ip3);
//  }

  /**
   * ip转int
   */
  public static int ip2Int(String ipAddr) {
    byte[] ip1 = ipToBytes(ipAddr);
    return bytesToInt(ip1);
  }

  /**
   * 把int->string地址
   *
   * @param ipInt
   * @return String
   */
  public static String intToIp(int ipInt) {
    // 先强转二进制，再进行移位处理
    // 右移3个字节（24位），得到IP地址的第一段也就是byte[0],为了防止符号位是1也就是负数，最后再一次& 0xFF
    return String.valueOf(((ipInt & 0xFF000000) >> 24) & 0xFF) + '.' +
                    ((ipInt & 0xFF0000) >> 16) + '.' +
                    ((ipInt & 0xFF00) >> 8) + '.' +
                    (ipInt & 0xFF);
  }


  /**
   * 第一步，把IP地址分解为一个btye数组
   */
  public static byte[] ipToBytes(String ipAddr) {
    // 初始化字节数组，定义长度为4
    byte[] ret = new byte[4];
    try {
      String[] ipArr = ipAddr.split("\\.");
      // 将字符串数组依次写入字节数组
      ret[0] = (byte) (Integer.parseInt(ipArr[0]));
      ret[1] = (byte) (Integer.parseInt(ipArr[1]));
      ret[2] = (byte) (Integer.parseInt(ipArr[2]));
      ret[3] = (byte) (Integer.parseInt(ipArr[3]));
      return ret;
    } catch (Exception e) {
      throw new IllegalArgumentException("invalid IP : " + ipAddr);
    }
  }

  /**
   * 根据位运算把 byte[] -> int
   * 原理：将每个字节强制转化为8位二进制码，然后依次左移8位，对应到Int变量的4个字节中
   */
  public static int bytesToInt(byte[] bytes) {
    // 先移位后直接强转的同时指定位数
    int addr = bytes[3] & 0xFF;
    addr |= ((bytes[2] << 8) & 0xFF00);
    addr |= ((bytes[1] << 16) & 0xFF0000);
    addr |= ((bytes[0] << 24) & 0xFF000000);
    return addr;
  }


}
