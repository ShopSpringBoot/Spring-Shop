package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 오버라이딩을 통해 보안 설정 커스터마이징 가능

    @Override
    // 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등 설정 작성
    protected void configure(HttpSecurity http) throws Exception {

    }


    @Bean
    // BCryptPasswordEncoder의 해시 함수를 이용한 비밀번호 암호화
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
