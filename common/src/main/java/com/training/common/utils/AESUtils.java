package com.training.common.utils;

import com.training.common.enums.ErrorCode;
import com.training.common.exception.AppException;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @User: wong
 * @Date: 2020/10/21
 * @Description: AES 加解密
 */

public class AESUtils {

  private static final String KEY_ALGORITHM = "AES";
  // 默认的加密算法
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


  /**
   * AES 加密操作
   *
   * @param content 待加密内容
   * @param seed    加密种子
   * @return 返回Base64转码后的加密数据
   */
  public static String encrypt(String content, String seed) {
    try {
      // 创建密码器
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
      // 初始化为加密模式的密码器
      cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(seed));
      // 加密
      byte[] result = cipher.doFinal(byteContent);
      // 通过Base64转码返回
      return Base64Utils.encodeToString(result);
    } catch (Exception e) {
      e.printStackTrace();
      throw new AppException(ErrorCode.UNEXCEPTED, "AES加密失败");
    }
  }

  /**
   * AES 解密操作
   */
  public static String decrypt(String content, String seed) {
    try {
      // 实例化
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      // 使用密钥初始化，设置为解密模式
      cipher.init(Cipher.DECRYPT_MODE, getSecretKey(seed));
      // 执行操作
      byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
      return new String(result, StandardCharsets.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
      throw new AppException(ErrorCode.UNEXCEPTED, "AES解密失败");
    }
  }

  /**
   * 生成加密秘钥
   */
  private static SecretKeySpec getSecretKey() {
    String seed = System.getenv("AES_SYS_KEY");
    if (seed == null) {
      seed = System.getProperty("AES_SYS_KEY");
    }
    if (seed == null || seed.trim().length() == 0) {
      // 默认密钥
      seed = "NSsu928&w012~3NSisd-csfps";
    }
    return getSecretKey(seed);
  }

  public static SecretKeySpec getSecretKey(String seed) {
    //返回生成指定算法密钥生成器的 KeyGenerator 对象
    KeyGenerator kg = null;
    try {
      kg = KeyGenerator.getInstance(KEY_ALGORITHM);
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      // 设置随机密码的种子值
      random.setSeed(seed.getBytes(StandardCharsets.UTF_8));
      // AES 要求密钥长度为 128/192/256
      kg.init(128, random);
      // 生成一个密钥
      SecretKey secretKey = kg.generateKey();
      // 转换为AES专用密钥
      return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    } catch (NoSuchAlgorithmException ex) {
      throw new AppException(ErrorCode.UNEXCEPTED, "AES生成密钥失败");
    }
  }

  public static void main(String[] args) throws Exception {
    String seed = "129dhsiah82dksahkfa";
    SecretKeySpec secretKey = getSecretKey(seed);


//    String content  = "12749218312321321";
//    String password = RSAUtil.encrypt("ODJjODUzY2Q0ODA3YjJlOTE1YTQ2NGFhOTY5YjNiMmE=");
//    String encrypt = encrypt(content, RSAUtil.decrypt(password));
//    String decrypt = decrypt(encrypt, RSAUtil.decrypt(password));
//    System.out.println(content);
//    System.out.println(encrypt);
//    System.out.println(decrypt);
  }

}
