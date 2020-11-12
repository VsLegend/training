package com.training.spring.aop;

import com.training.spring.aop.annotation.valid.FieldNotEmpty;
import com.training.spring.aop.annotation.valid.ValidMark;
import com.training.spring.exception.FieldEmptyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

@Aspect
@Component
public class FieldCheckAspect {

  @Pointcut("@annotation(com.training.spring.aop.annotation.valid.FieldValid)")
  public void check() {
  }

  @Before("check()")
  public void beforeCheck(JoinPoint joinpoint) {
    // 获取参数注解
    MethodSignature signature = (MethodSignature) joinpoint.getSignature();
    Method method = signature.getMethod();
    Annotation[][] annotations = method.getParameterAnnotations();
    // 获取入参
    Object[] args = joinpoint.getArgs();
    for (int i = 0; i < args.length; i++) {
      Object object = args[i];
      Class<?> aClass = object.getClass();
      Annotation[] paraAnnotation = annotations[i];
      Annotation annotation = checkAnnotationExist(paraAnnotation);
      if (null != annotation) {
        // 验证当前对象
        this.validObject(object, aClass, annotation);
      } else {
        // 验证对象的属性
        this.validObjectField(object, aClass);
      }
    }
  }

  // 判断是否标记注解
  public Annotation checkAnnotationExist(Annotation[] annotations) {
    for (Annotation annotation : annotations) {
      if (annotation.annotationType().isAnnotationPresent(ValidMark.class))
        return annotation;
    }
    return null;
  }

  // 校验对象
  public void validObject(Object object, Class<?> aClass, Annotation annotation) {
    // 校验是否为空
    if (FieldNotEmpty.class == annotation.annotationType()) {
      if (this.checkNullOrBlank(object, aClass)) {
        throw new FieldEmptyException(((FieldNotEmpty) annotation).message());
      }
    }
    // 校验其他…………
  }

  // 校验属性
  public void validField(Field field) {
    field.setAccessible(true);
    // 校验是否为空
    if (field.isAnnotationPresent(FieldNotEmpty.class)) {
      if (this.checkNullOrBlank(field, field.getClass())) {
        FieldNotEmpty fieldNotEmpty = field.getAnnotation(FieldNotEmpty.class);
        String message = fieldNotEmpty.message();
        throw new FieldEmptyException(message);
      }
    }
    // 校验其他…………
  }


  // 校验对象的属性
  public void validObjectField(Object object, Class<?> aClass) {
    Field[] fields = aClass.getDeclaredFields();
    if (fields.length < 1)
      return;
    for (Field field : fields) {
      // 校验属性
      this.validField(field);
    }
  }

  // 验证参数是否为空
  public boolean checkNullOrBlank(Object object, Class<?> aClass) {
    if (null == object)
      return true;
    /**
     * Boolean Character Byte Short Integer Long Float Double Void
     */
    if (aClass.isPrimitive())
      return false;
    if (object instanceof String)
      return StringUtils.isEmpty(object);
    if (object instanceof Collection)
      return CollectionUtils.isEmpty((Collection<?>) object);
    return false;
  }

}
