package com.devrun.backend.common.exception;

import lombok.Getter;

@Getter
public enum ErrorInfo {

    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    POST_NOT_FOUND("POST_NOT_FOUND", "게시글을 찾을 수 없습니다."),
    DUPLICATE_LOGIN_ID("DUPLICATE_LOGIN_ID", "중복된 아이디가 존재합니다."),
    DUPLICATE_EMAIL("DUPLICATE_EMAIL", "중복된 이메일이 존재합니다."),
    UNKNOWN("UNKNOWN", "서버 에러로 인해 데이터를 로드 할 수 없습니다."),
    NOT_EQUAL_PASSWORD_CONFIRM("NOT_EQUAL_PASSWORD_CONFIRM", "패스워드와 패스워드 확인이 일치하지 않습니다."),
    DUPLICATE_TAG_NAME("DUPLICATE_TAG_NAME", "중복된 태그명이 존재합니다."),
    TAG_NOT_FOUND("TAG_NOT_FOUND", "해당 태그를 찾을 수 없습니다."),
    LOGIN_FAIL("LOGIN_FAIL", "로그인에 실패하셨습니다.");


    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

}
