package com.training.mediatransfer.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @author Wangjunwei
 * @Date 2019/10/28 15:14
 * @Description 腾讯接口请求参数
 */

@Data
public class TencentVoiceInfo implements Serializable {

  /**
   * 是否必填  类型   描述
   */

  /**
   * 是	String	用户在腾讯云注册账号 AppId 对应的 SecretId，可以进入 API 密钥管理页面 获取
   */
  private String secretid;

  /**
   * 是	String	引擎类型：
   * 8k_0：电话 8k 通用模型；
   * 16k_0：16k 通用模型；
   * 8k_6：电话场景下单声道话者分离模型。
   */
  private String engine_model_type;

  /**
   * 是	String	回调 URL，用户自行搭建的用于接收识别结果的服务器地址， 长度小于2048字节
   */
  private String callback_url;

  /**
   * 是	Int	语音数据来源。0：语音 URL；1：语音数据（post body）
   */
  private Integer source_type;

  /**
   * 否	String	语音的 URL 地址，需要公网可下载。长度小于2048字节，当 source_type 值为 0 时须填写该字段，为 1 时不需要填写
   */
  private String url;

  /**
   * 是	Int	当前 UNIX 时间戳，可记录发起 API 请求的时间。如果与当前时间相差过大，会引起签名过期错误。可以取值为当前请求的系统时间戳即可
   */
  private Integer timestamp;

  /**
   * 是	Int	签名的有效期，是一个符合 UNIX Epoch 时间戳规范的数值，单位为秒；Expired 必须大于 Timestamp 且 Expired - Timestamp 小于90天。
   */
  private Integer expired;

  /**
   * 是	Int	随机正整数。用户需自行生成，最长10位
   */
  private String nonce;

}
