package com.example.SpringBootPlaygound.core_spring.bean;

import com.example.SpringBootPlaygound.core_spring.member.entity.Member;
import com.example.SpringBootPlaygound.core_spring.member.repo.MemberRepository;
import com.example.SpringBootPlaygound.core_spring.member.repo.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//todo
//스프링 빈 조회 - 동일한 타입이 둘 이상
//타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자.
//ac.getBeansOfType() 을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.
public class AppCTX3SameBeanFindTest {

    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(SameBeanConfig.class);



    @Test
    @DisplayName("타입으로 조회시 >> 같은 타입이 둘 이상 있으면 중복오류 발생")
    void findByTypeDuplicateError() {
        //DiscountPolicy policyBean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(Member.class));
    }

    @Test
    @DisplayName("타입으로 조회시 >> 같은 타입이 둘 이상이면 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }


    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> typeOfBeans
                = ac.getBeansOfType(MemberRepository.class);

        for (String key : typeOfBeans.keySet()) {
            System.out.println(" - [typeOfBeans] : " + key + " value = " + typeOfBeans.get(key));
        }

        System.out.println("beansOfType = " + typeOfBeans);
        assertThat(typeOfBeans.size()).isEqualTo(2);
    }


    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }
}
