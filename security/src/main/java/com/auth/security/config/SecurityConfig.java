package com.auth.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//클래스가 하나 이상의 @Bean 메소드를 선언하고
// 런타임에 해당 빈에 대한 빈 정의 및 서비스 요청을 생성하기 위해 Spring 컨테이너에 의해 처리
// 클래스 부트스트랩 : 부트스트랩(Bootstrap)이란, 일반적으로 한 번 시작되면 알아서 진행되는 일련의 과정, 구성(Config 의미)

@EnableWebSecurity
//스프링 시큐리티의 웹 보안 지원을 활성화하고 스프링 MVC 통합을 제공
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // configure(HttpSecurity)메서드는 보안되어야 하는 URL 경로와 보안되지 않아야 하는 URL 경로를 정의
        http

                // 특히 "/"및 "/home" 경로는 인증이 필요하지 않도록 구성됩니다.
                .authorizeRequests()
                .antMatchers("/", "home")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()

                //로그인페이지는 인증이 필요없음
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()

                //로그아웃 페이지도 인증이 필요없음
                .logout()
                .permitAll();

        ////다른 모든 경로는 인증이 필요함
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
