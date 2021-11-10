package com.devrun.backend.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DuplicationCheckResult {

    boolean result;

    public static DuplicationCheckResult of(boolean result) {
        return new DuplicationCheckResult(result);
    }

}
