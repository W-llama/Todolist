package com.example.todolist.todo.entity;

import com.example.todolist.calendar.entity.Calendar;
import com.example.todolist.global.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoLists extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_list_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime dueTime;

    @Column
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;
}
