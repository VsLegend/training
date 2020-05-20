package com.training.mediatransfer.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wangjunwei
 * @Date 2019/10/17 13:52
 * @Description
 */


public interface RequestHttpService {

  public String requestTecentAPI(MultipartFile file);

}
