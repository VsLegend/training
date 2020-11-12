package com.training.spring.exception;

/**
 * @User: wong
 * @Date: 2020/11/11
 * @Description:
 */
public class CValidException extends RuntimeException {

  public CValidException(String message) {
    super("com.training.spring.exception.CValidException: field valid failed: "
            + message);
  }

  public CValidException() {
    super();
  }

}
