package com.taskapp.taskapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.taskapp.taskapp.entity.User;
import com.taskapp.taskapp.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository  userRepository;

    // コンストラクタで Repository を受け取る
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
