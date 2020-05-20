package com.training.mediatransfer.utils;

import java.io.File;
import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

/**
 * @author Wangjunwei
 * @Date 2019/10/16 10:20
 * @Description 视频转语音
 */
public class MediaTransfer {

  /**
   * 无参数输出
   */
  public static void videoToVoice(String sourcePath, String targetPath)
      throws EncoderException {
    videoToVoice(sourcePath, targetPath, "pcm_s16le", "wav", 16000, 2, 8000);
  }

  /**
   * 在视频文件中提取音频文件
   * 腾讯语音设别最好的识别效果是 8khz 16bit pcm wav
   * 采样频率  采样位数  声道数
   * @param sourcePath 源文件地址
   * @param targetPath 输出文件地址
   * @param formatType 输出文件格式  pcm_s16le  libmp3lame
   * @param fileType 输出文件类型 wav、pcm、mp3、silk、speex、amr、m4a
   * @param bitRate 比特率 bits 16000 25600 56000 64000 65535 128000 160000
   * @param channels 声道 1单声道 2双声道
   * @param samplingRate 采样频率 kHZ 8000 11025 22050 44100 48000
   * @throws EncoderException
   */
  public static void videoToVoice(String sourcePath, String targetPath ,String formatType, String fileType
      , Integer bitRate, Integer channels, Integer samplingRate) throws EncoderException {
    File source = new File(sourcePath);
    File target = new File(targetPath);
    // 音频属性设置
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec(formatType);
    audio.setBitRate(bitRate);
    audio.setChannels(channels);
    audio.setSamplingRate(samplingRate);
    //编码属性设置
    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setFormat(fileType);
    attrs.setAudioAttributes(audio);
    Encoder encoder = new Encoder();
    encoder.encode(new MultimediaObject(source), target, attrs);
  }

//  public static void main(String[] args) throws EncoderException
//  {
//    String source = "F:\\\\Video\\\\food.flv";
//    String source1 = "F:\\\\Video\\\\NeZha.mp4";
//    String target = "F:\\\\Video\\\\output\\\\nezha.mp3";
//    System.out.println(source);
//    videoToVoice(source1, target);
//  }

}
