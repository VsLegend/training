package com.training.spring.controller;

import com.training.spring.aop.annotation.valid.FieldNotEmpty;
import com.training.spring.aop.annotation.valid.FieldValid;
import com.training.spring.dto.ExampleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @User: wong
 * @Date: 2020/11/11
 * @Description:
 */

@RestController
@RequestMapping("/check")
public class FieldCheckController {

  @FieldValid
  @PostMapping("/object")
  public String  object(@RequestBody ExampleDTO exampleDTO) {
    System.out.println(exampleDTO.toString());
    return exampleDTO.getName();
  }

  @FieldValid
  @PostMapping("/field")
  public String field(@RequestBody @FieldNotEmpty List<String> list) {
    System.out.println(list.toString());
    return null;
  }

}
