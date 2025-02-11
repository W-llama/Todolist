package com.example.todolist.calendar.service;

import com.example.todolist.auth.UserDetailsImpl;
import com.example.todolist.calendar.dto.CalendarRequestDto;
import com.example.todolist.calendar.dto.CalendarResponseDto;
import com.example.todolist.calendar.entity.Calendar;
import com.example.todolist.calendar.entity.CalendarUser;
import com.example.todolist.calendar.repository.CalendarRepository;
import com.example.todolist.calendar.repository.CalendarUserRepository;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalendarUserRepository calendarUserRepository;

    @Transactional
    public CalendarResponseDto makecalendar(CalendarRequestDto calendarRequestDto,  UserDetailsImpl userDetails) {

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
}
