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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ricky
 * @version 2.0 新增了打印异常详细信息功能
 * @date 2024/7/27
 * @className CommonExceptionHandler
 * @desc 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DbException.class)
    public Object handleDbException(HttpServletRequest request, DbException e) {
        log.error("mysql数据库操作异常 -> ", e);
        logDetail(request, e);
        return processResponse(e);
    }

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public Object handleBadRequestException(HttpServletRequest request, BaseException e) {
        log.error("自定义异常 -> {} , 异常原因：{}  ", e.getClass().getName(), e.getMessage());
        logDetail(request, e);
        return processResponse(e);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("|"));
        log.error("请求参数校验异常 -> {}", msg);
        logDetail(request, e);
        return processResponse(new BadRequestException(msg));
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public Object handleBindException(HttpServletRequest request, BindException e) {
        log.error("请求参数绑定异常 ->BindException， {}", e.getMessage());
        logDetail(request, e);
        return processResponse(new BadRequestException("请求参数格式错误"));
    }

    @ResponseBody
    @ExceptionHandler(NestedServletException.class)
    public Object handleNestedServletException(HttpServletRequest request, NestedServletException e) {
        log.error("参数异常 -> NestedServletException，{}", e.getMessage());
        logDetail(request, e);
        return processResponse(new BadRequestException("请求参数处理异常"));
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleRuntimeException(HttpServletRequest request, Exception e) {
        log.error("其他异常 uri : {} -> ", request.getRequestURI(), e);
        return processResponse(new BaseException("服务器内部异常", 500));
    }

    /**
     * 打印异常详细信息
     * 注意经过全局异常处理器处理过的异常会在控制台打印两条信息
     * 第一条为基本信息
     * 第二条为本方法的详细信息
     * @param request 请求对象，获取请求相关信息
     * @param exception 异常对象，获取错误堆栈信息
     */
    private void logDetail(HttpServletRequest request, Exception exception) {
        // 换行符
        String lineSeparatorStr = System.lineSeparator();

        StringBuilder exStr = new StringBuilder();
        StackTraceElement[] trace = exception.getStackTrace();
        // 获取堆栈信息并输出为打印的形式
        for (StackTraceElement s : trace) {
            exStr.append("\tat ").append(s).append("\r\n");
        }
        // 打印error级别的堆栈日志
        log.error("访问地址：" + request.getRequestURL() + ",请求方法：" + request.getMethod() +
                ",远程地址：" + request.getRemoteAddr() + lineSeparatorStr +
                "错误堆栈信息如下：" + exception + lineSeparatorStr + exStr);
    }

    /**
     * 统一异常响应
     * @param e 异常对象
     * @return 返回统一异常响应
     */
    private ResponseEntity<Result<Object>> processResponse(BaseException e) {
        return ResponseEntity.status(e.getCode()).body(Result.error(e));
    }

}
