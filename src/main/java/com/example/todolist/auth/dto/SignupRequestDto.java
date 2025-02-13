package com.example.todolist.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9]).{4,10}$",
            message = "아이디는 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 이루어져야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*+=-]).{8,15}$",
            message = "비밀번호는 8~15자이며, 대문자(A-Z) 1개 이상, 소문자(a-z) 1개 이상, 숫자(0-9) 1개 이상, 특수문자(!@#$%^*+=-) 1개 이상 포함해야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
