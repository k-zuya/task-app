package com.taskapp.taskapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskapp.taskapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDeletedFalse();

    Optional<Task> findByIdAndDeletedFalse(Long id);
}
