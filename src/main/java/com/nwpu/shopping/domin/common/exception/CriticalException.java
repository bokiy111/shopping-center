package com.nwpu.shopping.domin.common.exception;

import lombok.Getter;

@Getter
public class CriticalException extends RuntimeException {
    private final int code;

    public CriticalException(ExceptionType type) {
        super(type.getMessage());
        this.code = type.getCode();
    }

    public CriticalException(ExceptionType type, String message) {
        super(message);
        this.code = type.getCode();
    }
}
