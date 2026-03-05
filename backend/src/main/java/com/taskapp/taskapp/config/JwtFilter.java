package com.taskapp.taskapp.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // ① Authorization ヘッダーを取得
        String header = request.getHeader("Authorization");

        // ② "Bearer xxx" 形式ならトークン部分を取り出して検証
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // "Bearer " の後ろを取得
            String username = jwtUtil.validateToken(token);

            // ③ Spring Security に「この人は認証済み」と教える
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(username, null, List.of());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // ④ 次のフィルターに処理を渡す（これがないとリクエストが止まる）
        filterChain.doFilter(request, response);
    }
}
