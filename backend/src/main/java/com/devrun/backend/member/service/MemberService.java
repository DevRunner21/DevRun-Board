package com.devrun.backend.member.service;

import com.devrun.backend.domain.member.Member;
import com.devrun.backend.domain.member.Permission;
import com.devrun.backend.domain.member.PermissionRepository;
import com.devrun.backend.domain.member.MemberRepository;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PermissionRepository permissionRepository;

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        Assert.notNull(username,"username must be provided.");

        return memberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByProviderAndProviderId(String provider, String providerId) {
        Assert.notNull(provider,"provider must be provided.");
        Assert.notNull(providerId,"providerId must be provided.");

        return memberRepository.findByProviderAndProviderId(provider, providerId);
    }

    @Transactional
    public Member join(OAuth2User oauth2User, String authorizedClientRegistrationId) {
//        checkArgument(oauth2User != null, "oauth2User must be provided.");
        Assert.notNull(authorizedClientRegistrationId,"provider must be provided.");

        String providerId = oauth2User.getName();
        return findByProviderAndProviderId(authorizedClientRegistrationId, providerId)
                .map(user -> {
                    log.warn("Already exists: {} for (provider: {}, providerId: {})", user, authorizedClientRegistrationId, providerId);
                    return user;
                })
                .orElseGet(() -> {
                    Map<String, Object> attributes = oauth2User.getAttributes();
                    @SuppressWarnings("unchecked")
                    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
//                    checkArgument(properties != null, "OAuth2User properties is empty");
                    String nickname = (String) properties.get("nickname");
                    String profileImage = (String) properties.get("profile_image");
                    String name = "ROLE_USER";

                    Permission permission = permissionRepository.findByName(name)
                            .orElseThrow(() -> new IllegalStateException("Could not found group for USER_GROUP"));

                    return memberRepository.save(
                            Member.of(nickname, authorizedClientRegistrationId, providerId, profileImage, permission)
                    );
                });
    }



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
