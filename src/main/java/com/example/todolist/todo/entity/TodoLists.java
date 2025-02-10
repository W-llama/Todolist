package com.example.todolist.todo.entity;

import com.example.todolist.event.entity.Agendas;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class TodoLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_list_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime dueTime;

    @Column
    private String priority;

    @ManyToOne
    private Agendas agendas;

}
