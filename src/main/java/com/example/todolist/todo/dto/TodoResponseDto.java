package com.example.todolist.todo.dto;

import com.example.todolist.global.Timestamp;
import com.example.todolist.todo.entity.TodoLists;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponseDto extends Timestamp {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private String tag;

    public TodoResponseDto(TodoLists todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.tag = todo.getTag();
        this.startDate = todo.getStartTime();
        this.dueDate = todo.getDueTime();
    }
}
