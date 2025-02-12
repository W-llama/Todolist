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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final UserRepository userRepository;

    private final CalendarUserRepository calendarUserRepository;

    @Transactional
    public CalendarResponseDto createCalendar(CalendarRequestDto calendarRequestDto,  UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        Calendar calendar = Calendar.builder()
                .title(calendarRequestDto.getTitle())
                .description(calendarRequestDto.getDescription())
                .startDate(calendarRequestDto.getStartDate())
                .endDate(calendarRequestDto.getEndDate())
                .location(calendarRequestDto.getLocation())
                .build();

        calendarRepository.save(calendar);

        CalendarUser calendarUser = CalendarUser.builder()
                .calendar(calendar)
                .user(user)
                .build();

        calendarUserRepository.save(calendarUser);

        return CalendarResponseDto.builder()
                .title(calendar.getTitle())
                .description(calendar.getDescription())
                .startDate(calendar.getStartDate())
                .endDate(calendar.getEndDate())
                .location(calendar.getLocation())
                .build();
    }

    @Transactional
    public List<CalendarResponseDto> getCalendar() {
        List<Calendar> calendars = calendarRepository.findAll();

        return calendars.stream()
                .map(calendar -> CalendarResponseDto.builder()
                        .id(calendar.getId())
                        .title(calendar.getTitle())
                        .description(calendar.getDescription())
                        .location(calendar.getLocation())
                        .startDate(calendar.getStartDate())
                        .endDate(calendar.getEndDate())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public CalendarResponseDto getCalendarById(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CALENDAR));

        return CalendarResponseDto.builder()
                .id(calendar.getId())  // ID 추가
                .title(calendar.getTitle())
                .description(calendar.getDescription())
                .location(calendar.getLocation())
                .startDate(calendar.getStartDate())
                .endDate(calendar.getEndDate())
                .build();
    }


}
