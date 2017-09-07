package com.study.spring.exception;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理响应
 *
 * @author Jeffrey
 * @since 2017/05/05 16:55
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String logMessage = "--- system error! --- Host: {}, Url: {}, ERROR: {}";

    /**
     * 未定义系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String sysErrorHandler(HttpServletRequest req, Exception e) {
        printErrorLog(req, e);
        return e.getMessage();
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String argumentErrorHandler(HttpServletRequest req, ConstraintViolationException e) {
        printErrorLog(req, e);
        return e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(","));
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String argumenErrorHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        printErrorLog(req, e);
        return e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(","));
    }

    /**
     * 打印错误日志(会打印出堆栈信息)
     */
    private void printErrorLog(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        logger.error(logMessage, req.getRemoteHost(),
            req.getRequestURL(), e);
    }

    /**
     * 打印错误日志(仅打印错误信息)
     */
    private void printErrorLog(HttpServletRequest req, String message) {
        logger.error(logMessage, req.getRemoteHost(),
            req.getRequestURL(), message);
    }

    /**
     * 打印警告日志(仅打印错误信息)
     */
    private void printWarnLog(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        logger.warn(logMessage, req.getRemoteHost(),
            req.getRequestURL(), e.getMessage());
    }

    /**
     * 打印警告日志(仅打印错误信息)
     */
    private void printWarnLog(HttpServletRequest req, String message) {
        logger.warn(logMessage, req.getRemoteHost(),
            req.getRequestURL(), message);
    }
}
