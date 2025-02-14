package com.example.todolist.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "refresh_token", length = 500)
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private String newAccessToken;

    public TokenStore(String refreshToken, User loginUser) {
        this.refreshToken = refreshToken;
        this.user = loginUser;
    }

    public TokenStore(String newAccessToken, String refreshToken) {
        this.newAccessToken = newAccessToken;
        this.refreshToken = refreshToken;
    }

    public void clearTokens() {
        this.refreshToken = null;
    }
}
