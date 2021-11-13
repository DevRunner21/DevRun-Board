package com.devrun.backend.member.dto.response;

import com.devrun.backend.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private String id;

    private String name;

    public static LoginResponse of(Member member) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(member.getLoginId());
        loginResponse.setName(member.getName());

        return loginResponse;
    }

    public static LoginResponse of(String id, String name) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(id);
        loginResponse.setName(name);

        return loginResponse;
    }

}
