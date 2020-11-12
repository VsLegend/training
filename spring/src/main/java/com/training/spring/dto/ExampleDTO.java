package com.training.spring.dto;

import com.training.spring.aop.annotation.valid.FieldNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 17:02
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExampleDTO implements Serializable {

  @FieldNotEmpty(message = "姓名不能为空")
  private String name;

  private Date birthday;

  private int age;

  private boolean male;

  private double remainMoney;

  private List<String> list;

}
