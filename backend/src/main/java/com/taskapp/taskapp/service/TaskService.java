package com.taskapp.taskapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.taskapp.taskapp.entity.Task;
import com.taskapp.taskapp.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // コンストラクタで Repository を受け取る
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 全件取得
    public List<Task> findAll() {
        return taskRepository.findByDeletedFalse();
    }

    // ID検索
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    // 作成・更新
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    // 削除
    public void deleteById(Long id) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません"));
        existing.setDeleted(true);
        taskRepository.save(existing);
    }

    public Task update(Long id, Task task) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません"));

        // 送られてきた値がnullでなければ更新
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
