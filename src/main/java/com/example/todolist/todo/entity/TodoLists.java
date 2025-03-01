package com.example.todolist.todo.entity;

import com.example.todolist.calendar.entity.Calendar;
import jakarta.persistence.*;
import lombok.*;

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

    @Column
    private boolean isCompleted;

    public void updateTodo(String title, String description, String tag, LocalDateTime startTime, LocalDateTime dueTime, boolean isCompleted) {
        if (title != null) this.title = title;
        if (description != null) this.description = description;
        if (tag != null) this.tag = tag;
        if (startTime != null) this.startTime = startTime;
        if (dueTime != null) this.dueTime = dueTime;
        this.isCompleted = isCompleted;
    }

    public void toggleCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
