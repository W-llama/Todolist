package com.example.todolist.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@Builder
public class TokenStore {

    @Column(name = "refresh_token", length = 500)
    private String refreshToken;

    @Transient
    private String newAccessToken;

    public TokenStore(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenStore(String newAccessToken, String refreshToken) {
        this.newAccessToken = newAccessToken;
        this.refreshToken = refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void clearTokens() {
        this.refreshToken = null;
    }
}
