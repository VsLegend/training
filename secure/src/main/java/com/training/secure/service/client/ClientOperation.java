package com.training.secure.service.client;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSONObject;
import com.training.common.utils.AESUtils;
import com.training.common.utils.Base64Utils;
import com.training.common.utils.Md5Utils;
import com.training.common.utils.RSAUtils;
import com.training.secure.po.TransferDataPO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @User: wong
 * @Date: 2020/11/2
 * @Description: 客户端代码，模拟请求方的数据处理
 */

@Component
public class ClientOperation {

  @Value("${test.license}")
  private String license;

  /**
   * 请求API的接口参数
   * @return
   */
  public TransferDataPO generateRequestData() {
    long timestamp = System.currentTimeMillis();
    String nonce = this.generateNonce(timestamp);
    TransferDataPO transferDataPO = TransferDataPO.builder()
            .timestamp(timestamp)
            .nonce(nonce)
            .build();
    String sign = this.generateSign(timestamp, nonce);
    transferDataPO.setSign(sign);
    return transferDataPO;
  }

  /**
   * 客户端接收数据并解密
   * @param str
   */
  public void decryptData(String str) {
    try {
      String content = Base64Utils.base642Str(str);
      JSONObject json = JSONObject.parseObject(content);
      String key = (String) json.get("key");
      String value = (String) json.get("value");
      System.out.println("============key==========");
      System.out.println(key);
      System.out.println("============value==========");
      System.out.println(value);
      String seed = RSAUtils.decrypt(key);
      String originData = AESUtils.decrypt(value, seed);
      System.out.println("============originData==========");
      System.out.println(originData);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  // 生成nonce
  public String generateNonce(long timestamp) {
    return Md5Utils.encrypt(String.valueOf(new Random(timestamp)));
  }

  // sign生成
  public String generateSign(long timestamp, String nonce) {
    String data = "license=" + license + "&timestamp=" + timestamp + "&nonce=" + nonce;
    String encrypt = Md5Utils.encrypt(data);
    return Base64Utils.str2Base64(encrypt);
  }
}
