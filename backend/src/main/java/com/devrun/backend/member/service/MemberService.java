package com.devrun.backend.member.service;

import com.devrun.backend.member.domain.MemberRepository;
import com.devrun.backend.member.domain.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PermissionRepository permissionRepository;

//    @Transactional
//    public void joinMember(JoinMemberRequest request) {
//
//        if (isDuplicatedLoginId(request.getId())) {
//            throw new BusinessException(ErrorInfo.DUPLICATE_LOGIN_ID);
//        }
//
//        if (isDuplicatedEmail(request.getEmail())) {
//            throw new BusinessException(ErrorInfo.DUPLICATE_EMAIL);
//        }
//
//        if (!request.checkPasswordConfirm()) {
//            throw new BusinessException(ErrorInfo.NOT_EQUAL_PASSWORD_CONFIRM);
//        }
//
//        memberRepository.save(request.convertToMember());
//
//    }
//
//    public DuplicationCheckResponse checkIsDuplicatedLoginId(String loginId) {
//        return DuplicationCheckResponse.of(isDuplicatedLoginId(loginId));
//    }
//
//    private boolean isDuplicatedLoginId(String loginId) {
//        return memberRepository.findMembersByLoginId(loginId).isPresent();
//    }
//
//    public DuplicationCheckResponse checkIsDuplicatedEmail(String email) {
//        return DuplicationCheckResponse.of(isDuplicatedEmail(email));
//    }
//
//    private boolean isDuplicatedEmail(String email) {
//        return memberRepository.findMembersByEmail(email).isPresent();
//    }
//
//    public LoginResponse login(LoginRequest request) {
//        Member foundMember = memberRepository.findMembersByLoginId(request.getId())
//                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));
//
//        if (!matchPassword(request, foundMember)) {
//            throw new BusinessException(ErrorInfo.LOGIN_FAIL);
//        }
//
//        return LoginResponse.of(foundMember);
//    }
//
//    private boolean matchPassword(LoginRequest request, Member foundMember) {
//        return foundMember.getLoginPw().equals(request.getPassword());
//    }

}
