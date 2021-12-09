package com.devrun.backend.member.service;

import static org.junit.jupiter.api.Assertions.*;

import com.devrun.backend.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("MemberService 단위 테스트")
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("사용자 회원가입 테스트 - 성공")
    void joinMember_success() {

        // given

        // when

        // then

    }

    @Test
    void checkIsDuplicatedLoginId() {
    }

    @Test
    void checkIsDuplicatedEmail() {
    }

    @Test
    void login() {
    }

}
