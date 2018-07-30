package com.simons.cn.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommonResult<T> implements Serializable{
    private static final long serialVersionUID = -7443023570133562493L;
    private String code;
    private String message;
    private T data;

    public CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommonResult success(String code, String message, Object data) {
        return new CommonResult(code, message, data);
    }

    public static CommonResult success(String code, String message) {
        return new CommonResult(code, message);
    }

    public CommonResult() {

    }

    public static CommonResult fail(String code, String message) {
        return new CommonResult(code, message);
    }
}
