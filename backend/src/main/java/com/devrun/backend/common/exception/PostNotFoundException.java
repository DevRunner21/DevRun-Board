package com.devrun.backend.common.exception;

import com.devrun.backend.common.enums.ErrorInfo;

public class PostNotFoundException extends BusinessException {

    public PostNotFoundException() {
        super(ErrorInfo.POST_NOT_FOUND);
    }

}
