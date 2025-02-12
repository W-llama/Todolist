package com.example.todolist.calendar.dto;

import com.example.todolist.calendar.entity.Calendar;
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

    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.description = calendar.getDescription();
        this.location = calendar.getLocation();
        this.startDate = calendar.getStartDate();
        this.endDate = calendar.getEndDate();
    }
}
