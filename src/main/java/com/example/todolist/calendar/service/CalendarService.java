package com.example.todolist.calendar.service;

import com.example.todolist.auth.UserDetailsImpl;
import com.example.todolist.calendar.dto.CalendarRequestDto;
import com.example.todolist.calendar.dto.CalendarResponseDto;
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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;
    private final CalendarUserRepository calendarUserRepository;

    @Transactional
    public CalendarResponseDto createCalendar(CalendarRequestDto calendarRequestDto, UserDetailsImpl userDetails) {

        User user = userRepository.findById(userDetails.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USERNAME));

        Calendar calendar = Calendar.builder()
                .title(calendarRequestDto.getTitle())
                .description(calendarRequestDto.getDescription())
                .startDate(calendarRequestDto.getStartDate())
                .endDate(calendarRequestDto.getEndDate())
                .location(calendarRequestDto.getLocation())
                .build();

        calendarRepository.save(calendar);

        CalendarUser calendarUser = new CalendarUser(calendar, user, InviteStatus.ACCEPTED);
        calendarUserRepository.save(calendarUser);

        return new CalendarResponseDto(calendar);
    }

    @Transactional
    public List<CalendarResponseDto> getCalendar(Long userId) {
        List<CalendarUser> ownedCalendars = calendarUserRepository.findByUser_Id(userId);

        List<CalendarUser> acceptedInvites = calendarUserRepository.findByUser_IdAndInviteStatus(userId, InviteStatus.ACCEPTED);

        List<CalendarUser> pendingInvites = calendarUserRepository.findByUser_IdAndInviteStatus(userId, InviteStatus.PENDING);

        return Stream.concat(
                        Stream.concat(
                                ownedCalendars.stream(),
                                acceptedInvites.stream()
                        ),
                        pendingInvites.stream()
                )
                .distinct()
                .map(calendarUser -> new CalendarResponseDto(calendarUser.getCalendar(), calendarUser.getInviteStatus()))
                .toList();
    }

    @Transactional
    public CalendarResponseDto getCalendarById(Long id, Long userId) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

        boolean isUserAuthorized = calendar.getCalendarUsers().stream()
                .anyMatch(calendarUser -> calendarUser.getUser().getId().equals(userId) &&
                        calendarUser.getInviteStatus() == InviteStatus.ACCEPTED);

        if (!isUserAuthorized) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        return new CalendarResponseDto(calendar);
    }

    @Transactional
    public CalendarResponseDto updateCalendarById(CalendarRequestDto calendarRequestDto, UserDetailsImpl userDetails, Long id) {

        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

        boolean isUserInCalendar = calendar.getCalendarUsers().stream()
                .anyMatch(calendarUser -> calendarUser.getUser().getId().equals(userDetails.getUserId()));

        if (!isUserInCalendar) {
            throw new CustomException(ErrorCode.NO_CALENDAR_UPDATE_PERMISSION);
        }

        calendar.updateCalendar(calendarRequestDto);

        return new CalendarResponseDto(calendar);
    }

    @Transactional
    public CalendarResponseDto deleteCalendarById(UserDetailsImpl userDetails, Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

        boolean isUserInCalendar = calendar.getCalendarUsers().stream()
                .anyMatch(calendarUser -> calendarUser.getUser().getId().equals(userDetails.getUserId()));

        if (!isUserInCalendar) {
            throw new CustomException(ErrorCode.NO_CALENDAR_DELETE_PERMISSION);
        }

        calendarRepository.delete(calendar);

        return new CalendarResponseDto(calendar);
    }
}
