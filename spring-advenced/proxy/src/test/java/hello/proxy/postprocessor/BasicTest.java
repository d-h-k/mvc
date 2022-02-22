package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    @DisplayName("기본처리")
    public void basicCofing() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    }

    @Slf4j
    @Configuration
    static class A {
        @Bean(name = "beanA")
        public void helloA() {
            log.info("hello A");
        }
    }
    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
