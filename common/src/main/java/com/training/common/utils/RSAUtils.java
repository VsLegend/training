package com.training.common.utils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @User: wong
 * @Date: 2020/10/12
 * @Description: RSA 公私钥加解密
 */

public class RSAUtils {

  // 公钥
  private static final String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiAm26eZIkk+k8FFpQiymQisx7dT//w7t\n"
          + "Rk9McY0hcDLxTuW4GmfF597sBHFg0WStsC1xfLls0k3EuPaaAfNydZgCmUo5tq4s1Af0j+tWKP4g\n"
          + "+/5eCUFp2nkmaIEUORVzwPoklrD5V+X1hvVvTOsSPiBDIPIrB3XK3NmHi7DeTep7Y0dWrm7RX48X\n"
          + "jSo93YFQ3lNnpukpt2s0SkJkb3hTEm4VLJdQlBuoPTuGMFEEXW8XkvmFAhIjoTykK5Pg2lEnmmc/\n"
          + "HrD+UYvXkFbixnZk/4g48J9FsHF+eJjbrwDLdz+9RPoICi1qTB4Ut8lNf7dXKYPkPR5S8fEGLY9I\n"
          + "X2JlZwIDAQAB";
  // 私钥
  private static final String PRIVATEKEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCICbbp5kiST6TwUWlCLKZCKzHt\n"
          + "1P//Du1GT0xxjSFwMvFO5bgaZ8Xn3uwEcWDRZK2wLXF8uWzSTcS49poB83J1mAKZSjm2rizUB/SP\n"
          + "61Yo/iD7/l4JQWnaeSZogRQ5FXPA+iSWsPlX5fWG9W9M6xI+IEMg8isHdcrc2YeLsN5N6ntjR1au\n"
          + "btFfjxeNKj3dgVDeU2em6Sm3azRKQmRveFMSbhUsl1CUG6g9O4YwUQRdbxeS+YUCEiOhPKQrk+Da\n"
          + "USeaZz8esP5Ri9eQVuLGdmT/iDjwn0WwcX54mNuvAMt3P71E+ggKLWpMHhS3yU1/t1cpg+Q9HlLx\n"
          + "8QYtj0hfYmVnAgMBAAECggEAWkrwYGFb69j3JhczIRwXojWfZzeITteuf4tktr2w4xK8/dwVjVkx\n"
          + "j4xz5SMH2xPTAOu4tsswQooht47ee8sFPq4Nk4cjtW/4CyfYmKngP0clx3x3F/eV9c+Rwa1jKdxm\n"
          + "P0EIhgijB0d35wMCw/AG5LtvMu/pxYLWii3L/NkBRH8VU5RtFaIH2aRX+nC8RyNFVNeWHeAN69Or\n"
          + "TQSTuq5SFqvLjnPhPhY6vaDjcfZWrEi0jPTNzEKhy79fxfalk2hg/dmBPCfHo+6wxAWrYc6GM5us\n"
          + "RsB8mJHFOJolhCSzs6QGAB1zggK9WCxmTZwns4MR2b9ZExeLCuBPfVPwMEBHqQKBgQDBM/E1VvCi\n"
          + "VPSiTx3LrNToCNVGbodWAcqcahGq6JxR5YPldaHGdPrLMrXyi6LpbckffwFs1S3YX/QiVQ1nMaqL\n"
          + "uP9L2xJ7iwMZpLDz+vKqiXhnv+SZwvZL08SkTIOMhBXDzw/QvYWcRqFZtQffPgXfiBoplSwDaaAm\n"
          + "v1MLxsDGUwKBgQC0QS6K4u+cs7+gDXNMHpvMebmxrOtykbZfpQJx7pFmPGxG+LS4yRWGEI5o8/X3\n"
          + "L5Cu69F4qyl01+tshRPgJiukLuM4k3gqBEkYZS/F/Xl572XFZNgN523Z+C9G+Q+Ec7Q+R/74LBFW\n"
          + "AVVzLpL0F/98NFLrTmxYYZ/kH4w5pwqaHQKBgQCWWo/qjMAz9J10+tEbQmNmOAmSCE42Nqqw0zLw\n"
          + "qJmOemkM12CQi1TpcYt7OAQOWSvd4P8A7V4CN5pMZZhKqLIqjkvPgqt9jY9aE3rKRI84/EbBpuqG\n"
          + "Zt054ZH0sr7lSZ2OhsOuFO+30Jecsm9v4lq7vLVvivF3rmm9/cLpNUGslQKBgCnGtZfA0tHZov7g\n"
          + "PymUKn2wdIYmUodCGCeAodlv9kjgliO4voHZMODac3cFm6sHXhv8UZcK7FfFNy32hc28av7pUwYo\n"
          + "v3/VcG9z/CeCAKZvAoJGRgCq833CFIr5bExBWLPakdBIvlfVhU1lrO9fpHnP0qvwiBHPxnPjj+dI\n"
          + "vJ9NAoGBAKnhK6zG1JNK9vazE+vRgA3sjNwdtYKoDVQFLb9UlSOVmy5DlRQ0Z0UbK4M6ME0PaOKR\n"
          + "ah0tRKR8i0+CGvc1jZ8QV8TylwOrdWpBX//CKpWHf1uNqvSsONweyTnBHIdu6suW0dhsC/Ogvhun\n"
          + "uxRtXwoqZsr7qNt8gXI52hlse3eW";

  // 生成秘钥对
  public static KeyPair getKeyPair() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    return keyPair;
  }

  // 获取公钥(Base64编码)
  public static String getPublicKey(KeyPair keyPair) {
    PublicKey publicKey = keyPair.getPublic();
    byte[] bytes = publicKey.getEncoded();
    return Base64Utils.byte2Base64(bytes);
  }

  // 获取私钥(Base64编码)
  public static String getPrivateKey(KeyPair keyPair) {
    PrivateKey privateKey = keyPair.getPrivate();
    byte[] bytes = privateKey.getEncoded();
    return Base64Utils.byte2Base64(bytes);
  }

  // 将Base64编码后的公钥转换成PublicKey对象
  public static PublicKey string2PublicKey(String pubStr) throws Exception {
    byte[] keyBytes = Base64Utils.base642Byte(pubStr);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey publicKey = keyFactory.generatePublic(keySpec);
    return publicKey;
  }

  // 将Base64编码后的私钥转换成PrivateKey对象
  public static PrivateKey string2PrivateKey(String priStr) throws Exception {
    byte[] keyBytes = Base64Utils.base642Byte(priStr);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
    return privateKey;
  }


  /**
   * 公钥加密数据
   *
   * @param content 原始数据
   * @return 加密数据
   */
  public static String encrypt(String content) throws Exception {
    byte[] encrypt = encrypt(content.getBytes(StandardCharsets.UTF_8));
    return Base64Utils.byte2Base64(encrypt);
  }

  // 公钥加密
  public static byte[] encrypt(byte[] content) throws Exception {
    PublicKey publicKey = RSAUtils.string2PublicKey(PUBLICKEY);
    return publicEncrypt(content, publicKey);
  }

  // 公钥加密
  public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] bytes = cipher.doFinal(content);
    return bytes;
  }


  /**
   * 私钥解密
   *
   * @param content 加密密文
   * @return 解密原文
   */
  public static String decrypt(String content) throws Exception {
    byte[] decrypt = decrypt(Base64Utils.base642Byte(content));
    return new String(decrypt, StandardCharsets.UTF_8);
  }

  // 私钥解密
  public static byte[] decrypt(byte[] content) throws Exception {
    PrivateKey privateKey = RSAUtils.string2PrivateKey(PRIVATEKEY);
    return privateDecrypt(content, privateKey);
  }

  // 私钥解密
  public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] bytes = cipher.doFinal(content);
    return bytes;
  }

  public static void main(String[] args) throws Exception {
//        // 生成密钥对
    KeyPair keyPair = RSAUtils.getKeyPair();
    System.out.println("-------------------公钥-------------------");
    System.out.println(Base64Utils.byte2Base64(keyPair.getPublic().getEncoded()));
    System.out.println("-------------------私钥-------------------");
    System.out.println(Base64Utils.byte2Base64(keyPair.getPrivate().getEncoded()));
  }

}
