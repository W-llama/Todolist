package com.example.todolist.calendar.entity;

import com.example.todolist.calendar.dto.CalendarRequestDto;
import com.example.todolist.todo.entity.TodoLists;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendars_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String location;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<TodoLists> todoLists = new ArrayList<>();

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<CalendarUser> calendarUsers;

    public void updateCalendar(CalendarRequestDto requestDto) {
        if (requestDto.getTitle() != null) this.title = requestDto.getTitle();
        if (requestDto.getDescription() != null) this.description = requestDto.getDescription();
        if (requestDto.getStartDate() != null) this.startDate = requestDto.getStartDate();
        if (requestDto.getEndDate() != null) this.endDate = requestDto.getEndDate();
        if (requestDto.getLocation() != null) this.location = requestDto.getLocation();
    }
}
