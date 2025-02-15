package com.example.todolist.calendar.repository;

import com.example.todolist.calendar.entity.Calendar;
import com.example.todolist.calendar.entity.CalendarUser;
import com.example.todolist.calendar.entity.InviteStatus;
import com.example.todolist.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarUserRepository extends JpaRepository<CalendarUser, Long> {

    List<CalendarUser> findByUser_Id(Long userId);

    boolean existsByCalendarAndUser(Calendar calendar, User invitedUser);

    List<CalendarUser> findByCalendar(Calendar calendar);

    List<CalendarUser> findByUser_IdAndInviteStatus(Long userId, InviteStatus inviteStatus);
}
