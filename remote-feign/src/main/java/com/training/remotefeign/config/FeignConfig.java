package com.training.remotefeign.config;

import feign.Contract;
import feign.Logger;
import feign.Request.Options;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author Wangjunwei
 * @Date 2020/4/17 16:36
 * @Description feign类配置
 * 配置feign 有两种情况
 * 1. 通过@Bean来加载所有的feign配置
 * 2. 通过Feign.builder()
 *
 * 优劣
 * 1. 加上@Configuration成为配置类，即可为所有@FeignClient所修饰的类加上配置。即全局配置。
 */

//@Configuration
public class FeignConfig implements RequestInterceptor {

  @Autowired
  private ObjectFactory<HttpMessageConverters> messageConverters;

  // feign 契约 RequestLine
  @Bean
  public Contract feignContract() {
    return new Contract.Default();
//    return new SpringMvcContract();
  }

  // 记录请求和响应的头文件，正文和元数据。
  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  // 连接超时时间 s 读取超时时间 s 是否重定向
  @Bean
  public Options options() {
    return new Options(10 * 1000, TimeUnit.MINUTES, 60 * 1000, TimeUnit.SECONDS, true);
  }

  // 编码方式
  @Bean
  public Encoder feignFormEncoder() {
    return new SpringFormEncoder(new SpringEncoder(messageConverters));
  }

  // 解析方式
  @Bean
  public Decoder feignDecoder() {
    return new SpringDecoder(messageConverters);
  }

  // feign调用拦截器
  @Override
  public void apply(RequestTemplate template) {
  }

}
