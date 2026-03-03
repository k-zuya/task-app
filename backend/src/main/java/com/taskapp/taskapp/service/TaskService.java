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
        return taskRepository.findAll();
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
        taskRepository.deleteById(id);
    }
}
