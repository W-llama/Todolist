package com.example.todolist.todo.dto;

import com.example.todolist.global.Timestamp;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponseDto extends Timestamp {

    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private String tag;
}
