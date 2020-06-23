package com.training.common.utils;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;

/**
 * @User: wong
 * @Date: 2020/6/23
 * @Description: 文件工具类
 */
public class FileUtils {

  public static void toFile(String text, String targetPath) throws IOException {
    byte[] buffer = text.getBytes();
    FileOutputStream out = new FileOutputStream(targetPath);
    out.write(buffer);
    out.close();
  }

  /**
   * 文件转base64
   */
  public static String fileToBase64(String path) throws IOException {
    File file = createFileNotExists(path);
    FileInputStream inputFile = new FileInputStream(file);
    byte[] buffer = new byte[(int) file.length()];
    inputFile.read(buffer);
    inputFile.close();
    Base64.Encoder encoder = Base64.getEncoder();
    String suffix = path.substring(path.lastIndexOf(".") + 1);
    return "data:image/" + suffix + ";base64," + encoder.encodeToString(buffer);
  }

  /**
   * base64转文件
   */
  public static void base64ToFile(String base64, String targetPath) throws Exception {
    byte[] buffer = new BASE64Decoder().decodeBuffer(base64);
    FileOutputStream out = new FileOutputStream(targetPath);
    out.write(buffer);
    out.close();
  }


  /**
   * 文件目录不存在时，自动创建
   *
   * @param path
   */
  public static File createFileNotExists(String path) {
    return createFileNotExists(new File(path));
  }

  public static File createFileNotExists(File path) {
    if (!pathExists(path)) {
      path.getParentFile().mkdir();
    }
    if (!path.exists()) {
      try {
        path.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return path;
  }

  /**
   * 判断文件路径是否存在
   *
   * @param path
   */
  public static boolean pathExists(String path) {
    return pathExists(new File(path));
  }

  public static boolean pathExists(File path) {
    if (!path.getParentFile().exists()) {
      return false;
    }
    return true;
  }

}
