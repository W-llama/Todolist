package com.example.todolist.calendar.repository;

import com.example.todolist.calendar.entity.CalendarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarUserRepository extends JpaRepository<CalendarUser, Long> {
}
