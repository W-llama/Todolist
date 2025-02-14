package com.example.todolist.user.entity;

import com.example.todolist.calendar.entity.CalendarUser;
import com.example.todolist.global.Timestamp;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class User extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 225)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CalendarUser> calendarUsers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private TokenStore tokenStore;

    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
        if (tokenStore != null) {
            tokenStore.setUser(this);
        }
    }

    public void clearRefreshToken() {
        if (this.tokenStore != null) {
            this.tokenStore.clearTokens();
        }
    }
}
