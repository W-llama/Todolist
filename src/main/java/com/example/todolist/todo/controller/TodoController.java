package com.example.todolist.todo.controller;

import com.example.todolist.global.dto.CommonResponse;
import com.example.todolist.todo.dto.TodoRequestDto;
import com.example.todolist.todo.dto.TodoResponseDto;
import com.example.todolist.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendar")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/{calendarId}/todo")
    public ResponseEntity<CommonResponse<TodoResponseDto>> maketodo(
            @PathVariable Long calendarId,
            @RequestBody TodoRequestDto todoRequestDto
    ){
        TodoResponseDto responseDto = todoService.maketodo(calendarId,todoRequestDto);
        return ResponseEntity.ok(new CommonResponse<>("일정 생성완료", 200, responseDto));
    }
}