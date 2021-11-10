package com.devrun.backend.common.exception;

import com.devrun.backend.common.enums.ErrorInfo;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BusinessException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

}
