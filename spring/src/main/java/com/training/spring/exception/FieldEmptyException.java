package com.training.spring.exception;

/**
 * @User: wong
 * @Date: 2020/11/11
 * @Description:
 */
public class FieldEmptyException extends RuntimeException {

  public FieldEmptyException(String message) {
    super("com.training.spring.exception.FieldEmptyException: field valid failed: "
            + message);
  }

  public FieldEmptyException() {
    super();
  }

}
