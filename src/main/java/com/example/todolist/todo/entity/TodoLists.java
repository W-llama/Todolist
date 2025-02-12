package com.example.todolist.todo.entity;

import com.example.todolist.calendar.entity.Calendar;
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
public class TodoLists {

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
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;

    public void updateTodo(String title, String description, String tag, LocalDateTime startTime, LocalDateTime dueTime) {
        if (title != null) this.title = title;
        if (description != null) this.description = description;
        if (tag != null) this.tag = tag;
        if (startTime != null) this.startTime = startTime;
        if (dueTime != null) this.dueTime = dueTime;
    }
}
