package com.nwpu.shopping.domin.common.exception;

/**
 * 资源不存在异常
 * 会返回HTTP 404
 *
 * @author 廖菁璞
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
