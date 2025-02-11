package com.example.todolist.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDto {

    private String title;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
}
