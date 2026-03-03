package com.taskapp.taskapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.taskapp.taskapp.entity.Task;
import com.taskapp.taskapp.repository.TaskRepository;

@Service
public class TaskService {
    
    private final TaskRepository  taskRepository;

    // コンストラクタで Repository を受け取る
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 全件取得
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
