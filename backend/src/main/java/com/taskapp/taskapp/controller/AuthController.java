package com.taskapp.taskapp.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taskapp.taskapp.service.UserService;
import com.taskapp.taskapp.config.JwtUtil;
import com.taskapp.taskapp.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // コンストラクタインジェクション

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // 1. 認証を実行
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.name(), request.password()));
        // 2. 成功したらメッセージを返す
        return jwtUtil.generateToken(request.name());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userService.register(user);
        return "登録完了";
    }
}
