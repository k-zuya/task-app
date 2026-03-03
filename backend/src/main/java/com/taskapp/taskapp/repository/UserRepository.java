package com.taskapp.taskapp.repository;
import com.taskapp.taskapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
    
}
