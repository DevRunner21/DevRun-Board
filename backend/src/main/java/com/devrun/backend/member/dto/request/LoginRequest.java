package com.devrun.backend.member.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank(message = "Id는 필수 입력값입니다.")
    private String id;

    @NotBlank(message = "Password 필수 입력값입니다.")
    private String password;

}
