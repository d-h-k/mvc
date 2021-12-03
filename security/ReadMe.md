# 인덱싱

- 스프링 시큐리티 아키텍쳐
- 스프링 공식 가이드문서 : Securing a Web Application



# 요구사항
- 모든 인증은 AJAX 요청기반
- HS-256 알고리즘 암호화된 JWT로 리턴
- Controller 에 Method Security 
- 소셜 로그인 서비스 제공사(네이버,카카오,구글,깃헙) 로그인 기초 작업
- FE 인증 플로우는 implicit Grant Flow를 따름


------------------

# 스프링 시큐리티 아키텍쳐
- https://spring.io/guides/topicals/spring-security-architecture/

## 인증과 인가
인증 및 액세스 제어
- 애플리케이션 보안은 인증(당신은 누구입니까)과 인가(권한부여/할수있는 일이 뭔가요) 두 가지 
- Spring Security에는 인증과 권한 부여를 분리하도록 설계된 아키텍처가 둘 모두에 대한 전략과 확장점이 있습니다

## Auth (인증/Authentication) 
- 인증을 strategy 인터페이스는 다음 AuthenticationManager과 같은 한 가지 방법만 있습니다.
- 전략 패턴(strategy pattern) 은 실행 중에 알고리즘을 선택할 수 있게 하는 행위 소프트웨어 디자인 패턴이며, 특정한 계열의 알고리즘들을 정의하고, 각 알고리즘을 캡슐화하며, 여러 알고리즘들을 서로 교체가 가능하게 만드는 패턴

```java
public interface AuthenticationManager {
    Authentication authenticate(Authentication authentication)
    throws AuthenticationException;
}
```
- `AuthenticationManager`은 `authenticate()`메서드 에서 3가지 중 하나를 수행
  - 1] Return(리턴) : 입력이 유효한 주체를 나타내는 것을 확인할 수있는 경우 객체를 반환
  - 2] throw : 입력이 유효하지 않은 보안 주체를 나타낸다고 판단되면 `AuthenticationException` 을 throw
  - 3] null : 결정할 수 없는 경우

가장 일반적으로 사용되는 구현 AuthenticationManager인 ProviderManager의 사슬에있는 대표, AuthenticationProvider인스턴스. An AuthenticationProvider은 약간 비슷 AuthenticationManager하지만 호출자가 주어진 Authentication유형을 지원하는지 여부를 쿼리할 수 있도록 하는 추가 메서드가 있습니다 .

public interface AuthenticationProvider {

	Authentication authenticate(Authentication authentication)
			throws AuthenticationException;

	boolean supports(Class<?> authentication);
}
메서드 의 Class<?>인수 supports()는 실제로입니다 Class<? extends Authentication>( authenticate()메소드에 전달되는 항목을 지원하는지 여부만 묻습니다 ). A ProviderManager는 의 체인에 위임하여 동일한 애플리케이션에서 여러 다른 인증 메커니즘을 지원할 수 있습니다 AuthenticationProviders. A는 경우 ProviderManager특정 인식하지 않는 Authentication인스턴스 유형을, 그것은 생략됩니다.

A ProviderManager는 선택적인 부모를 가지며 모든 제공자가 를 반환하면 참조할 수 있습니다 null. 상위 항목을 사용할 수 없는 경우 null Authentication결과는 AuthenticationException.

경우에 따라 애플리케이션에는 보호된 리소스의 논리적 그룹(예: 경로 패턴과 일치하는 모든 웹 리소스 /api/**)이 있고 각 그룹에는 고유한 전용 AuthenticationManager. 종종 각각은 이며 ProviderManager부모를 공유합니다. 그러면 부모는 일종의 "전역" 리소스가 되어 모든 제공자에 대한 대체 역할을 합니다.

## 참고링크
- https://spring.io/guides/topicals/spring-security-architecture/
- 


------------------


# Securing a Web Application
- 출처 : https://spring.io/guides/gs/securing-web/
