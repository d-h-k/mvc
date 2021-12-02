package com.example.SpringBootPlaygound.core_spring.singleton;

import com.example.SpringBootPlaygound.core_spring.AppConfig;
import com.example.SpringBootPlaygound.core_spring.member.repo.MemberRepository;
import com.example.SpringBootPlaygound.core_spring.member.service.MemberServiceImp;
import com.example.SpringBootPlaygound.core_spring.order.OrderServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test //80p
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImp memberService = ac.getBean("memberService", MemberServiceImp.class);
        OrderServiceImp orderService = ac.getBean("orderService", OrderServiceImp.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //모두 같은 인스턴스를 참고하고 있다.
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        /*
        확인해보면 memberRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다.
        AppConfig의 자바 코드를 보면 분명히 각각 2번 new MemoryMemberRepository 호출해서 다른 인스
        턴스가 생성되어야 하는데?
        어떻게 된 일일까? 혹시 두 번 호출이 안되는 것일까? 실험을 통해 알아보자
         */
    }


}
