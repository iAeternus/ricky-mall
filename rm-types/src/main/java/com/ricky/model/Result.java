package com.ricky.model;

import com.ricky.exception.BaseException;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/5/27
 * @className Result
 * @desc 统一响应结果
 */
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static Result<Void> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "OK", data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> error(BaseException e) {
        return new Result<>(e.getCode(), e.getMessage(), null);
    }

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
