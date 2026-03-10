package com.taskapp.taskapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import com.taskapp.taskapp.entity.User;
import com.taskapp.taskapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findByDeletedFalse();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: id=" + id));
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: id=" + id));

        if (user.getName() != null) {
            existing.setName(user.getName());
        }
        if (user.getRole() != null) {
            existing.setRole(user.getRole());
        }
        if (user.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(existing);
    }

    public void deleteById(Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: id=" + id));
        existing.setDeleted(true);
        userRepository.save(existing);
    }
}
