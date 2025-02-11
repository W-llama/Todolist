package com.example.todolist.calendar.dto;

import com.example.todolist.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRequestDto {

    private String title;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private User user;
}
