package com.devrun.backend.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BusinessException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

}
