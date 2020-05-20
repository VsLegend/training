package com.training.mediatransfer.service.impl;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSONPObject;
import com.example.transfer.service.RequestHttpService;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
