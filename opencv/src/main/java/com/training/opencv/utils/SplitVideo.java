package com.training.opencv.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Wangjunwei
 * @Date 2019/11/29 14:22
 * @Description
 */
public class SplitVideo {


  public static void runSplitVideo(String path, String targetPath, int split)
      throws FileNotFoundException {
    File file = new File(path);
    if (!file.exists() || file.isDirectory()) {
      throw new FileNotFoundException();
    }
    String suffix = path.substring(path.lastIndexOf("."));
    long videoLength = ScreenShot.getVideoLength(path); //时长：秒
    // 切割的视频 确保所有视频都能切到
    long splitNum = videoLength % split == 0 ? videoLength / split : videoLength / split + 1;
    for (int i = 0; i < splitNum; i++) {
      String comand = "cmd /c ffmpeg -ss " + i * split + " -t " + split + " -i " + path + " -vcodec copy -acodec copy " + targetPath + "\\" + i + suffix;
      runCommand(comand);
    }
  }

  /**
   * ffmpeg -ss 00:00:00 -t 00:00:30 -i keyoutput.mp4 -vcodec copy -acodec copy split.mp4
   * ffmpeg -ss 开始时间  -i 原视频 -to 截取长度  -c copy -copyts 截取后的视频
   */
  public static void runCommand(String command) {
    try {
      System.out.println(command);
      Runtime.getRuntime().exec(command);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args)
  {
    String source = "F:\\Video\\NeZha.mp4";
    String target = "F:\\Video\\output";
    try {
      runSplitVideo(source, target, 60);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
