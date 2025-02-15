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

    @GetMapping("/readcalendar")
    public ResponseEntity<CommonResponse<List<CalendarResponseDto>>> readCalendar(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long userId = userDetails.getUserId();
        List<CalendarResponseDto> responseDto =calendarService.getCalendar(userId);
        return ResponseEntity.ok(new CommonResponse<>("일정 조회 완료",200, responseDto));
    }

    @GetMapping("/readcalendar/{id}")
    public ResponseEntity<CommonResponse<CalendarResponseDto>> readCalendarById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long userId = userDetails.getUserId();
        CalendarResponseDto response = calendarService.getCalendarById(id, userId);
        return ResponseEntity.ok(new CommonResponse<>("일정 조회 완료",200, response));
    }

    @PutMapping("/updatecalendar/{id}")
    public ResponseEntity<CommonResponse<CalendarResponseDto>> updateCalendar(
            @PathVariable Long id,
            @RequestBody CalendarRequestDto calendarRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        CalendarResponseDto response = calendarService.updateCalendarById(calendarRequestDto,userDetails ,id);
        return ResponseEntity.ok(new CommonResponse<>("일정 수정 완료",200, response));
    }

    @DeleteMapping("/deletecalendar/{id}")
    public ResponseEntity<CommonResponse<CalendarResponseDto>> deleteCalendar(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        CalendarResponseDto response = calendarService.deleteCalendarById(userDetails ,id);
        return ResponseEntity.ok(new CommonResponse<>("일정 삭제 완료",200, response));
    }
}
