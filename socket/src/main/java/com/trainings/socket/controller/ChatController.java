package com.trainings.socket.controller;

import com.training.common.common.Result;
import com.trainings.socket.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 18:07
 * @Description
 */

@RestController
@Api(tags = "服务端数据修改")
@RequestMapping("/server")
public class ChatController {

  @Resource
  private ChatService chatService;

  @GetMapping("/sent/{message}/{id}")
  @ApiOperation("接收其他消息")
  private Result sentMessage(@RequestParam("message") String message, @RequestParam("id") String id) {
    chatService.sendMessage(message, id);
    return Result.success();
  }

}
