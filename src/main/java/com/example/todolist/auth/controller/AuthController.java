package com.example.todolist.auth.controller;

import com.example.todolist.auth.dto.LoginRequestDto;
import com.example.todolist.auth.dto.LoginResponseDto;
import com.example.todolist.auth.dto.SignupRequestDto;
import com.example.todolist.auth.dto.SignupResponseDto;
import com.example.todolist.auth.service.LoginService;
import com.example.todolist.auth.service.LogoutService;
import com.example.todolist.auth.service.SignupService;
import com.example.todolist.global.dto.CommonResponse;
import com.example.todolist.global.excetion.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignupService signupService;

    private final LoginService loginService;

    private final LogoutService logoutService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 기능")
    public ResponseEntity<CommonResponse<SignupResponseDto>> signup(
            @Valid @RequestBody SignupRequestDto signupRequestDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), 400, null));
        }
        SignupResponseDto responseDto = signupService.signup(signupRequestDto);
        return ResponseEntity.ok(new CommonResponse<>("회원가입 성공", 200, responseDto));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 기능")
    public ResponseEntity<CommonResponse<LoginResponseDto>> login(
            @RequestBody LoginRequestDto loginRequestDto
    ){
        try {
            LoginResponseDto responseDto = loginService.login(loginRequestDto);
            CommonResponse<LoginResponseDto> response = new CommonResponse<>("로그인 성공", 200, responseDto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            CommonResponse<LoginResponseDto> response = new CommonResponse<>("잘못된 요청입니다.", 400, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            CommonResponse<LoginResponseDto> response = new CommonResponse<>("서버 오류가 발생했습니다.", 500, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃 기능")
    public ResponseEntity<CommonResponse<Void>> logout(HttpServletRequest request) {
        try {
            CommonResponse<Void> response = logoutService.logout(request);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            CommonResponse<Void> response = new CommonResponse<>(e.getMessage(), e.getStatusCode().value(), null);
            return ResponseEntity.status(e.getStatusCode()).body(response);
        } catch (Exception e) {
            CommonResponse<Void> response = new CommonResponse<>("로그아웃 실패", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
