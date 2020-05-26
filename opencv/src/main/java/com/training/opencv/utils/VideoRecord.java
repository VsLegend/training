package com.training.opencv.utils;

import javax.swing.WindowConstants;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;

/**
 * @author Wangjunwei
 * @Date 2019/11/22 18:00
 * @Description
 */
public class VideoRecord {

  public OpenCVFrameGrabber grabber;

  public VideoRecord() {
    if (null == grabber) {
      grabber = new OpenCVFrameGrabber(0);
    }
  }

  /**
   * 打开摄像头
   */
  public void getCamera() throws Exception, InterruptedException {
//    VideoInputFrameGrabber grabber1 = VideoInputFrameGrabber.createDefault(0);
    this.grabber.start();
    CanvasFrame canvas = new CanvasFrame("软件");
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    canvas.setCanvasSize(900, 600);
    while (true) {
      // 判断窗口是否关闭
      if (!canvas.isDisplayable()) {
        this.grabber.stop();
        System.exit(-1);
      }
      Frame frame = this.grabber.grabFrame();
      canvas.showImage(frame);
      // 每隔50m抓取一次图像
      Thread.sleep(50);
    }
  }

  public static void main(String[] args) {
    VideoRecord videoRecord = new VideoRecord();
    try {
      videoRecord.getCamera();
    } catch (Exception | InterruptedException e) {
      e.printStackTrace();
    }
  }

}
