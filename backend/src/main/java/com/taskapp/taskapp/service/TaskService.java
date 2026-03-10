package com.taskapp.taskapp.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.taskapp.taskapp.entity.Task;
import com.taskapp.taskapp.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findByDeletedFalse();
    }

    public Task findById(Long id) {
        return taskRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません: id=" + id));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません: id=" + id));
        existing.setDeleted(true);
        taskRepository.save(existing);
    }

    public Task update(Long id, Task task) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません: id=" + id));

        if (task.getTitle() != null) {
            existing.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            existing.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            existing.setStatus(task.getStatus());
        }
        if (task.getPriority() != null) {
            existing.setPriority(task.getPriority());
        }
        if (task.getCategory() != null) {
            existing.setCategory(task.getCategory());
        }
        if (task.getDueDate() != null) {
            existing.setDueDate(task.getDueDate());
        }
        if (task.getAssigneeId() != null) {
            existing.setAssigneeId(task.getAssigneeId());
        }
        return taskRepository.save(existing);
    }
}
