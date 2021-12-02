package com.example.SpringBootPlaygound.core_spring.singleton;

import com.example.SpringBootPlaygound.core_spring.AppConfig;
import com.example.SpringBootPlaygound.core_spring.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

//3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워
//드로 객체 인스턴스가 생성되는 것을 막는다

//private으로 new 키워드를 막아두었다.
//호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.
//> 참고: 싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해두는 가장 단순하고 안
//전한 방법을 선택했다.
//싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유
//해서 효율적으로 사용할 수 있다. 하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다.

/*
싱글톤 패턴 문제점
싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
테스트하기 어렵다.
내부 속성을 변경하거나 초기화 하기 어렵다.
private 생성자로 자식 클래스를 만들기 어렵다.
결론적으로 유연성이 떨어진다.
안티패턴으로 불리기도 한다.



 */

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1.조회 호출할 떄마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2.조회 호출할 떄마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);

    }


    @Test
    @DisplayName("싱글톤 패턴이 적용된 객체 사용")
    void singletonServiceTest() {

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isEqualTo(singletonService2);
        assertThat(singletonService1).isSameAs(singletonService2);
        singletonService1.logic();
        System.out.println("뽀인트 : singletonService1 && 2가 완전히 동일한 인스턴스라는거에 주목 ");
    }

    //@ todo : 싱글톤은 사실 문제가 있다 안티패턴에 가까운 단점들이 있다!!
    // 71페이지
    // 문제점을 극복하고서라도 쓸려면 당신이 스프링 소스코드만큼 잘짜야됨
    // 일반적인 프로그래머들한테는 싱글톤패턴이 안티패턴이라고 할수 있다


    @Test // 72p
    @DisplayName("스프링 컨테이너와 싱글톤패턴 - 스프링 컨테이너를 사용하는 테스트코드")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        //1
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        //2
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //3
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //4
        assertThat(memberService1).isEqualTo(memberService2);
        assertThat(memberService1).isSameAs(memberService2);
        System.out.println("");
        //스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유
        //해서 효율적으로 재사용할 수 있다.
        //> 참고: 스프링의 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 것은 아니다. 요청할 때 마다 새
        //로운 객체를 생성해서 반환하는 기능도 제공한다. 자세한 내용은 뒤에 빈 스코프에서 설명하겠다
    }





}
