package com.training.mediatransfer.service.impl;

import com.training.mediatransfer.service.RequestHttpService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wangjunwei
 * @Date 2019/10/17 13:53
 * @Description
 */

@Service
public class RequestHttpServiceImpl implements RequestHttpService {


  @Override
  public String requestTecentAPI(MultipartFile file) {
//    String url = "https://aai.qcloud.com/asr/v1/";
//    String appID = "1257124885";
    String url = "https://www.apiopen.top/femaleNameApi";
    String symbol = "?";
    String param = "page=1";

    return null;
  }
}
