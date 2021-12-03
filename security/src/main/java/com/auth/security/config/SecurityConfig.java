package com.auth.security.config;

import com.auth.security.account.AccountRole;
import com.auth.security.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // configure(HttpSecurity)메서드는 보안되어야 하는 URL 경로와 보안되지 않아야 하는 URL 경로를 정의
        http
                //h2 콘솔을 사용하기위해 한다는데..?
                .csrf()
                .disable()
                .headers().frameOptions().disable()
                .and()


                // 특히 "/"및 "/home" 경로는 인증이 필요하지 않도록 구성됩니다.
                .authorizeRequests()
                //전부허용
                .antMatchers("/",
                             "/home",
                             "/",
                             "/error",
                             "/webjars/**",
                             "/css/**",
                             "/images/**",
                             "/js/**",
                             "/h2-console/**",
                             "/profile")
                .permitAll()
                //user 허용
                .antMatchers("/api/v1/**")
                .hasRole(AccountRole.USER.name())
                //admin 허용
                .antMatchers("/admin/**")
                .hasRole(AccountRole.ADMIN.name())
                //모든 요청에 대해서
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
                .logoutSuccessUrl("/")
                .permitAll()
                .and()


                //oauth 설정
                .oauth2Login()//진입점
                .userInfoEndpoint()//로그인 성공 이후 사용자 정보를 가져올 떄의 설정들
                .userService(customOAuth2UserService);//소셜로그인성공시후속조치
        // 로그인 토큰을 가져오고 나서 후속조치를 처리할 UserService 인터페이스의 impl 을 등록
        // 리소스서버(대기업서비스들)에서 사용자 정보를 가져온 상태에서 추가 진행 기능을명시
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
