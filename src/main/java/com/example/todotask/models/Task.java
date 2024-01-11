package com.example.todotask.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="task_id")
    private long id;

    @Column(name="task_description")
    private String description;

    @Column(name="timecomplete")
    private int timeToComplete;

    @Column(name="completed")
    private boolean completed;

}
