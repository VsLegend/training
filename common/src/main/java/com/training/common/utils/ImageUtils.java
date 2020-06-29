package com.training.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @User: wong
 * @Date: 2020/6/23
 * @Description: 图片转换处理 工具类
 */

@Slf4j
public class ImageUtils {

  /**
   * png 转 jpg
   * @param source
   * @param target
   */
  public static File pngToJpg(String source, String target) throws IOException {
    File png = FileUtils.createFileNotExists(source);
    File jpg = FileUtils.createFileNotExists(target);
    return pngToJpg(png, jpg);
  }

  public static File pngToJpg(File png, File jpg) throws IOException {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(png);
      // 设置相同大小
      BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
              bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
      ImageIO.write(newBufferedImage, "jpg", jpg);
      log.info("PNG 转 JPG 成功");
    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException("PNG 转 JPG 失败");
    }
    return jpg;
  }

}
