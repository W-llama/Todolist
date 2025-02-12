package com.example.todolist.todo.service;

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

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final CalendarRepository calendarRepository;

    @Transactional
    public TodoResponseDto maketodo(Long calendarId ,TodoRequestDto todoRequestDto) {

        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        TodoLists todoLists = TodoLists.builder()
                .title(todoRequestDto.getTitle())
                .description(todoRequestDto.getDescription())
                .tag(todoRequestDto.getTag())
                .startTime(todoRequestDto.getStartDate())
                .dueTime(todoRequestDto.getDueDate())
                .build();

        todoRepository.save(todoLists);

        return TodoResponseDto.builder()
                .title(todoLists.getTitle())
                .description(todoLists.getDescription())
                .tag(todoLists.getTag())
                .startDate(todoLists.getStartTime())
                .dueDate(todoLists.getDueTime())
                .build();
    }
}
