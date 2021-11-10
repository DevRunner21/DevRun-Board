package com.devrun.backend.common.exception;

import com.devrun.backend.common.dto.ApiResponse;
import com.devrun.backend.common.enums.ErrorInfo;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("{}", ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
            .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        return ApiResponse.error("METHOD_ARG_NOT_VALID", errors);
    }

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> handleNotFoundException(BusinessException ex){
        log.error("{}", ex);
        final ErrorInfo errorInfo = ex.getErrorInfo();

        return ApiResponse.error(ex.getErrorInfo());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception ex){
        log.error("{}", ex);

        return ApiResponse.error(ErrorInfo.UNKNOWN);
    }

}
