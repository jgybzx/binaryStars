package com.jgybzx.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jgybzx
 * @date 2021/3/3 17:56
 * @description 统一异常处理，捕捉异常，将异常信息封装 ResultMessage
 */
//@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理系统异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMessage exceptionHandler(Exception e) {

        return ResultMessage.error(e.getMessage());
    }
}
