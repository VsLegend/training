package com.training.spring.aop.annotation.valid;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * @User: wong
 * @Date: 2020/11/11
 * @Description: 标识要进行验证的方法
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface CValid {
}
