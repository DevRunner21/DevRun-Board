package com.devrun.backend.member.service;

import com.devrun.backend.common.dto.DuplicationCheckResponse;
import com.devrun.backend.common.enums.ErrorInfo;
import com.devrun.backend.common.exception.BusinessException;
import com.devrun.backend.member.domain.Member;
import com.devrun.backend.member.dto.request.JoinMemberRequest;
import com.devrun.backend.member.dto.request.LoginRequest;
import com.devrun.backend.member.dto.response.LoginResponse;
import com.devrun.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void joinMember(JoinMemberRequest request) {

        if (isDuplicatedLoginId(request.getId())) {
            throw new BusinessException(ErrorInfo.DUPLICATE_LOGIN_ID);
        }

        if (isDuplicatedEmail(request.getEmail())) {
            throw new BusinessException(ErrorInfo.DUPLICATE_EMAIL);
        }

        if (!request.checkPasswordConfirm()) {
            throw new BusinessException(ErrorInfo.NOT_EQUAL_PASSWORD_CONFIRM);
        }

        memberRepository.save(request.convertToMember());

    }

    public DuplicationCheckResponse checkIsDuplicatedLoginId(String loginId) {
        return DuplicationCheckResponse.of(isDuplicatedLoginId(loginId));
    }

    private boolean isDuplicatedLoginId(String loginId) {
        return memberRepository.findMembersByLoginId(loginId).isPresent();
    }

    public DuplicationCheckResponse checkIsDuplicatedEmail(String email) {
        return DuplicationCheckResponse.of(isDuplicatedEmail(email));
    }

    private boolean isDuplicatedEmail(String email) {
        return memberRepository.findMembersByEmail(email).isPresent();
    }

    public LoginResponse login(LoginRequest request) {
        Member foundMember = memberRepository.findMembersByLoginId(request.getId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        if (!matchPassword(request, foundMember)) {
            throw new BusinessException(ErrorInfo.LOGIN_FAIL);
        }

        return LoginResponse.of(foundMember);
    }

    private boolean matchPassword(LoginRequest request, Member foundMember) {
        return foundMember.getLoginPw().equals(request.getPassword());
    }

}
