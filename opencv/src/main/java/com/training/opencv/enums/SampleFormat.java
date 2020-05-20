package com.training.opencv.enums;

/**
 * @author Wangjunwei
 * @Date 2019/12/16 17:52
 * @Description
 */
public enum  SampleFormat {

  /**
   * 非平面型
   */
  AV_SAMPLE_FMT_NONE,
  AV_SAMPLE_FMT_U8,          // unsigned 8 bits 无符号8位
  AV_SAMPLE_FMT_S16,         // signed 16 bits
  AV_SAMPLE_FMT_S32,         // signed 32 bits
  AV_SAMPLE_FMT_FLT,         // float
  AV_SAMPLE_FMT_DBL,         // double

  /**
   * 平面型
   */
  AV_SAMPLE_FMT_U8P,         // unsigned 8 bits, planar
  AV_SAMPLE_FMT_S16P,        // signed 16 bits, planar
  AV_SAMPLE_FMT_S32P,        // signed 32 bits, planar
  AV_SAMPLE_FMT_FLTP,        // float, planar
  AV_SAMPLE_FMT_DBLP,        // double, planar
  AV_SAMPLE_FMT_S64,         // signed 64 bits
  AV_SAMPLE_FMT_S64P,        // signed 64 bits, planar

  AV_SAMPLE_FMT_NB           // Number of sample formats. DO NOT USE if linking dynamically

}
