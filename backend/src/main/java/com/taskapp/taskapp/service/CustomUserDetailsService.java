package com.taskapp.taskapp.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskapp.taskapp.entity.User;
import com.taskapp.taskapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // コンストラクタインジェクション
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) { // ← コンストラクタ
        this.userRepository = userRepository; // ← 代入
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }

}
