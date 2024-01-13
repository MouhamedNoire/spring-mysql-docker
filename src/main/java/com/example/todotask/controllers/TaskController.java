package com.example.todotask.controllers;

import com.example.todotask.dtos.TaskDTO;
import com.example.todotask.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getallTasks(){
        return taskService.getAllTasksDTOs();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskDTO(@PathVariable long id){
        return taskService.getTaskDTOById(id);
    }

    @PostMapping()
    public ResponseEntity<Void> saveTask(@RequestParam TaskDTO taskDTO){
        if (taskDTO!=null){
            taskService.saveTaskDTO(taskDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable long id, @RequestParam TaskDTO taskDTO){
        if (taskDTO!=null){
            taskService.updateTaskDTO(id, taskDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id){
        if (taskService.deleteTaskDTO(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}