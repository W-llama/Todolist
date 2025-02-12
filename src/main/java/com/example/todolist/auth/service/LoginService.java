package com.example.todolist.auth.service;

import com.example.todolist.auth.dto.LoginRequestDto;
import com.example.todolist.auth.dto.LoginResponseDto;
import com.example.todolist.auth.security.jwt.JwtUtil;
import com.example.todolist.global.excetion.CustomException;
import com.example.todolist.global.excetion.ErrorCode;
import com.example.todolist.user.entity.TokenStore;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.entity.UserRole;
import com.example.todolist.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User loginUser = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USERNAME)
        );

        if(!passwordEncoder.matches(password, loginUser.getPassword())){
            System.out.println("비밀번호 불일치");
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        UserRole role = loginUser.getRole();

        String accessToken = jwtUtil.createToken(username, role,JwtUtil.ACCESS_TOKEN_EXPIRATION);
        String refreshToken = jwtUtil.createToken(username, role, JwtUtil.REFRESH_TOKEN_EXPIRATION);


        if (loginUser.getTokenStore() == null) {
            loginUser.setTokenStore(new TokenStore(refreshToken));
        } else {
            loginUser.getTokenStore().setRefreshToken(refreshToken);
        }

        userRepository.save(loginUser);

        return new LoginResponseDto(accessToken, refreshToken);
    }
}
