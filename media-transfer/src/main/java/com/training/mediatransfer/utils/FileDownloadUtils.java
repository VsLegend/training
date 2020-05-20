package com.training.mediatransfer.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Encoder;

/**
 * @author nieyu
 * @date 2019/11/07 0023 01:04
 * @Desc: 文件下载接口处理
 */
@Slf4j
public class FileDownloadUtils {

  public static void downloadFiles(HttpServletResponse response, HttpServletRequest request,
      String fileName, String filePath) {
    /* 设置强制下载不打开 */
    response.setContentType("application/force-download");
    byte[] buffer = new byte[1024];
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try {
      /* 解决中文文件名称的问题 */
      File file = ResourceUtils.getFile(filePath);
      String filenameIso = encodeName(request, fileName);
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os = response.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(fis);
    }
  }

  /**
   * 解决各种浏览器下载文件乱码问题
   */
  public static String encodeName(HttpServletRequest request, String fileName) {
    //IE9之前包括IE9都包含MSIE;IE10之后都包含Trident;edge浏览器包含Edge
    String filename = "";
    String userAgent = request.getHeader("User-Agent");
    try {
      if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent
          .contains("Edge")) {
        filename = URLEncoder.encode(fileName, "UTF-8");
      } else {
        filename = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return filename;
  }

  public static String fileStream(HttpServletRequest request, HttpServletResponse response,
      String fileUrl, String fileName) {
    BASE64Encoder encoder = new BASE64Encoder();
    InputStream inputStream = null;
    BufferedInputStream bufferedInputStream = null;
    ByteArrayOutputStream baos = null;
    byte[] bytes = null;
    byte[] buffer = new byte[1024];
    try {
      URL url = new URL(fileUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      //设置超时间为3秒
      conn.setConnectTimeout(3 * 1000);
      //防止屏蔽程序抓取而返回403错误
      conn.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//      conn.setRequestProperty("Referer", referer);
      String filenameIso = encodeName(request, fileName);
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      //得到输入流
      inputStream = conn.getInputStream();
      // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
      baos = new ByteArrayOutputStream();
      bufferedInputStream = new BufferedInputStream(inputStream);
      OutputStream os = response.getOutputStream();
      int i = bufferedInputStream.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bufferedInputStream.read(buffer);
      }
      os.flush();
      bytes = baos.toByteArray();
      //sun公司的API
    } catch (IOException e) {
      e.printStackTrace();
      log.error("下载文件出错：{}", e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bufferedInputStream);
      IOUtils.closeQuietly(inputStream);
    }
    log.info("{},下载文件成功", fileUrl);
    return encoder.encodeBuffer(bytes).trim();
  }

  public static String downloadFileStream(HttpServletRequest request, HttpServletResponse response,
      String filePath, String fileName) {
    BASE64Encoder encoder = new BASE64Encoder();
    InputStream inputStream = null;
    BufferedInputStream bufferedInputStream = null;
    ByteArrayOutputStream baos = null;
    byte[] bytes = null;
    byte[] buffer = new byte[1024];
    try {
      File file = ResourceUtils.getFile(filePath);
      String filenameIso = encodeName(request, fileName);
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      //得到输入流
      inputStream = new FileInputStream(file);
      // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
      baos = new ByteArrayOutputStream();
      bufferedInputStream = new BufferedInputStream(inputStream);
      OutputStream os = response.getOutputStream();
      int i = bufferedInputStream.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bufferedInputStream.read(buffer);
      }
      os.flush();
      bytes = baos.toByteArray();
      //sun公司的API
    } catch (IOException e) {
      e.printStackTrace();
      log.error("下载文件出错：{}", e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bufferedInputStream);
      IOUtils.closeQuietly(inputStream);
    }
    log.info("{},下载文件成功", filePath);
    return encoder.encodeBuffer(bytes).trim();
  }

  public static void downloadFileByURL(HttpServletRequest request, HttpServletResponse response,
      String fileUrl, String fileName) {
    InputStream inputStream = null;
    BufferedInputStream bufferedInputStream = null;
    byte[] buffer = new byte[1024];
    try {
      URL url = new URL(fileUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      //设置超时间为3秒
      conn.setConnectTimeout(3 * 1000);
      //防止屏蔽程序抓取而返回403错误
      conn.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//      conn.setRequestProperty("Referer", referer);
      String filenameIso = encodeName(request, fileName + ".pdf");
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      //得到输入流
      inputStream = conn.getInputStream();
      bufferedInputStream = new BufferedInputStream(inputStream);
      OutputStream os = response.getOutputStream();
      int i = bufferedInputStream.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bufferedInputStream.read(buffer);
      }
    } catch (IOException e) {
      e.printStackTrace();
      log.error("下载文件出错：{}", e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bufferedInputStream);
      IOUtils.closeQuietly(inputStream);
    }
    log.info("{},下载文件成功", fileUrl);
  }

  /**
   * 从输入流中获取字节数组
   */
  public static byte[] readInputStream(InputStream inputStream) throws IOException {
    byte[] buffer = new byte[1024];
    int len = 0;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    while ((len = inputStream.read(buffer)) != -1) {
      bos.write(buffer, 0, len);
    }
    bos.close();
    return bos.toByteArray();
  }

  /**
   * 本地图片base64编码
   */
  public static String base64Img(String path) {
    InputStream is = null;
    ByteArrayOutputStream outStream = null;
    try {
      is = new FileInputStream(path);
      outStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int len = 0;
      while ((len = is.read(buffer)) != -1) {
        outStream.write(buffer, 0, len);
      }
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new BASE64Encoder().encode(outStream.toByteArray())
        .replace("data/image/jpeg/base64/", "");
  }

  public static void downloadByStream(HttpServletResponse response, HttpServletRequest request,
      String fileName, String filePath) {
    /* 设置强制下载不打开 */
    byte[] buffer = new byte[1024];
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try {
      /* 解决中文文件名称的问题 */
      File file = ResourceUtils.getFile(filePath);
      String filenameIso = encodeName(request, fileName);
      /* 设置文件名 */
      response.addHeader("Accept-Ranges", "bytes");
      response.addHeader("Content-Type", "application/octet-stream");
      //设置response编码
      response.setCharacterEncoding("UTF-8");
      //设置输出文件类型
      response.setContentType("video/mpeg4");
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os = response.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(fis);
    }
  }

  public static void videoCut(HttpServletResponse response,
      String sourcePath, int set) {
    File file = new File(sourcePath);
    response.addHeader("Content-Type", "application/octet-stream");
    InputStream inputStream = null;
    OutputStream os = null;
    BufferedInputStream buffered = null;
    try {
      inputStream = new FileInputStream(sourcePath);
      buffered = new BufferedInputStream(inputStream);
      os = response.getOutputStream();
      int i = 0;
      int available = buffered.available();
      byte[] bytes = new byte[available / 10];
      int flag = 0;
      while (-1 != (i = buffered.read(bytes))) {
        if (flag == set) {
          os.write(bytes, 0, i);
          return;
        }
        flag++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(os);
      IOUtils.closeQuietly(buffered);
      IOUtils.closeQuietly(inputStream);
    }
  }

}
