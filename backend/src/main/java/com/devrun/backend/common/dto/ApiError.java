package com.devrun.backend.common.dto;

import com.devrun.backend.common.enums.ErrorInfo;
import lombok.Getter;
import lombok.Value;

@Getter
public class ApiError {

    private String code;

    private Object message;

    private ApiError(String code, Object message) {
        this.code = code;
        this.message = message;
    }

    static ApiError of(ErrorInfo errorInfo) {
        return new ApiError(errorInfo.getCode(), errorInfo.getMessage());
    }

    static ApiError of(String code, Object messages) {
        return new ApiError(code, messages);
    }

}
