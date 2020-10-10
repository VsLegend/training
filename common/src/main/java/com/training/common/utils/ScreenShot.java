package com.training.common.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @User wong
 * @Date 2019/11/20 17:45
 * @Description 图片截取
 */
public class ScreenShot {

  /**
   * 获取视频时长(单位：秒)
   */
  public static long getVideoLength(String sourcePath) throws Exception {
    long length = 0;
    FrameGrabber grabber = new FFmpegFrameGrabber(sourcePath);
    grabber.start();
    length = grabber.getLengthInTime() / (1000 * 1000);
    grabber.stop();
    return length;
  }

  /**
   * 视频图像截取
   */
  public static void screenShot2(String sourcePath, String targetPath) throws IOException {
    String suffix = targetPath.substring(targetPath.lastIndexOf(".") + 1);
    Java2DFrameConverter converter = new Java2DFrameConverter();
    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(sourcePath);
    grabber.start();
    Frame frame = null;
    int length = grabber.getLengthInFrames();
    int i = 0;
    do {
      frame = grabber.grabImage();
      i++;
    } while (i < length && i < 50 && i < length / 100);
    BufferedImage image = converter.convert(frame);
    // 可操作BufferImage 实现图片的缩放，剪切等功能
    ImageIO.write(image, suffix, new File(targetPath));
  }

}
