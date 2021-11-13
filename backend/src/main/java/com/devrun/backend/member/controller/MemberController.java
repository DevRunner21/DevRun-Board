package com.devrun.backend.member.controller;

import com.devrun.backend.common.dto.ApiResponse;
import com.devrun.backend.common.dto.DuplicationCheckResponse;
import com.devrun.backend.member.dto.request.JoinMemberRequest;
import com.devrun.backend.member.dto.request.LoginRequest;
import com.devrun.backend.member.dto.response.LoginResponse;
import com.devrun.backend.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse join(@Valid @RequestBody JoinMemberRequest request) {
        memberService.joinMember(request);
        return ApiResponse.ok(null);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<DuplicationCheckResponse> checkDuplicatedLoginId(@RequestParam("loginId") String loginId) {
        DuplicationCheckResponse response = memberService.checkIsDuplicatedLoginId(loginId);
        return ApiResponse.ok(response);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponse<DuplicationCheckResponse> checkDuplicatedEmail(@RequestParam("email") String email) {
//        DuplicationCheckResponse response = memberService.checkIsDuplicatedEmail(email);
//        return ApiResponse.ok(response);
//    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request);
        return ApiResponse.ok(response);
    }

}
