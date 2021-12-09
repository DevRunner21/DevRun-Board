package com.devrun.backend.member.controller;

import com.devrun.backend.common.enums.ErrorInfo;
import com.devrun.backend.common.exception.BusinessException;
import com.devrun.backend.jwt.JwtAuthentication;
import com.devrun.backend.member.dto.response.MemberResponse;
import com.devrun.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 보호받는 엔드포인트 - ROLE_USER 또는 ROLE_ADMIN 권한 필요함
     */
    @GetMapping(path = "/me")
    public MemberResponse me(@AuthenticationPrincipal JwtAuthentication authentication) {
        return memberService.findByUsername(authentication.getUsername())
                .map(member -> MemberResponse.of(authentication.getToken(), authentication.getUsername(), member.getPermission().getName()))
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));
    }

//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponse join(@Valid @RequestBody JoinMemberRequest request) {
//        memberService.joinMember(request);
//        return ApiResponse.ok(null);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponse<DuplicationCheckResponse> checkDuplicatedLoginId(@RequestParam("loginId") String loginId) {
//        DuplicationCheckResponse response = memberService.checkIsDuplicatedLoginId(loginId);
//        return ApiResponse.ok(response);
//    }
//
////    @ResponseStatus(HttpStatus.OK)
////    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
////    public ApiResponse<DuplicationCheckResponse> checkDuplicatedEmail(@RequestParam("email") String email) {
////        DuplicationCheckResponse response = memberService.checkIsDuplicatedEmail(email);
////        return ApiResponse.ok(response);
////    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
//        LoginResponse response = memberService.login(request);
//        return ApiResponse.ok(response);
//    }

}
