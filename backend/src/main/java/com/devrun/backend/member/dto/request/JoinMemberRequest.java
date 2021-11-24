package com.devrun.backend.member.dto.request;

import com.devrun.backend.domain.member.Member;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JoinMemberRequest {

    @NotBlank(message = "Id는 필수로 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호는 필수로 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호는 필수로 입력해주세요.")
    private String passwordConfirm;

    @NotBlank(message = "이름은 필수로 입력해주세요.")
    private String name;

    @Email(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "올바른 이메일 형식을 입력해주세요.")
    private String email;

    public Member convertToMember() {
        return Member.of(this.id, this.password, this.name, this.email);
    }

    public boolean checkPasswordConfirm() {
        return this.password.equals(this.passwordConfirm);
    }

}
