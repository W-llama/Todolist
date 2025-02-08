package com.example.todolist.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {

    private String AccessToken;
    private String refreshToken;

    public LoginResponseDto(String accessToken, String refreshToken) {
        this.AccessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
