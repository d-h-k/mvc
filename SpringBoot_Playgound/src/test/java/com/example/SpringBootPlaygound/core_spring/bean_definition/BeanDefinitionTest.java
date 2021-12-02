package com.example.SpringBootPlaygound.core_spring.bean_definition;

import com.example.SpringBootPlaygound.core_spring.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 66~67 페이지
//정리
//BeanDefinition을 직접 생성해서 스프링 컨테이너에 등록할 수 도 있다. 하지만 실무에서
//BeanDefinition을 직접 정의하거나 사용할 일은 거의 없다. 어려우면 그냥 넘어가면 된다^^!
//BeanDefinition에 대해서는 너무 깊이있게 이해하기 보다는, 스프링이 다양한 형태의 설정 정보를
//BeanDefinition으로 추상화해서 사용하는 것 정도만 이해하면 된다.
//가끔 스프링 코드나 스프링 관련 오픈 소스의 코드를 볼 때, BeanDefinition 이라는 것이 보일 때가 있다. 이때 이러한 메커니즘을 떠올리면 된다.
// 어려우면 그냥 이런게 있나보다..


//출력결과
/*

beanDefinitionName() : appConfig  /  beanDefinition : Generic bean: class [com.example.SpringBootPlaygound.core_spring.AppConfig$$EnhancerBySpringCGLIB$$4b4de53a]; scope=singleton; abstract=false; lazyInit=null; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
beanDefinitionName() : memberService  /  beanDefinition : Root bean: class [null]; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=appConfig; factoryMethodName=memberService; initMethodName=null; destroyMethodName=(inferred); defined in com.example.SpringBootPlaygound.core_spring.AppConfig
beanDefinitionName() : orderService  /  beanDefinition : Root bean: class [null]; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=appConfig; factoryMethodName=orderService; initMethodName=null; destroyMethodName=(inferred); defined in com.example.SpringBootPlaygound.core_spring.AppConfig
beanDefinitionName() : memberRepository  /  beanDefinition : Root bean: class [null]; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=appConfig; factoryMethodName=memberRepository; initMethodName=null; destroyMethodName=(inferred); defined in com.example.SpringBootPlaygound.core_spring.AppConfig
beanDefinitionName() : discountPolicy  /  beanDefinition : Root bean: class [null]; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=appConfig; factoryMethodName=discountPolicy; initMethodName=null; destroyMethodName=(inferred); defined in com.example.SpringBootPlaygound.core_spring.AppConfig

 */

// 애플리케이션 컨텍스트는  getBeanDefinition 을 못받아옴
// 스프링 사용자 입장에서는 빈 정의 정보를 가져올일이 없긴 하다!
// 반면 xml은 다르다

// 두가지
// 1.직접 스프링빈 스프링빈에 등록
// 2. 우회로 팩터리메서드 : AppConfig라는 팩토리 메서드를 통해서 이 메서드를 통해서 제공해주는 방식 >> 외부에서 호출해서 어쩌고


//팩토리 메서드방식
// factoryBeanName=appConfig; factoryMethodName=orderService; initMethodName=null;  팩토리메서드


// 스프링은  BeanDefinition이라는 걸로 스프링의 설정 메타정보를 추상화 한다
// 스프링빈 만드는건 두가지방법(직접등록/팩토리빈을 통해 등록), 자바컨피그는 팩토리빈 방식이다!
public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefNames = ac.getBeanDefinitionNames();
        for (String beanName : beanDefNames) {
            BeanDefinition definition = ac.getBeanDefinition(beanName);

            if(definition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.printf("beanDefinitionName() : %s  /  beanDefinition : %s \r\n",
                        beanName,definition);
            }
        }
    }
}
