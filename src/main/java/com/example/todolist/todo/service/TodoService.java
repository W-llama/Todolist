package com.example.todolist.todo.service;

import com.example.todolist.auth.UserDetailsImpl;
import com.example.todolist.calendar.entity.Calendar;
import com.example.todolist.calendar.repository.CalendarRepository;
import com.example.todolist.global.excetion.CustomException;
import com.example.todolist.global.excetion.ErrorCode;
import com.example.todolist.todo.dto.TodoRequestDto;
import com.example.todolist.todo.dto.TodoResponseDto;
import com.example.todolist.todo.entity.TodoLists;
import com.example.todolist.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final CalendarRepository calendarRepository;

    @Transactional
    public TodoResponseDto createTodo(Long calendarId, TodoRequestDto todoRequestDto) {

        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

        if(todoRequestDto.getStartDate().isBefore(calendar.getStartDate().atStartOfDay())){
            throw new CustomException(ErrorCode.INVALID_TODO_START_DATE);
        }

        if(todoRequestDto.getDueDate().isAfter(calendar.getEndDate().atStartOfDay())){
            throw new CustomException(ErrorCode.INVALID_TODO_DUE_DATE);
        }

        TodoLists todoLists = TodoLists.builder()
                .calendar(calendar)
                .title(todoRequestDto.getTitle())
                .description(todoRequestDto.getDescription())
                .tag(todoRequestDto.getTag())
                .startTime(todoRequestDto.getStartDate())
                .dueTime(todoRequestDto.getDueDate())
                .isCompleted(false)
                .build();

        todoRepository.save(todoLists);

        return new TodoResponseDto(todoLists);
    }

    @Transactional
    public List<TodoResponseDto> getTodos(Long calendarId) {
        List<TodoLists> todos = todoRepository.findByCalendarId(calendarId);

        return todos.stream()
                .map(TodoResponseDto::new)
                .toList();
    }

    @Transactional
    public TodoResponseDto getTodoById(Long todoId) {
        TodoLists todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TODO));

        return new TodoResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoRequestDto todoRequestDto, UserDetailsImpl userDetails) {

        TodoLists todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TODO));

        boolean isUserInCalendar = todo.getCalendar().getCalendarUsers().stream()
                        .anyMatch(calendarUser -> calendarUser.getUser().getId().equals(userDetails.getUserId()));

        if (!isUserInCalendar) {
            throw new CustomException(ErrorCode.NO_TODO_UPDATE_PERMISSION);
        }

        todo.updateTodo(
                todoRequestDto.getTitle(),
                todoRequestDto.getDescription(),
                todoRequestDto.getTag(),
                todoRequestDto.getStartDate(),
                todoRequestDto.getDueDate(),
                todoRequestDto.getIsCompleted()
        );

        return new TodoResponseDto(todo);
    }

    public TodoResponseDto updatedTodoToggle(Long todoId, UserDetailsImpl userDetails) {
        TodoLists todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TODO));

        todo.setCompleted(true);
        todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

    @Transactional
    public void deleteTodo(Long todoId, UserDetailsImpl userDetails) {
        TodoLists todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TODO));

        boolean isUserInCalendar = todo.getCalendar().getCalendarUsers().stream()
                .anyMatch(calendarUser -> calendarUser.getUser().getId().equals(userDetails.getUserId()));

        if (!isUserInCalendar) {
            throw new CustomException(ErrorCode.NO_TODO_DELETE_PERMISSION);
        }

        todoRepository.delete(todo);
    }
}

