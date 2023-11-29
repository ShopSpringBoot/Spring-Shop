package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    // 오버라이딩을 통해 보안 설정 커스터마이징 가능
    @Bean
    // 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등 설정 작성
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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

        // 시큐리티 처리에 HttpServletRequest 이용
        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                //모든 사용자가 인증없이 해당 경로 접근 설정
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                // /admin시작 경로는 ADMIN Role일때 접근 가능
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                // 위의 경로를 제외한 나머지 경로들은 인증 요구
                .anyRequest().authenticated()
        ;

        http.exceptionHandling()
                // 인증되지 않은 사용자가 리소스에 접근했을 때 수행되는 핸들러 등록
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;
        return http.build();
    }

    @Bean
    // BCryptPasswordEncoder의 해시 함수를 이용한 비밀번호 암호화
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}