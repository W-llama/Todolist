package com.example.todolist.auth.service;

import com.example.todolist.auth.security.jwt.JwtUtil;
import com.example.todolist.global.dto.CommonResponse;
import com.example.todolist.global.excetion.CustomException;
import com.example.todolist.global.excetion.ErrorCode;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogoutService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LogoutService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public CommonResponse<Void> logout(HttpServletRequest request) {

        String token = jwtUtil.getJwtTokenFromHeader(request);
        if (token == null || token.isEmpty()) {
            throw new CustomException(ErrorCode.FALSE_TOKEN);
        }

        String username;
        try {
            username = jwtUtil.getUserInfoFromToken(token).getSubject();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRATION);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USERNAME));

        if (user.getTokenStore() != null && user.getTokenStore().getRefreshToken() != null) {
            user.clearRefreshToken();
        } else {
            throw new CustomException(ErrorCode.FALSE_TOKEN);
        }

        return new CommonResponse<>("로그아웃 되었습니다.", 200, null);
    }
}
