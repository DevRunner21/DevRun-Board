package com.devrun.backend.common.exception;

import com.devrun.backend.common.enums.ErrorInfo;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorInfo.USER_NOT_FOUND);
    }

}
