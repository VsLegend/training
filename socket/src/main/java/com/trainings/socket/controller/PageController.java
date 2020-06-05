package com.trainings.socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 16:49
 * @Description
 */

@Controller
@RequestMapping("/socket")
public class PageController {

  @RequestMapping("/index")
  public String index() {
    return "index";
  }

}
