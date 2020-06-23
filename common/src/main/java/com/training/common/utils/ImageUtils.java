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
   * @param path
   * @param target
   */
  public static File pngToJpg(String path, String target) throws IOException {
    BufferedImage bufferedImage;
    File png = FileUtils.createFileNotExists(path);
    File jpg = FileUtils.createFileNotExists(target);
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
      log.info("PNG 转 JPG 失败");
    }
    return jpg;
  }

}
