package com.example.todolist.event.entity;

import com.example.todolist.todo.entity.TodoLists;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Agendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agendas_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private String location;

    @OneToMany(mappedBy = "agendas")
    private List<TodoLists> todoLists = new ArrayList<>();
}
