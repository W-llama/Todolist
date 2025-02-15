package com.example.todolist.calendar.entity;

import com.example.todolist.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_users_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InviteStatus inviteStatus = InviteStatus.PENDING;

    public CalendarUser(Calendar calendar, User user) {
        this.calendar = calendar;
        this.user = user;
    }

    public CalendarUser(Calendar calendar, User invitedUser, InviteStatus inviteStatus) {
        this.calendar = calendar;
        this.user = invitedUser;
        this.inviteStatus = inviteStatus;
    }

    public void updateInviteStatus(InviteStatus status) {
        this.inviteStatus = status;
    }
}

