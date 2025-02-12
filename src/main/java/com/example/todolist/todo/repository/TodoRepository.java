package com.example.todolist.todo.repository;


import com.example.todolist.todo.entity.TodoLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository <TodoLists, Long> {

    List<TodoLists> findByCalendarId(Long calendarId);
}
