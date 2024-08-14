package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Task task, Long userId, String timeZone) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        task.setAssignedTo(user);

        ZoneId zoneId = timeZone != null ? ZoneId.of(timeZone) : ZoneId.of(user.getTimezone());
        ZonedDateTime createdAt = ZonedDateTime.now(zoneId);
        task.setCreatedAt(createdAt.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());
        task.setUpdatedAt(task.getCreatedAt());

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));
    }

    public Task updateTask(Long id, Task taskDetails, String timeZone) {
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        ZoneId zoneId = timeZone != null ? ZoneId.of(timeZone) : ZoneId.of(task.getAssignedTo().getTimezone());
        ZonedDateTime updatedAt = ZonedDateTime.now(zoneId);
        task.setUpdatedAt(updatedAt.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
