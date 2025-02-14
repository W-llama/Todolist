package com.example.todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {

    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private String tag;
    private boolean isCompleted;

    public boolean getIsCompleted() {
        return isCompleted;
    }
}
