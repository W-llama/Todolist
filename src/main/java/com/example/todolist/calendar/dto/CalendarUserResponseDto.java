package com.example.todolist.calendar.dto;

import com.example.todolist.calendar.entity.InviteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CalendarUserResponseDto {
    private Long id;
    private Long calendarId;
    private Long userId;
    private InviteStatus status;
}
