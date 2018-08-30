package com.zust.itee.exception;

import lombok.Data;

/**
 * 业务逻辑异常
 *
 * @author pojun
 */
@Data
public class BusinessException extends RuntimeException {

    private Integer code;

    private static final int DEFAULT_ERROR_CODE = 0;

    public BusinessException(String message) {
        super(message);
        this.code = DEFAULT_ERROR_CODE;
    }

    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
