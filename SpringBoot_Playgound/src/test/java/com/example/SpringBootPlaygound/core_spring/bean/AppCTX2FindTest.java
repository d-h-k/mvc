package com.example.SpringBootPlaygound.core_spring.bean;

import com.example.SpringBootPlaygound.core_spring.AppConfig;
import com.example.SpringBootPlaygound.core_spring.member.service.MemberService;
import com.example.SpringBootPlaygound.core_spring.member.service.MemberServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


// todo
//스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
//ac.getBean(빈이름, 타입)
//ac.getBean(타입)
//조회 대상 스프링 빈이 없으면 예외 발생
//NoSuchBeanDefinitionException: No bean named 'xxxxx' available
//참고로 구체 타입으로 조회하면 변경시 유연성이 떨어진다.

public class AppCTX2FindTest {
    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름으로 조회")
    void findByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImp.class);

    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("구체적인 타입으로 조회")
    void findByType2Specific() {
        MemberServiceImp memberService = ac.getBean("memberService", MemberServiceImp.class);
        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findByNameX() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
    }



}
