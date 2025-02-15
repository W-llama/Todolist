package com.example.todolist.calendar.service;

import com.example.todolist.calendar.dto.CalendarUserResponseDto;
import com.example.todolist.calendar.entity.Calendar;
import com.example.todolist.calendar.entity.CalendarUser;
import com.example.todolist.calendar.entity.InviteStatus;
import com.example.todolist.calendar.repository.CalendarRepository;
import com.example.todolist.calendar.repository.CalendarUserRepository;
import com.example.todolist.global.excetion.CustomException;
import com.example.todolist.global.excetion.ErrorCode;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarUserService {

    private final CalendarUserRepository calendarUserRepository;
    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;

    @Transactional
    public CalendarUserResponseDto inviteUserToCalendar(Long calendarId, String username) {
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

        User invitedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USERNAME));

        if (calendarUserRepository.existsByCalendarAndUser(calendar, invitedUser)) {
            throw new CustomException(ErrorCode.IS_EXISTS);
        }

        CalendarUser invite = new CalendarUser(calendar, invitedUser, InviteStatus.PENDING);
        CalendarUser savedInvite = calendarUserRepository.save(invite);

        return CalendarUserResponseDto.builder()
                .id(savedInvite.getId())
                .calendarId(savedInvite.getCalendar().getId())
                .userId(invitedUser.getId())
                .status(savedInvite.getInviteStatus())
                .build();
    }

    @Transactional
    public CalendarUserResponseDto respondToInvite(Long inviteId, InviteStatus status) {
        CalendarUser invite = calendarUserRepository.findById(inviteId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INVITE));

        if (invite.getInviteStatus() != InviteStatus.PENDING) {
            throw new CustomException(ErrorCode.ALREADY_RESPONDED);
        }

        invite.updateInviteStatus(status);

        return CalendarUserResponseDto.builder()
                .id(invite.getId())
                .calendarId(invite.getCalendar().getId())
                .userId(invite.getUser().getId())
                .status(invite.getInviteStatus())
                .build();
    }

    @Transactional(readOnly = true)
    public List<CalendarUserResponseDto> getInvitesByCalendar(Long calendarId) {
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

        List<CalendarUser> invites = calendarUserRepository.findByCalendar(calendar);

        return invites.stream()
                .map(invite -> CalendarUserResponseDto.builder()
                        .id(invite.getId())
                        .calendarId(calendarId)
                        .userId(invite.getUser().getId())
                        .status(invite.getInviteStatus())
                        .build())
                .toList();
    }
}
