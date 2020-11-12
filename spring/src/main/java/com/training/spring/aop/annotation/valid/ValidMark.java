package com.training.spring.aop.annotation.valid;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @User: wong
 * @Date: 2020/11/12
 * @Description: 注解标记类 标明注解是校验注解的一种
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
public @interface ValidMark {
}
