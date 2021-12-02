package com.example.SpringBootPlaygound.core_spring.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


        StatefulService service1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService service2 = ac.getBean("statefulService", StatefulService.class);

        service1.order("Andante", 10000);
        service2.order("Boradole", 27500);

        int price1 = service1.getPrice();
        int price2 = service2.getPrice();
        System.out.println("price1 = " + price1);
        assertThat(service1.getPrice()).isNotEqualTo(service2.getPrice());
        System.out.println("두 숫자가 달라야하는데 무조건 동일하게 나오므로 실패 >> 상태를 저장하는 싱글톤은 버그의 가능성이 있다!!");

    }
    /*
    
    실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터진다.(몇년에 한번씩 꼭만난다
    진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자.

    */


    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}

