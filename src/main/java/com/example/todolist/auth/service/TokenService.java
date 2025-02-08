package com.example.todolist.auth.service;

import com.example.todolist.auth.security.jwt.JwtUtil;
import com.example.todolist.global.excetion.CustomException;
import com.example.todolist.global.excetion.ErrorCode;
import com.example.todolist.user.entity.TokenStore;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public TokenStore refreshAccessToken(String refreshTokenHeader) {
        String refreshToken = refreshTokenHeader.replace("Bearer ", "");

        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        Claims claims = jwtUtil.getUserInfoFromToken(refreshToken);
        String username = claims.getSubject();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USERNAME));

        if (!refreshToken.equals(user.getTokenStore().getRefreshToken())) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String newAccessToken = jwtUtil.createToken(username, user.getRole(), JwtUtil.ACCESS_TOKEN_EXPIRATION);

        return new TokenStore(newAccessToken ,refreshToken);
    }
}
