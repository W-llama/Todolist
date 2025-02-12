package com.example.todolist.calendar.controller;

import com.example.todolist.auth.UserDetailsImpl;
import com.example.todolist.calendar.dto.CalendarRequestDto;
import com.example.todolist.calendar.dto.CalendarResponseDto;
import com.example.todolist.calendar.service.CalendarService;
import com.example.todolist.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping
    public ResponseEntity<CommonResponse<CalendarResponseDto>> createcalendar(
            @RequestBody CalendarRequestDto calendarRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CalendarResponseDto responseDto = calendarService.createCalendar(calendarRequestDto, userDetails);
        return ResponseEntity.ok(new CommonResponse<>("일정 생성 완료", 200, responseDto));
    }

    @GetMapping("/read")
    public ResponseEntity<CommonResponse<List<CalendarResponseDto>>> readCalendar(
    ){
        List<CalendarResponseDto> responseDto =calendarService.getCalendar();
        return ResponseEntity.ok(new CommonResponse<>("일정 조회 완료",200, responseDto));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CommonResponse<CalendarResponseDto>> readCalendarById(
            @PathVariable Long id
    ){
        CalendarResponseDto response = calendarService.getCalendarById(id);
        return ResponseEntity.ok(new CommonResponse<>("일정 조회 완료",200, response));
    }
}
