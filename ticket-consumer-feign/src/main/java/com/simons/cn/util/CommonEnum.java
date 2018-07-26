package com.simons.cn.util;

/**
 * 项目名称：user-provider
 * 类名称：com.simons.cn.util
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/19 11:10
 */
public enum  CommonEnum {
    SUCESS("0","success"),
    FAIL("1","system error");

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
