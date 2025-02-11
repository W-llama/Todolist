package com.example.todolist.calendar.controller;

import com.example.todolist.auth.UserDetailsImpl;
import com.example.todolist.calendar.dto.CalendarRequestDto;
import com.example.todolist.calendar.dto.CalendarResponseDto;
import com.example.todolist.calendar.service.CalendarService;
import com.example.todolist.global.dto.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping
    public ResponseEntity<CommonResponse<CalendarResponseDto>> makecalendar(
            @RequestBody CalendarRequestDto calendarRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        CalendarResponseDto responseDto = calendarService.makecalendar(calendarRequestDto, userDetails);
        return ResponseEntity.ok(new CommonResponse<>("일정 생성 완료", 200, responseDto));
    }
}
