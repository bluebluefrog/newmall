package com.wjb.newmall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ERROR(-1,"服务端错误"),

    SUCCESS(0,"成功"),

    PASSWORD_ERROR(1,"密码错误"),

    USERNAME_EXIST(2,"用户名已存在"),

    EMAIL_EXIST(4,"邮箱已存在"),

    PARAM_ERROR(3,"参数错误"),

    USERNAME_OR_PASSWORD_ERROR(3,"参数错误"),

    NEED_LOGIN(10,"用户未登录"),
                    ;
    Integer code;

    String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
