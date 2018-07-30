package com.simons.cn.util;

public enum  CommonEnum {
    SUCESS("0","success"),
    FAIL("1","system error"),
    SERVICE_NOT_AVAILABLE("5001","当前服务不可用，请稍后重试!"),
    ILLEALREQUEST("1001","非法请求!");

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CommonEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
