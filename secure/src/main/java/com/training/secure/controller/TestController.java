package com.training.secure.controller;

import com.training.common.common.Result;
import com.training.secure.po.TransferDataPO;
import com.training.secure.service.client.ClientOperation;
import com.training.secure.service.server.ServerOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 14:25
 * @Description
 */

@Api(tags = "加解密接口测试")
@RestController
@RequestMapping("/secure")
@Slf4j
public class TestController {

  @Resource
  private ClientOperation clientOperation;

  @Resource
  private ServerOperation serverOperation;

  @GetMapping("/api")
  @ApiOperation(value = "API加密")
  public Result generateRequestData() {
    // 1 客户端请求
    TransferDataPO transferDataPO = clientOperation.generateRequestData();
    // 2 服务端接收并返回数据
    String data = serverOperation.acceptData(transferDataPO);
    // 3 客户端接收并解析
    clientOperation.decryptData(data);
    return Result.success();
  }

}
