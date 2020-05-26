package com.training.java8stream.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wangjunwei
 * @Date 2019/9/19 11:15
 * @Description
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  private String name;

  private String age;

  private String length;

}
