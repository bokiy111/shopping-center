package com.nwpu.shopping.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: feoyang
 * @date: 2024/1/11 0:19
 * @description: TODO
 */
@Data
@NoArgsConstructor
@RestControllerAdvice(basePackages = "com.nwpu.shopping.api.controller")
@Slf4j
public class CommonResult {
    private static final Integer SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 是否成功（必填）
    **/
    private Boolean success;
    /**
     * 业务状态码（必填）
     */
    private Integer code;
    /**
     * 对业务状态码的解释（必填）
     */
    private String message;
    /**
     * 返回数据（选填）
     */
    private Object data;

    public CommonResult(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult success() {
        return new CommonResult(true, SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static CommonResult success(Object data) {
        return new CommonResult(true, SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static CommonResult failure(String message) {
        return new CommonResult(false, -1, message, null);
    }

    public static CommonResult failure(Integer code, String message) {
        return new CommonResult(false, code, message, null);
    }
}
