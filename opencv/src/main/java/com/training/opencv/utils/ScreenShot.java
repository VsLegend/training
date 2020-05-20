package com.training.opencv.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;

/**
 * @author Wangjunwei
 * @Date 2019/11/20 17:45
 * @Description 图片截取
 */
public class ScreenShot {

  /**
   * 获取视频时长(单位：秒)
   */
  public static long getVideoLength(String sourcePath) {
    long length = 0;
    FrameGrabber grabber = new FFmpegFrameGrabber(sourcePath);
    try {
      grabber.start();
      length = grabber.getLengthInTime() / (1000 * 1000);
      grabber.stop();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return length;
  }

  /**
   * 视频图像截取 截取视频的一帧，并保存为图片bmp jpg jpeg png tiff等格式。截取的图片大小为原视频的大小
   */
  public static void screenShot(String sourcePath, String targetPath) {
    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(sourcePath);
    // C语言图像类型
    OpenCVFrameConverter.ToIplImage converter = new ToIplImage();
    try {
      grabber.start();
      int length = grabber.getLengthInFrames();
      System.out.println("视频帧数：" + length);
      if (1 > length) {
        return;
      }
      Frame frame = null;
      int i = 0;
      // 获取一帧
      do {
        frame = grabber.grabImage();
        i++;
      } while (i < length && i < 50 && i < length / 100); //尽量帧数小，避免占用资源，也要避开视频开始的黑屏
      IplImage iplImage = converter.convertToIplImage(frame);
      opencv_imgcodecs.cvSaveImage(targetPath, iplImage);
      grabber.release();
      grabber.stop();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 视频图像截取
   */
  public static void screenShot2(String sourcePath, String targetPath) {
    String suffix = targetPath.substring(targetPath.lastIndexOf(".") + 1);
    Java2DFrameConverter converter = new Java2DFrameConverter();
    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(sourcePath);
    try {
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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 视频转音频
   */
  public static void videoToVoice(String sourcePath, String targetPath) {
    FFmpegFrameGrabber ffmpeg = new FFmpegFrameGrabber(sourcePath);
    AudioFormat audioFormat = initSourceDataLine(ffmpeg);
    Frame frame = null;
    try {
      ffmpeg.start();
      while (true) {
        frame = ffmpeg.grabSamples();
        if (null == frame) {
          ffmpeg.stop();
          System.exit(0);
        }
        Buffer[] samples = frame.samples;
        Buffer sample = samples[0];
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
  }


  /**
   * 输出音频
   *
   * @param sampleFormat 音频格式
   * @param samples 音频数据
   */
  private static void processAudio(int sampleFormat, Buffer[] samples) {
    //
    FloatBuffer leftF, rightF;
    ShortBuffer leftS, rightS;
    ByteBuffer leftByte, rightByte;
    byte[] leftData, rightData;
    byte[] result;
    try {
      switch (sampleFormat) {
        //平面型左右声道分开
        case avutil.AV_SAMPLE_FMT_FLTP:

          break;
        //非平面型左右声道在一个buffer中
        case avutil.AV_SAMPLE_FMT_S16:
          break;
        //float非平面型
        case avutil.AV_SAMPLE_FMT_FLT:
          break;
        //平面型左右声道分开
        case avutil.AV_SAMPLE_FMT_S16P:
          break;
        default:
          throw new Exception("不支持的音乐格式或音频数据不正确");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 初始化音频的输出格式
   */
  private static AudioFormat initSourceDataLine(FFmpegFrameGrabber ffmpeg) {
    AudioFormat audioFormat = null;
    try {
      switch (ffmpeg.getSampleFormat()) {
        case avutil.AV_SAMPLE_FMT_U8://无符号short 8bit
          break;
        case avutil.AV_SAMPLE_FMT_S16://有符号short 16bit
          audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, ffmpeg.getSampleRate(), 16,
              ffmpeg.getAudioChannels(), ffmpeg.getAudioChannels() * 2, ffmpeg.getSampleRate(),
              true);
          break;
        case avutil.AV_SAMPLE_FMT_S32:
          break;
        case avutil.AV_SAMPLE_FMT_FLT:
          audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, ffmpeg.getSampleRate(), 16,
              ffmpeg.getAudioChannels(), ffmpeg.getAudioChannels() * 2, ffmpeg.getSampleRate(),
              true);
          break;
        case avutil.AV_SAMPLE_FMT_DBL:
          break;
        case avutil.AV_SAMPLE_FMT_U8P:
          break;
        case avutil.AV_SAMPLE_FMT_S16P://有符号short 16bit,平面型
          audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, ffmpeg.getSampleRate(), 16,
              ffmpeg.getAudioChannels(), ffmpeg.getAudioChannels() * 2, ffmpeg.getSampleRate(),
              true);
          break;
        case avutil.AV_SAMPLE_FMT_S32P://有符号short 32bit，平面型，但是32bit的话可能电脑声卡不支持，这种音乐也少见
          audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, ffmpeg.getSampleRate(), 32,
              ffmpeg.getAudioChannels(), ffmpeg.getAudioChannels() * 2, ffmpeg.getSampleRate(),
              true);
          break;
        case avutil.AV_SAMPLE_FMT_FLTP://float 平面型 需转为16bit short
          audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, ffmpeg.getSampleRate(), 16,
              ffmpeg.getAudioChannels(), ffmpeg.getAudioChannels() * 2, ffmpeg.getSampleRate(),
              true);
          break;
        case avutil.AV_SAMPLE_FMT_DBLP:
          break;
        case avutil.AV_SAMPLE_FMT_S64://有符号short 64bit 非平面型
          break;
        case avutil.AV_SAMPLE_FMT_S64P://有符号short 64bit平面型
          break;
        default:
          throw new Exception("不支持的音乐格式");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return audioFormat;
  }

  /**
   * 有符号short流转byte流
   */
  public static ByteBuffer shortToByteValue(ShortBuffer arr, float vol) {
    int len = arr.capacity();
    ByteBuffer bb = ByteBuffer.allocate(len * 2);
    for (int i = 0; i < len; i++) {
      bb.putShort(i * 2, (short) ((float) arr.get(i) * vol));
    }
    return bb; // 默认转为大端序
  }

  /**
   * float流转byte流
   */
  public static ByteBuffer floatToByteValue(FloatBuffer arr, float vol) {
    int len = arr.capacity();
    float f;
    float v;
    ByteBuffer res = ByteBuffer.allocate(len * 2);
    v = 32768.0f * vol;
    for (int i = 0; i < len; i++) {
      f = arr.get(i)
          * v;//Ref：https://stackoverflow.com/questions/15087668/how-to-convert-pcm-samples-in-byte-array-as-floating-point-numbers-in-the-range
      if (f > v) {
        f = v;
      }
      if (f < -v) {
        f = v;
      }
      //默认转为大端序
      res.putShort(i * 2, (short) f);//注意乘以2，因为一次写入两个字节。
    }
    return res;
  }

}
