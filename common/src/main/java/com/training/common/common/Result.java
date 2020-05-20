package com.training.common.common;


import com.training.common.enums.ErrorCode;
import java.io.Serializable;
import lombok.Data;

@Data
public class Result<T>  implements Serializable {

  private Integer code;
  private T data;
  private String message;

  private Result() {
    this.code = ErrorCode.SUCCESS.getValue();
  }

  private Result(T data) {
    this.code = ErrorCode.SUCCESS.getValue();
    this.data = data;
  }

  public Result(ErrorCode code) {
    this.code = code.getValue();
    this.message = code.getMsg();
  }

  public Result(ErrorCode code, String message) {
    this.code = code.getValue();
    this.message = message;
  }

  public Result(ErrorCode code, String message, T data) {
    this.code = code.getValue();
    this.message = message;
    this.data = data;
  }

  public static <U> Result<U> success() {
    return new Result<U>();
  }

  /**
   * 解决范型问题，无法展示data基础类型
   */
  public static <U> Result<U> success(U data) {
    return new Result<U>(data);
  }

  public static <U> Result<U> failed(Exception e) {
    if (e instanceof AppException) {
      return fail((AppException) e);
    } else {
      e.printStackTrace();
      return new Result<U>(ErrorCode.UNEXCEPTED, null);
    }
  }

  private static <U> Result<U> fail(AppException e) {
    return new Result<U>(e.getCode(), e.getMessage());
  }

  public static <U> Result<U> failed(ErrorCode errorCode, String message) {
    return new Result<U>(errorCode, message);

  }

  public static <U> Result<U> failed(ErrorCode errorCode, String message, U data) {
    return new Result<U>(errorCode, message, data);

  }

}
