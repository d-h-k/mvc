package com.example.SpringBootPlaygound.core_spring.scan;

import com.example.SpringBootPlaygound.AutoAppConfig;
import com.example.SpringBootPlaygound.core_spring.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        //AnnotationConfigApplicationContext 를 사용하는 것은 기존과 동일하다.
        //설정 정보로 AutoAppConfig 클래스를 넘겨준다.
        //실행해보면 기존과 같이 잘 동작하는 것을 확인할 수 있다.
    }
    //로그를 잘 보면 컴포넌트 스캔이 잘 동작하는 것을 확인할 수 있다.
    //ClassPathBeanDefinitionScanner - Identified candidate component class:
    //.. RateDiscountPolicy.class
    //.. MemberServiceImpl.class
    //.. MemoryMemberRepository.class
    //.. OrderServiceImpl.class
}


//todo : 87,88p
/* 확인해야 하는 로그

14:25:26.048 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [/Users/dong/Workspace/SpringBoot_Playgound/build/classes/java/main/com/example/SpringBootPlaygound/bean_validator/PersonController.class]
14:25:26.049 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [/Users/dong/Workspace/SpringBoot_Playgound/build/classes/java/main/com/example/SpringBootPlaygound/core_spring/member/repo/MemoryMemberRepository.class]
14:25:26.051 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [/Users/dong/Workspace/SpringBoot_Playgound/build/classes/java/main/com/example/SpringBootPlaygound/core_spring/member/service/MemberServiceImp.class]
14:25:26.051 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [/Users/dong/Workspace/SpringBoot_Playgound/build/classes/java/main/com/example/SpringBootPlaygound/core_spring/order/OrderServiceImp.class]
14:25:26.052 [Test worker] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [/Users/dong/Workspace/SpringBoot_Playgound/build/classes/java/main/com/example/SpringBootPlaygound/core_spring/order/discountpolicy/RateDiscountPolicy.class]

 */
