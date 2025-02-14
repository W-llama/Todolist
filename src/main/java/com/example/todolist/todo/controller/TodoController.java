package com.example.todolist.todo.controller;

import com.example.todolist.auth.UserDetailsImpl;
import com.example.todolist.global.dto.CommonResponse;
import com.example.todolist.todo.dto.TodoRequestDto;
import com.example.todolist.todo.dto.TodoResponseDto;
import com.example.todolist.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{calendarId}/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<CommonResponse<TodoResponseDto>> createTodo(
            @PathVariable Long calendarId,
            @RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto responseDto = todoService.createTodo(calendarId, todoRequestDto);
        return ResponseEntity.ok(new CommonResponse<>("할 일 생성 완료", 200, responseDto));
    }

    @GetMapping("/readtodo")
    public ResponseEntity<CommonResponse<List<TodoResponseDto>>> getTodos(@PathVariable Long calendarId) {
        List<TodoResponseDto> todos = todoService.getTodos(calendarId);
        return ResponseEntity.ok(new CommonResponse<>("할 일 목록 조회 완료", 200, todos));
    }

    @GetMapping("/readtodo/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> getTodoById(@PathVariable Long todoId) {
        TodoResponseDto todo = todoService.getTodoById(todoId);
        return ResponseEntity.ok(new CommonResponse<>("할 일 조회 완료", 200, todo));
    }

    @PutMapping("/updatetodo/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> updateTodo(
            @PathVariable Long todoId,
            @RequestBody TodoRequestDto todoRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto updatedTodo = todoService.updateTodo(todoId, todoRequestDto, userDetails);
        return ResponseEntity.ok(new CommonResponse<>("할 일 수정 완료", 200, updatedTodo));
    }

    @PutMapping("/{todoId}/toggle")
    public ResponseEntity<CommonResponse<TodoResponseDto>> toggleTodo(
            @PathVariable Long todoId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto updatedTodoToggle = todoService.updatedTodoToggle(todoId, userDetails);
        return ResponseEntity.ok(new CommonResponse<>("할 일 수정 완료", 200, updatedTodoToggle));
    }


    @DeleteMapping("/deletetodo/{todoId}")
    public ResponseEntity<CommonResponse<Void>> deleteTodo(
            @PathVariable Long todoId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        todoService.deleteTodo(todoId, userDetails);
        return ResponseEntity.ok(new CommonResponse<>("할 일 삭제 완료", 200, null));
    }
}
