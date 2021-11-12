package com.devrun.backend.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DuplicationCheckResponse {

    boolean result;

    public static DuplicationCheckResponse of(boolean result) {
        return new DuplicationCheckResponse(result);
    }

}
