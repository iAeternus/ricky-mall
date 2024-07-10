package com.ricky.handler;


import com.ricky.exception.BadRequestException;
import com.ricky.exception.BaseException;
import com.ricky.exception.DbException;
import com.ricky.model.Result;
import com.ricky.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import java.net.BindException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/5/27
 * @className CommonExceptionHandler
 * @desc 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(DbException.class)
    public Object handleDbException(DbException e) {
        log.error("mysql数据库操作异常 -> ", e);
        return processResponse(e);
    }

    @ExceptionHandler(BaseException.class)
    public Object handleBadRequestException(BaseException e) {
        log.error("自定义异常 -> {} , 异常原因：{}  ", e.getClass().getName(), e.getMessage());
        log.debug("", e);
        return processResponse(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("|"));
        log.error("请求参数校验异常 -> {}", msg);
        log.debug("", e);
        return processResponse(new BadRequestException(msg));
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        log.error("请求参数绑定异常 ->BindException， {}", e.getMessage());
        log.debug("", e);
        return processResponse(new BadRequestException("请求参数格式错误"));
    }

    @ExceptionHandler(NestedServletException.class)
    public Object handleNestedServletException(NestedServletException e) {
        log.error("参数异常 -> NestedServletException，{}", e.getMessage());
        log.debug("", e);
        return processResponse(new BadRequestException("请求参数处理异常"));
    }

    @ExceptionHandler(Exception.class)
    public Object handleRuntimeException(Exception e) {
        log.error("其他异常 uri : {} -> ", Objects.requireNonNull(WebUtils.getRequest()).getRequestURI(), e);
        return processResponse(new BaseException("服务器内部异常", 500));
    }

    private ResponseEntity<Result<Void>> processResponse(BaseException e) {
        return ResponseEntity.status(e.getCode()).body(Result.error(e));
    }

}
