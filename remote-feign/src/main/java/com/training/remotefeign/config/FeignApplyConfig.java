package com.training.remotefeign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author Wangjunwei
 * @Date 2020/4/17 16:36
 * @Description feign 全局拦截请求体 类配置
 * 作用：当feign转发请求时，都需要携带某个请求参数，那么可以通过改拦截器直接全局统一设置请求内容
 */

@Configuration
public class FeignApplyConfig implements RequestInterceptor {

  // feign调用拦截器 可以设置请求的所有参数
  @Override
  public void apply(RequestTemplate template) {
    // 这里相当于 header信息转发
//    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//            .getRequestAttributes();
//    HttpServletRequest request = attributes.getRequest();
//    if (request != null) {
//      System.err.println("feign传递参数");
////        只携带token
////            String authorization = request.getHeader("Authorization");
////            requestTemplate.header("Authorization", authorization);
//      // 将请求feign接口的所有header信息一并传给携带，并传给feign调用的远程接口
//      Enumeration<String> headerNames = request.getHeaderNames();
//      if (headerNames != null) {
//        while (headerNames.hasMoreElements()) {
//          String name = headerNames.nextElement();
//          String values = request.getHeader(name);
//          template.header(name, values);
//        }
//      }
//    }
    // 从headers()的构架可以得知，feign发送的请求头键值中，允许一个键保留多个值
    Map<String, Collection<String>> queries = template.queries();
    Map<String, Collection<String>> headers = template.headers();
    if (StringUtils.isEmpty(headers.get("Authorization"))) {
      template.header("Authorization", "user_token");
    }
  }

}
