package com.example.SpringBootPlaygound.core_spring.bean;

import com.example.SpringBootPlaygound.core_spring.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//todo
// ac.getBeanDefinitionNames() : 이친구가 하는거다

public class AppCTX1InfoTest {
    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기-내가만든")
    void findAllBean_Dong() {
        String[] beans = ac.getBeanDefinitionNames();
        for (String contents : beans) {
            System.out.println(" - [DongDong] : " + contents);
        }
        System.out.println("설명 : 이 프로젝트에서 동작하는 모든 Bean을 출력, 스프링이 내부적으로 사용하는 Bean 포함\n");
    }

    @Test
    @DisplayName("모든 빈 출력하기- 영한님코드")
    void findAllBean_YoungHan() {
        String[] beans = ac.getBeanDefinitionNames();
        for (String content : beans) {
            Object bean = ac.getBean(content);
            System.out.println(" - [YoungHan] : name:" + content + "  /  object:" + bean);
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findAppBean() {
        String[] beanDefNames = ac.getBeanDefinitionNames();
        for (String name : beanDefNames) {
            BeanDefinition definition = ac.getBeanDefinition(name);
            if (definition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(name);
                System.out.println(" - [BeanPrinter] : name:" + name + "  /  object:" +bean);
            }
        }
        System.out.println("설명 : 스프링이 내부적으로 사용하는 Bean 은 제외하고 내가 사용하는 Bean만 출력함\n");
    }
}
