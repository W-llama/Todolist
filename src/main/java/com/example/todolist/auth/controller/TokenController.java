package com.example.todolist.auth.controller;

import com.example.todolist.auth.service.TokenService;
import com.example.todolist.global.dto.CommonResponse;
import com.example.todolist.user.entity.TokenStore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    @Operation(summary = "토큰연장", description = "로그인 연장기능")
    public ResponseEntity<CommonResponse<TokenStore>> refreshAccessToken(
            @RequestHeader("Authorization") String refreshTokenHeader
    ) {
        TokenStore tokenStore = tokenService.refreshAccessToken(refreshTokenHeader);
        return ResponseEntity.ok(new CommonResponse<>("로그인 연장 성공",200, tokenStore));
    }
}
