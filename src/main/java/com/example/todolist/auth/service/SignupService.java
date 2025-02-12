package com.example.todolist.auth.service;

import com.example.todolist.auth.dto.SignupRequestDto;
import com.example.todolist.auth.dto.SignupResponseDto;
import com.example.todolist.global.excetion.CustomException;
import com.example.todolist.global.excetion.ErrorCode;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.entity.UserRole;
import com.example.todolist.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SignupService {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$"
    );

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        try{
            validatePassword(signupRequestDto.getPassword());

            String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

            User user = User.builder()
                    .username(signupRequestDto.getUsername())
                    .password(encodedPassword)
                    .email(signupRequestDto.getEmail())
                    .role(UserRole.ROLE_USER)
                    .build();

            userRepository.save(user);

            return SignupResponseDto.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();

            } catch (DataIntegrityViolationException e) {
                throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
            } catch (Exception e) {
            throw new CustomException(ErrorCode.SIGNUP_FAILED);
        }
    }

    private void validatePassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
    }
}
