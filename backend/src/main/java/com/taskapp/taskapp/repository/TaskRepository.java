package com.taskapp.taskapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.taskapp.taskapp.entity.Task;

public interface  TaskRepository extends JpaRepository<Task, Long> {
    
}
