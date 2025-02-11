package com.example.todolist.calendar.entity;

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

    @OneToMany(mappedBy = "calendar")
    private List<TodoLists> todoLists = new ArrayList<>();

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<CalendarUser> calendarUsers;
}
