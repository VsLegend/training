package com.training.secure.handler;


import com.training.common.common.Result;
import com.training.common.enums.ErrorCode;
import com.training.common.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.stream.Collectors;

/**
 * 异常捕获类
 */

@ControllerAdvice
@ResponseBody
@Slf4j
public class DefaultExceptionHandler {

  @ExceptionHandler(Exception.class)
  public Result<String> processException(NativeWebRequest request, Exception e) {
    log.error("exception", e);
    return Result.failed(e);
  }

  @ExceptionHandler(RuntimeException.class)
  public Result<String> processValidationException(RuntimeException e) {
    log.error("runtime exception {} ", e);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, e.getMessage());
  }


  @ExceptionHandler(BindException.class)
  public Result<String> handleBindException(BindException e) {
    String message = e.getAllErrors().stream().map(ObjectError::getDefaultMessage)
        .collect(Collectors.joining(", "));
    log.error("bind error {}", message, e);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, message);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Result<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
    BindingResult br = e.getBindingResult();
    String message = br.getAllErrors().stream().map(ObjectError::getDefaultMessage)
        .collect(Collectors.joining(", "));
    log.error("validation error {}", message, e);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, message);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    log.error(" error {}", ex);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, "参数解析失败");
  }

  @ExceptionHandler({AppException.class})
  public Result processAPPException(Exception e) {
    log.error("AppException error : ", e);
    ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
    String message = "服务器内部错误";
    if (e instanceof AppException) {
      AppException appException = (AppException) e;
      errorCode = appException.getCode();
      message = appException.getMessage();
    }
    return new Result(errorCode, message);
  }
}
