package com.training.common.exception;


import com.google.common.base.Strings;
import com.training.common.enums.ErrorCode;

import java.util.List;

public class Assert {

  /**
   * 判空异常，为空时抛出异常
   * @param obj
   * @param errorCode
   */
  public static void isNotNull(Object obj, ErrorCode errorCode) {
    isNotNull(obj, errorCode, null);
  }

  public static void isNotNull(Object obj, ErrorCode errorCode, String message) {
    if (obj == null) {
      throw new AppException(errorCode, message);
    }
  }

  /**
   * 非空异常，非空时抛出异常
   * @param obj
   * @param errorCode
   */
  public static void isNull(Object obj, ErrorCode errorCode) {
    isNull(obj, errorCode, null);
  }

  public static void isNull(Object obj, ErrorCode errorCode, String message) {
    if (obj != null) {
      throw new AppException(errorCode, message);
    }
  }

  /**
   * 非真异常，不为true时抛出异常
   * @param condition
   * @param errorCode
   */
  public static void isTrue(boolean condition, ErrorCode errorCode) {
    isTrue(condition, errorCode, null);
  }

  public static void isTrue(boolean condition, ErrorCode errorCode, String message) {
    if (!condition) {
      throw new AppException(errorCode, message);
    }
  }

  /**
   * 值不等时抛出异常
   * @param obj1
   * @param obj2
   * @param code
   */
  public static void isEquals(Object obj1, Object obj2, ErrorCode code) {
    isEquals(obj1, obj2, code, null);
  }

  public static void isEquals(Object obj1, Object obj2, ErrorCode code, String message) {
    if (obj1 == obj2) {
      return;
    }
    if (obj1 != null && obj1.equals(obj2)) {
      return;
    }
    throw new AppException(code, message);
  }

  /**
   * 值为空时抛出异常
   * @param str
   * @param code
   */
  public static void isNotEmpty(String str, ErrorCode code) {
    isNotEmpty(str, code, null);
  }

  public static void isNotEmpty(String str, ErrorCode code, String message) {
    if (Strings.isNullOrEmpty(str)) {
      throw new AppException(code, message);
    }
  }

  /**
   * 如果非空，抛异常
   * @param list
   * @param code
   * @param message
   */
  public static void isListEmpty(List<?> list, ErrorCode code, String message) {
    if (list != null && (!list.isEmpty())) {
      throw new AppException(code, message);
    }
  }

  /**
   * 如果非空，抛异常
   * @param list
   * @param code
   */
  public static void isListEmpty(List<?> list, ErrorCode code) {
    isListEmpty(list, code, null);
  }

  /**
   * 如果空，抛异常
   * @param list
   * @param code
   * @param message
   */
  public static void isListNotEmpty(List<?> list, ErrorCode code, String message) {
    if (list == null || list.isEmpty()) {
      throw new AppException(code, message);
    }
  }

  /**
   * 如果空，抛异常
   * @param list
   * @param code
   */
  public static void isListNotEmpty(List<?> list, ErrorCode code) {
    isListNotEmpty(list, code, null);
  }
}
