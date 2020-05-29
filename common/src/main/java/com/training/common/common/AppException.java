package com.training.common.common;

import com.training.common.enums.ErrorCode;
import org.springframework.util.StringUtils;

public class AppException extends RuntimeException {

  private static final long serialVersionUID = 8449738842423044010L;

  private ErrorCode code;

  public AppException(ErrorCode code) {
    super(code.getMsg());
    this.code = code;
  }

  public AppException(ErrorCode code, String message) {
    super(message);
    this.code = code;
  }

  public ErrorCode getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    if (StringUtils.isEmpty(super.getMessage())) {
      return code.toString();
    }
    return super.getMessage();
  }
}
