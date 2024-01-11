package com.example.todotask.services;
import com.example.todotask.dtos.TaskDTO;
import com.example.todotask.models.Task;
import com.example.todotask.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<TaskDTO> getAllTasksDTOs(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TaskDTO getTaskDTOById(long id){
        Task task = taskRepository.findById(id).orElseThrow();
        return (task!=null) ? convertToDTO(task) : null;
    }

    public TaskDTO saveTaskDTO(TaskDTO taskDTO){
        Task task = convertToEntity(taskDTO);
        task = taskRepository.save(task);
        return convertToDTO(task);

    }

    public TaskDTO updateTaskDTO(Long id, TaskDTO updatedTaskDTO){
        Task existingTask = taskRepository.findById(id).orElse(null);
        if(existingTask != null){
            existingTask.setDescription(updatedTaskDTO.getDescription());
            existingTask.setTimeToComplete(updatedTaskDTO.getTimeToComplete());
            existingTask.setCompleted(updatedTaskDTO.isCompleted());
            Task updatedTask = taskRepository.save(existingTask);
            return convertToDTO(updatedTask);
        }
        return null;
    }

    public boolean deleteTaskDTO(Long id){
        Task existingTask = taskRepository.findById(id).orElse(null);
        if(existingTask != null){
            taskRepository.delete(existingTask);
            return true;
        }
        return false;
    }

    //mapper
    public TaskDTO convertToDTO(Task task){
        return new TaskDTO(task);
    }

    public Task convertToEntity(TaskDTO taskDTO){
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setDescription(taskDTO.getDescription());
        task.setTimeToComplete(taskDTO.getTimeToComplete());
        task.setCompleted(taskDTO.isCompleted());
        return task;
    }
}
