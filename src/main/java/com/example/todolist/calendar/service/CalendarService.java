package com.example.todolist.calendar.service;

import com.example.todolist.auth.UserDetailsImpl;
import com.example.todolist.calendar.dto.CalendarRequestDto;
import com.example.todolist.calendar.dto.CalendarResponseDto;
import com.example.todolist.calendar.entity.Calendar;
import com.example.todolist.calendar.entity.CalendarUser;
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

        CalendarUser calendarUser = new CalendarUser(calendar, user);
        calendarUserRepository.save(calendarUser);

        return new CalendarResponseDto(calendar);
    }

    @Transactional
    public List<CalendarResponseDto> getCalendar() {
        List<Calendar> calendars = calendarRepository.findAll();

        return calendars.stream()
                .map(CalendarResponseDto::new)
                .toList();
    }

    @Transactional
    public CalendarResponseDto getCalendarById(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

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
