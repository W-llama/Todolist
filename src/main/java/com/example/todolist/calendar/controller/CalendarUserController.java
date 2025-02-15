package com.example.todolist.calendar.controller;

import com.example.todolist.calendar.dto.CalendarUserResponseDto;
import com.example.todolist.calendar.entity.InviteStatus;
import com.example.todolist.calendar.service.CalendarUserService;
import com.example.todolist.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar/{calendarId}/invite")
@RequiredArgsConstructor
public class CalendarUserController {

    private final CalendarUserService calendarUserService;

    @PostMapping
    public ResponseEntity<CommonResponse<CalendarUserResponseDto>> inviteUser(
            @PathVariable Long calendarId,
            @RequestParam String username
    ){
        CalendarUserResponseDto response = calendarUserService.inviteUserToCalendar(calendarId, username);
        return ResponseEntity.ok(new CommonResponse<>("초대 성공",200,response));
    }

    @PutMapping("/{inviteId}")
    public ResponseEntity<CommonResponse<CalendarUserResponseDto>> respondToInvite(
            @PathVariable Long calendarId,
            @PathVariable Long inviteId,
            @RequestParam InviteStatus status
    ) {
        CalendarUserResponseDto response = calendarUserService.respondToInvite(inviteId, status);
        return ResponseEntity.ok(new CommonResponse<>("초대 응답 완료", 200, response));
    }

    @GetMapping("/list")
    public ResponseEntity<CommonResponse<List<CalendarUserResponseDto>>> getInvitesByCalendar(
            @PathVariable Long calendarId
    ) {
        List<CalendarUserResponseDto> response = calendarUserService.getInvitesByCalendar(calendarId);
        return ResponseEntity.ok(new CommonResponse<>("초대 목록 조회 성공", 200, response));
    }
}
