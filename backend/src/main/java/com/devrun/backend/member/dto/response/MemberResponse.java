package com.devrun.backend.member.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private String token;

    private String loginId;

    private String permission;


    public static MemberResponse of(String token, String username, String permission) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setToken(token);
        memberResponse.setLoginId(username);
        memberResponse.setPermission(permission);

        return memberResponse;
    }
}
