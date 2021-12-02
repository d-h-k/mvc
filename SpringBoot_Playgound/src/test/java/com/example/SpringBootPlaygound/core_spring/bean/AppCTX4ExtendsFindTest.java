package com.example.SpringBootPlaygound.core_spring.bean;

import com.example.SpringBootPlaygound.core_spring.order.discountpolicy.DiscountPolicy;
import com.example.SpringBootPlaygound.core_spring.order.discountpolicy.FixDiscountPolicy;
import com.example.SpringBootPlaygound.core_spring.order.discountpolicy.RateDiscountPolicy;
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
//부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
//그래서 모든 자바 객체의 최고 부모인 Object 타입으로 조회하면, 모든 스프링 빈을 조회한다.

public class AppCTX4ExtendsFindTest {

    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모타입으로 조회시 >> 자식이 둘 이상 있으면 >> 중복 오류가 발생한다")
    void findByParentTypeDupError() {
        DiscountPolicy policyBean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입으로 조회시 >> 자식이 둘 이상 있으면 >> 빈의 이름을 지정해주자")
    void findByParentTypeName() {
        DiscountPolicy policyBean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(policyBean).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("특정 하위 타입으로의 조회")
    void findBeanBySubType() {
        RateDiscountPolicy policyBean = ac.getBean(RateDiscountPolicy.class);
        assertThat(policyBean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllByParentType() {
        Map<String, DiscountPolicy> typeOfBeans = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(typeOfBeans.size()).isEqualTo(2);

        for (String key : typeOfBeans.keySet()) {
            System.out.println("key = " + key + " value=" + typeOfBeans.get(key));
        }
    }


    @Test
    @DisplayName("부모타입으로 모두 조회하기 - Object")
    void findALlByObjType() {
        Map<String, Object> typeOfBeans = ac.getBeansOfType(Object.class);
        assertThat(typeOfBeans.size()).isGreaterThan(2);

        for (String key : typeOfBeans.keySet()) {
            System.out.println("key = " + key + " value=" + typeOfBeans.get(key));
        }
    }


    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }
}
