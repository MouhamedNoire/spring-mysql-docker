package com.example.todotask.dtos;
import com.example.todotask.models.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDTO {
    private long id;
    private String description;
    private int timeToComplete;
    private boolean completed;

    public TaskDTO(Task task){
        this.id = task.getId();
        this.description = task.getDescription();
        this.timeToComplete = task.getTimeToComplete();
        this.completed = task.isCompleted();
    }
}