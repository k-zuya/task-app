package com.taskapp.taskapp.repository;
import com.taskapp.taskapp.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
    
    public Optional<User> findByName  (String name);
}
