package com.training.common.enums;

public enum ErrorCode {


  UNEXCEPTED(9999, "系统异常"),

  /**
   * 系统内部错误
   */
  INTERNAL_ERROR(500, "系统内部错误"),

  /**
   * 成功
   */
  SUCCESS(1000, "成功"),
  /**
   * 非法参数
   */
  ILLEGAL_PARAMETER(1001, "非法参数"),
  /**
   * 权限不足
   */
  ACCESS_DENIED(1002, "权限不足"),
  /**
   * 结果为空
   */
  RESULT_EMPTY(1003, "结果为空"),

  /**
   * 参数请求验证失败
   */
  PARAMETER_CHECK_FAILED(1004, "参数请求验证失败"),

  /**
   * 数据返回加密失败
   */
  DATA_ENCRYPT_FAILED(1005, "数据返回加密失败"),

  /**
   * socket连接关闭
   */
  SOCKET_HAVE_BEEN_CLOSED(2001, "Socket连接异常，客户端已关闭连接"),

  ;


  private int value;

  private String msg;

  private ErrorCode(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }

  public int getValue() {
    return value;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
