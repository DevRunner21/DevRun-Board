package com.devrun.backend.common.exception;

import com.devrun.backend.common.enums.ErrorInfo;

public class DuplicatedLoginIdException extends BusinessException {

    public DuplicatedLoginIdException() {
        super(ErrorInfo.DUPLICATE_LOGIN_ID);
    }

}
