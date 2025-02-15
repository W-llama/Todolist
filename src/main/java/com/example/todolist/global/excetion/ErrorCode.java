package com.example.todolist.global.excetion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD REQUEST"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT FOUND"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR"),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다."),
    TOKEN_EXPIRATION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다. 재로그인 해주세요."),
    NOT_SUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),
    FALSE_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 JWT 토큰입니다."),
    HEADER_NOT_FOUND(HttpStatus.BAD_REQUEST, "헤더가 잘못되었거나 누락되었습니다."),
    UNMATCHED_TOKEN(HttpStatus.BAD_REQUEST, "일치하지 않는 토큰입니다."),

    NOT_FOUND_USERNAME(HttpStatus.NOT_FOUND, "유저를 찾을수 없습니다."),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED,"잘못된권한입니다." ),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "중복된 유저아이디입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다." ),
    SIGNUP_FAILED(HttpStatus.BAD_REQUEST, "회원가입에 실패하셨습니다."), 
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 이루어져 있어야 합니다."),

    NOT_FOUND_CALENDAR(HttpStatus.NOT_FOUND,"해당 일정이 존재하지 않습니다: " ),
    NO_CALENDAR_UPDATE_PERMISSION(HttpStatus.UNAUTHORIZED,"일정 수정 권한이 없습니다." ),
    NO_CALENDAR_DELETE_PERMISSION(HttpStatus.UNAUTHORIZED,"일정 삭제 권한이 없습니다." ),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED,"일정 조회 권한이 없습니다."),

    NOT_FOUND_TODO(HttpStatus.FORBIDDEN,"존재하지 않은 할 일입니다." ),
    NO_TODO_UPDATE_PERMISSION(HttpStatus.UNAUTHORIZED,"할일 수정 권한이 없습니다." ),
    NO_TODO_DELETE_PERMISSION(HttpStatus.UNAUTHORIZED,"할일 수정 권한이 없습니다." ),
    INVALID_TODO_START_DATE(HttpStatus.UNAUTHORIZED, "유효 하지 않은 시작일 입니다."),
    INVALID_TODO_DUE_DATE(HttpStatus.UNAUTHORIZED, "유효 하지 않은 마감일 입니다."),
;
    private final HttpStatus status;
    private final String message;
}
