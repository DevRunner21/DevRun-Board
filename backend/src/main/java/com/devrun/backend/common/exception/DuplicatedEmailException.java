package com.devrun.backend.common.exception;

import com.devrun.backend.common.enums.ErrorInfo;

public class DuplicatedEmailException extends BusinessException {

    public DuplicatedEmailException() {
        super(ErrorInfo.DUPLICATE_EMAIL);
    }

}
