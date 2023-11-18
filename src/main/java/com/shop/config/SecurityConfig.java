package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    // 오버라이딩을 통해 보안 설정 커스터마이징 가능
    @Override
    // 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등 설정 작성
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 로그인 페이지 URL 설정
                .loginPage("/members/login")
                // 로그인 성공 시 이동할 URL 설정
                .defaultSuccessUrl("/")
                // 로그인 시 사용할 파라미터 이름을 email로 설정
                .usernameParameter("email")
                // 로그인 실패 시 이동할 URL 설정
                .failureUrl("/members/login/error")
                .and()
                .logout()
                // 로그아웃 URL 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                // 로그아웃 성공 시 이동할 URL 설정
                .logoutSuccessUrl("/")
                ;
    }

    @Bean
    // BCryptPasswordEncoder의 해시 함수를 이용한 비밀번호 암호화
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    // AuthenticationManager 생성
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // userDetailService를 구현하는 memberService 지정
        auth.userDetailsService(memberService)
                // passwordEncoder 지정
                .passwordEncoder(passwordEncoder());
    }
}
