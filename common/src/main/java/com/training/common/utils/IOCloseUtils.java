package com.training.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @User: wong
 * @Date: 2020/6/22
 * @Description: 关闭流工具类
 */

@Slf4j
public class IOCloseUtils {

  /**
   * 关闭工作簿
   */
  public static void closeWorkBook(Workbook workbook) {
    try {
      if (workbook != null) {
        workbook.close();
      }
    } catch (IOException e) {
      log.info("关闭workBook错误", e);
    }
  }

  /**
   * 关闭输入流
   */
  public static void closeInStream(InputStream inputStream) {
    try {
      if (inputStream != null) {
        inputStream.close();
      }
    } catch (IOException e) {
      log.info("关闭输入流错误", e);
    }
  }

  /**
   * 关闭输出流
   */
  public static void closeOutStream(OutputStream outputStream) {
    try {
      if (outputStream != null) {
        outputStream.close();
      }
    } catch (IOException e) {
      log.info("关闭输出流错误", e);
    }
  }

}
