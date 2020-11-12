package com.training.spring.aop.annotation.valid;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @User: wong
 * @Date: 2020/11/11
 * @Description: 验证字段是否为空，为空报错
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, PARAMETER })
@ValidMark
public @interface CNotEmpty {

  /**
   * 报错消息
   * @return
   */
  public String message() default "字段不能为null或空";

}
