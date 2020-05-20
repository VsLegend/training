package com.training.mediatransfer.service;

import com.training.mediatransfer.domain.Result;
import com.training.mediatransfer.service.impl.APIServiceImpl;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Wangjunwei
 * @Date 2019/10/17 15:30
 * @Description feign 这里可以在远程接口请求错误时，进行降级处理。远程命名随意，此处并无作用。
 */

@FeignClient(name = "api", fallback = APIServiceImpl.class)
public interface APIService {

  @RequestLine("GET /femaleNameApi?page={page}")
  Result get(@Param("page") String page);

}
