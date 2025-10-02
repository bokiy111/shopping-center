package com.nwpu.shopping.domin.common.exception;


import lombok.Getter;

/**
 * 业务异常
 * 会返回HTTP 200,但是会在response的body部分标注请求失败及失败原因
 *
 * @author 廖菁璞
 */
@Getter
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(ExceptionType type) {
        super(type.getMessage());
        this.code = type.getCode();
    }

    public BusinessException(ExceptionType type, String message) {
        super(message);
        this.code = type.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }


}
