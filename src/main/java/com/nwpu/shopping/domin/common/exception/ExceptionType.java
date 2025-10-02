package com.nwpu.shopping.domin.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义业务异常的异常种类
 *
 * @author 廖菁璞
 */
@Getter
@AllArgsConstructor
public enum ExceptionType {
    /**
     * 参数校验失败
     */
    PARAM_VALIDATE_FAILED(10000, "参数校验失败"),

    /**
     * 系统内部错误
     */
    SYSTEM_ERROR(10001, "未知异常，请稍后再试"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR(10002, "密码错误"),

    /**
     * 用户名不存在
     */
    USER_NOT_FOUND(10003, "用户名不存在"),

    /**
     * 用户名不存在
     */
    USERNAME_EXISTED(10004, "用户名已存在");

    /**
     * 异常状态码
     */
    private final int code;

    /**
     * 异常信息
     */
    private final String message;

}
