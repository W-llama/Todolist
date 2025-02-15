package com.example.todolist.auth.security.filter;

import com.example.todolist.auth.dto.LoginRequestDto;
import com.example.todolist.auth.dto.LoginResponseDto;
import com.example.todolist.auth.security.jwt.JwtUtil;
import com.example.todolist.global.dto.CommonResponse;
import com.example.todolist.global.excetion.CustomException;
import com.example.todolist.global.excetion.ErrorCode;
import com.example.todolist.user.entity.TokenStore;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.entity.UserRole;
import com.example.todolist.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        super.setAuthenticationManager(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;

        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword(), null)
            );
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((UserDetails) authResult.getPrincipal()).getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USERNAME));

        UserRole role = user.getRole();

        String accessToken = jwtUtil.createToken(username, role, JwtUtil.ACCESS_TOKEN_EXPIRATION);
        String refreshToken = jwtUtil.createToken(username, role, JwtUtil.REFRESH_TOKEN_EXPIRATION);

        saveRefreshToken(user, refreshToken);

        CommonResponse<LoginResponseDto> responseDto = new CommonResponse<>(
                "로그인 성공",
                200,
                new LoginResponseDto(accessToken, refreshToken)
        );

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        responseSetting(response, HttpServletResponse.SC_OK, responseDto);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        if (failed instanceof BadCredentialsException) {
            responseSetting(response, HttpServletResponse.SC_UNAUTHORIZED,
                    Map.of("message", "로그인 실패: 비밀번호가 일치하지 않습니다."));
        } else if (failed instanceof UsernameNotFoundException) {
            responseSetting(response, HttpServletResponse.SC_UNAUTHORIZED,
                    Map.of("message", "로그인 실패: 아이디를 찾을 수 없습니다."));
        } else {
            responseSetting(response, HttpServletResponse.SC_UNAUTHORIZED,
                    Map.of("message", "로그인 실패: 인증에 실패했습니다."));
        }
    }

    private void saveRefreshToken(User user, String refreshToken) {
        String pureRefreshToken = refreshToken.replace("Bearer ", "");

        if (user.getTokenStore() == null) {
            user.setTokenStore(TokenStore.builder()
                    .refreshToken(pureRefreshToken)
                    .build());
        } else {
            user.getTokenStore().setRefreshToken(pureRefreshToken);
        }

        userRepository.save(user);
    }

    private void responseSetting(HttpServletResponse response, int status, Object responseObject) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseObject));
    }
}