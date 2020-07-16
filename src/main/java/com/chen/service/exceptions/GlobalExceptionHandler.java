package com.chen.service.exceptions;

import com.chen.utils.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author chen
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public CommonResult<String> handleCommonException(CommonException e) {
        return CommonResult.failed(e.getMessage());
    }
}
