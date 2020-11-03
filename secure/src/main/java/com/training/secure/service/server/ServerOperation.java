package com.training.secure.service.server;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSONObject;
import com.training.common.enums.ErrorCode;
import com.training.common.exception.AppException;
import com.training.common.utils.AESUtils;
import com.training.common.utils.Base64Utils;
import com.training.common.utils.Md5Utils;
import com.training.common.utils.RSAUtils;
import com.training.secure.po.TransferDataPO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @User: wong
 * @Date: 2020/11/2
 * @Description: 服务端代码，模拟接收方的数据处理
 */

@Component
public class ServerOperation {

  @Value("${test.license}")
  private String license;

  @Resource
  private RedisTemplate<String, Set<String>> redisTemplate;

  /**
   * 服务端接收请求
   *
   * @param transferDataPO
   * @return
   */
  public String acceptData(TransferDataPO transferDataPO) {
    Long timestamp = transferDataPO.getTimestamp();
    String nonce = transferDataPO.getNonce();
    // 查询license
    transferDataPO.setLicense(license);
    String sign = transferDataPO.getSign();
    boolean checkTime = this.checkTime(timestamp);
    boolean checkNonce = this.checkNonce(nonce);
    boolean checkSign = this.checkSign(transferDataPO.toString(), sign);
    if (!(checkTime && checkNonce && checkSign)) {
      throw new AppException(ErrorCode.PARAMETER_CHECK_FAILED, ErrorCode.PARAMETER_CHECK_FAILED.getMsg());
    }
    // 模拟数据
    String content = "阿巴阿巴。阿……阿巴阿巴阿巴阿巴~";
    // 生成密钥的种子值
    String seed = timestamp + nonce;
    try {
      // 数据加密
      return encryptContent(content, seed);
    } catch (Exception e) {
      e.printStackTrace();
      throw new AppException(ErrorCode.DATA_ENCRYPT_FAILED, ErrorCode.DATA_ENCRYPT_FAILED.getMsg());
    }
  }


  // 验证时间
  public boolean checkTime(long timestamp) {
    long current = System.currentTimeMillis();
    return current <= (timestamp + 60 * 1000);
  }

  // 验证nonce
  public boolean checkNonce(String nonce) {
    String key = "nonce";
    Set<String> nonceList = redisTemplate.opsForValue().get(key);
    if (CollectionUtils.isEmpty(nonceList)) {
      nonceList = new HashSet<>();
    } else if (nonceList.contains(nonce)) {
      return false;
    }
    nonceList.add(nonce);
    redisTemplate.opsForValue().set(key, nonceList, 24, TimeUnit.HOURS);
    return true;
  }


  // 验证签名
  public boolean checkSign(String data, String sign) {
    try {
      String str = Base64Utils.base642Str(sign);
      return Md5Utils.matches(data, str);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  // 加密数据
  public static String encryptContent(String content, String seed) throws Exception {
    Map<String, String> map = new HashMap<>();
    String key = Md5Utils.encrypt(seed + System.currentTimeMillis());
    // 内容AES加密
    String contentAES = AESUtils.encrypt(content, key);
    // 种子RSA加密
    String encryptSeed = RSAUtils.encrypt(key);
    map.put("key", encryptSeed);
    map.put("value", contentAES);
    System.out.println("==============map============");
    System.out.println(map);
    // 转为base64
    return Base64Utils.str2Base64(JSONObject.toJSONString(map));
  }

}
