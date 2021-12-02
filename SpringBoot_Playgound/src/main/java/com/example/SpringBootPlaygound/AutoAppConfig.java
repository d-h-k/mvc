package com.example.SpringBootPlaygound;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


//85페이지
//컴포넌트 스캔을 사용하려면 먼저 @ComponentScan 을 설정 정보에 붙여주면 된다.
//기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 하나도 없다!
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

)
public class AutoAppConfig {


    //96페이지
    //수동 빈 등록 vs 자동 빈 등록
    //만약 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌되면 어떻게 될까?


    //에러가 발생하는 코드
    /*
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
     */


    //에러 메시지
    /*

    Description:
    The bean 'memoryMemberRepository', defined in class path resource [com/example/SpringBootPlaygound/AutoAppConfig.class], could not be registered.
    A bean with that name has already been defined in file [/Users/dong/Workspace/SpringBoot_Playgound/build/classes/java/main/com/example/SpringBootPlaygound/core_spring/member/repo/MemoryMemberRepository.class]
      and overriding is disabled.

    Action:
    Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
    Process finished with exit code 0

    이 경우 수동 빈 등록이 우선권을 가진다.
    (수동 빈이 자동 빈을 오버라이딩 해버린다.)

    스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
     */
}
