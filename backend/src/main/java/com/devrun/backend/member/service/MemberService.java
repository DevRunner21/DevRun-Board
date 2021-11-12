package com.devrun.backend.member.service;

import com.devrun.backend.common.dto.DuplicationCheckResponse;
import com.devrun.backend.common.enums.ErrorInfo;
import com.devrun.backend.common.exception.BusinessException;
import com.devrun.backend.member.dto.request.JoinMemberRequest;
import com.devrun.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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
}
