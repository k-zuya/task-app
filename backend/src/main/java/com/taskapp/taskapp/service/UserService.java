package com.taskapp.taskapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    // 全件取得
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // ID検索
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // 作成・更新
    public User save(User user) {
        return userRepository.save(user);
    }

    // 削除
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
