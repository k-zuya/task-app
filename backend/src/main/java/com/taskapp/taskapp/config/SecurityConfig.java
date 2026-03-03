package com.taskapp.taskapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ① CSRF保護を無効化
                .authorizeHttpRequests(auth -> // ② リクエストの認可ルール
                auth.anyRequest().permitAll() // → 全リクエストを許可

                );
        return http.build();
    }
}
