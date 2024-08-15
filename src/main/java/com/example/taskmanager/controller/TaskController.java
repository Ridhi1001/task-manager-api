package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

@PostMapping
public Task createTask(@RequestBody Map<String, Object> payload) {
    Long userId = Long.valueOf(payload.get("assignedTo").toString());
    String timeZone = (String) payload.get("timeZone");

    Task task = new Task();
    task.setTitle((String) payload.get("title"));
    task.setDescription((String) payload.get("description"));
    task.setStatus((String) payload.get("status"));

    return taskService.createTask(task, userId, timeZone);
}


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task taskDetails,
                           @RequestParam(required = false) String timeZone) {
        return taskService.updateTask(id, taskDetails, timeZone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
