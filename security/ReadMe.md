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

## jasypt (자바 설정파일 간단 암호화) 라이브러리
- Jasypt : Java Simplified Encryption : 자바 코드 암호화 서비스 제공
- DB 패스워드, OAuth Client Secret 등 민감정보를 암호화하기 위해 사용한다.

### 1) Jasypt 사용하기
- 의존성 추가 : `build.gradle` 에 아래 의존성 추가
```groovy
implementation 'com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.3'
```
- 블로그 글 보다보니까 `github.mowedgrass:jasypt` 이 라이브러리로 임포트 하는 경우가 왕왕 있었는데, 내 환경에서는 해당 라이브러리가 Bean 등록에 실패해서 동작하지 않았다.

### 2) 비밀번호 암호화
- 비밀번호 암호화는 여러가지 방법이 있는데 그중 대중적인 두가지
  - 쉘스크립트를 다운로드 받아 로컬에서 암호화하기(안전함)
  - 웹페이지에서 간단하게 암호화하기(편리함) - HTTP 프로토콜 타고 송수신되는 구간은은 어쨋거나 평문으로 주고받기 때문
  - 웹페이지 써보면서 알게된건데, 홈페이지 입력이 POST 여서 서버 어디론가 값을 전송할수도 있고, 자동완성을 지원하기 위해서 쿠키에다 담아놓을수 있으므로 어쨌든 홈페이지는 여러모로 위험한듯 합니다
- 사용목적에서의 Jasypt 가 동작하는 방식은 암호화 복호화에 같은 알고리즘을 사용하는 `대칭키/공개키` 암호화 방식이라서  BF공격이나 Rainbow Table에 털릴수도 있다는 사실을 

#### 2.1) 웹페이지에서 간단하게 암호화
- 아래 링크 에서 암호화하고 싶은 코드의 암호화를 진행 
  - https://www.devglan.com/online-tools/jasypt-online-encryption-decryption
- `Jasypt Online Encryption` 영역에서
   - plain text to Encrypt : 비밀키(암호화하고 싶은 평문)
   - Type of Encryption : 암호화 방식 - Two way 로 선택해야 하는데, 복호화가 가능해야하기 때문 알고리즘은 PBEWithMD5AndDES이다.
   - Secret Key : 암호화에 사용할 키 ,비밀번호
   - Encrypted String : 암호화된 문자열


### 3) 민감정보를 암호화코드로 대체
- 암호화하기 전 코드를 ENC(암호화한 코드)로 로 치환한 뒤 시크릿 키를 환경변수로 셋팅한다
- `application.yml` 파일 수정
```groovy
github.client.secrets=ENC(OQLcoaTQsAdvyxldhkju7xBUa2c8cpTgIKSgM8SipRRMsr7cLHnrwu36AKEZTgTSoE014OG84eo=)
jasypt.encryptor.password=${JASYPT_PASSWORD}
```
- ${JASYPT_PASSWORD} : 암호화에 사용한 시크릿키를 인텔리제이 실행 환경변수로 등록



### 4) Jasypt Configuration 클래스 추가 및 Bean 설정
- 어노테이션 기반으로 `@Value`를 통해 ${jasypt.encryptor.password}로 가져온 enctryptKey 변수(=시크릿키)를 가져옴
- setPassword()의 인자로 시크릿키를 설정하고 setAlgorithm("PBEWithMD5AndDES") 으로 알고리즘 지정 하면서 JasyptStringEncryptor를 Spring Bean 으로 등록한다.
- 복호화 할수 있는 Key 의 흐름은
  - 외부에 공개되지 않고 커밋으로 넘어다니지 않는 쉘 환경변수/IDE 실행변수에 등록하고
  - 스프링 실행될때 환경변수에서 읽어서 .yml 컨피그 파일로 넘어가면
  - 컨피그 파일로부터 자바빈이 코드를 읽어들여서 런타임에 복호화 한다
```java
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}") //복호화를 위해 키를 가져옴
    private String encryptKey;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptKey);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}
```


### 암호화 용어 정리 (salt, iteration counts)
#### salt : 해시에 소금치기
- Salt(소금) : 패스워드를 해쉬하기 전에 랜덤값을 추가하여 키 스페이스를 늘린다
    - salt 는 암호문과 함께 저장
    - 복호화할 때는 암호화된 데이터로부터 salt를 분리해야 함
    - salt 는 매번 다르게 생성되며 동일한 문장에 대해서도 다른 값이 생성됨
    - 그래서 `PBEWithMD5AndDES` 알고리즘으로 동일한 키에 동일한 패스워드를 넣고 여러번 암호화를 눌러도 매번 다른 암호화문자열이 등장하는게 매번 salt 값이 다르기 때문

#### iteration counts (=Key Stretching) : 해시함수 여러번 반복하기
- `iteration counts` 는 해시의 결과값으로 나온 다이제스트를 해쉬알고리즘에 N번 더 넣고 돌리는것
- 돌리는 횟수는 개발자만 알고있지만, 소스코드를 까보면 몇번의 해싱을 거치는지 알아낼수가 있다.
- 그럼에도 불구하고, 최종 다이제스트에서 플레인 텍스트를 얻는데 소모되는 시간과 에너지를 더욱 많이 사용하므로 공격이 어려워진다
- 그래서 `iteration counts` 기법은 브루트포스 공격을 방어하기 위한 기법

#### 해쉬함수의 문제와 해결법
- 문제점 1 : 인식 가능성(recognizability) : 복호화는 불가능하지만, 암호를 알아낼수 있다
    - 메시지가 같으면 다이제스트도 같으므로, 다이제스트(암호화 문자열)에서 원문으로 복호화는 불가능하지만, 알아낼수 있는 방법이 있는데, `rainbow table` 에 미리 해시해둔 패스워드를 잔뜩 가지고 있다가 훔쳐낸 패스워드와 비교하는 것이며 이런 방식을 `rainbow attack` 이라고 한다. 복호화는 불가능하지만, 아무튼 알아낼수는 있는 원리
- 문제점 2 : 속도(speed)가 너무 빨라서 문제
    - 해시 함수의 처리 속도가 빠르기 때문에 무차별 대입 공격(Brute force attack)을 당할 수 있다. 해쉬함수가 가볍고 빠른게 단점이 되는게, 해커 입장에서도 해쉬함수는 가볍기때문에 BF 공격시 시간이 짧게 걸리기 때문

- 해결책 1 : 솔팅(Salting)
    - 고기(Plain text)에 소금(salt)을 더해서 굽는것(Hasing) 처럼 임의의 문자열 salt를 추가해서 해슁하는 기법
    - Salting 의 가장 큰 목적은 salt를 이용해서 rainbow table을 무의미하게 만드는 것이다. 솔팅은 패스워드에다가 임의의 문자열인 salt를 추가하여 다이제스트를 생성하는데, salt는 최소 128bit 정도는 되어야 안전

- 해결책 2 : Key Stretching/iteration counts (키 스트레칭/해시 반복하기)
    - 두 단어가 같은 의미이며 다이제스트를 N번 더 해싱하는 방식
    - 해시를 여러번 반복해 Brute force attack에 대비한다. 보통 사용자가 작성한 패스워드가 짧거나 예측하기 쉬운 경우가 많은데 Key Stretching은 이를 더 어렵게 만들어 준다.

- 참고링크 : `https://d2.naver.com/helloworld/318732`, `https://st-lab.tistory.com/100`


#### PBEWithMD5AndDES 알고리즘
- PBE : 패스워드 기반 암호화(PBE : Password-Based Encryption)
    - 패스워드를 키로 사용하는 방법
    - 키 스페이스가 TripleDES나 Blowfish보다 훨씬 작아 안전하지 못함
    - 사전공격(=BF,RainbowTable) 에 취약
    - 해쉬와 일반적인 대칭키 방식의 암호화를 사용함
    - 미리 패스워드 해쉬 목록을 만들어 놓고 복호화 할 수 있음
    - `salting`와 `iteration counts(=키 스트레칭)` 를 도입하여 보안성 강화

- MD5 : Message-Digest algorithm 5, 128비트 암호화 해시 함수이다. 프로그램이나 파일이 원본 그대로인지를 확인하는 무결성 검사 해쉬기반 암호화 알고리즘
    - 해시함수의 특징은 입력 값의 길이에 상관 없이 고정된 길이의 해시값(다이제스트)이 출력된다. 입력 값의 일부만 변경되더라도 avalanche 효과로 전혀 다른 해시값이 출력된다
    - 해시함수는 단방향(one-way)이므로 복호화를 할 수 없다. 즉, 암호화는 가능하지만 복호화는 불가능하기 때문에 원본을 알 수 없다. 그렇기 때문에 패스워드는 반드시 단방향 해쉬 함수를 이용해야 하는데, 해쉬함수의 문제점은 아래서 후술

- DES : 데이터 암호화 표준(Data Encryption Standard, DES)은 블록 암호의 일종으로, 미국 NBS (National Bureau of Standards, 현재 NIST)에서 국가 표준으로 정한 암호이다. DES는 대칭키 암호이며, 56비트의 키를 사용한다 (위키)


### 암호화 관련참고링크
- [정리가 정말 잘되어있다..](https://velog.io/@haeny01/Jasypt-yaml-파일의-암호화) [..](https://velog.io/@haeny01/Jasypt-yaml-%ED%8C%8C%EC%9D%BC%EC%9D%98-%EC%95%94%ED%98%B8%ED%99%94)
- https://luvstudy.tistory.com/67

--------------------

## 스프링 시큐리티 작업하다가..
- 스프링 시큐리티를 작업하다 실수로 아래 Oauth Key-value를 공개 Repo에 업로드해버렸다..
- git push 엔터를 치자마자 앗차! 싶었는데 이미 업로드 된 뒤였고 최대한 빨리 해결하기 위해서 일단 Oauth 키부터 삭제하고
  - 다행히 구글, 깃헙 두개뿐이 사용중이지 않아서, API 관리자 계정으로 접속해서 기존 키는 모조리 삭제처리하고, 새로운 키를 발급받았습니다 ㅠㅠ.
- 다음 `git reset -Hard HEAD^` + `git push --force` 로 서버의 업로드 기록까지 삭제했다. github Key 일 뿐이라 노출되도 크게 영향은 없겠지만 AWS 키파일 같은거라면 좀 많이 곤란했을꺼같아요.. 

```text
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            clientId:@@@@@ 
            clientSecret:@@@@
            scope: email, profile
          google:
            clientId:@@@@
            clientSecret:@@@@
            scope: email, profile
```
- 이런식의 평문으로 clientSecret을 노출해버렸습니다 ;;

### 왜 그랬을까...??
- 일단, 해당 레포가 각잡고 개발하던 레포가 아니고 기능 테스트를 위해서 간단히 만들어본 레포인데요, 정석대로라면 암호화를 하던가, 파일을 나눠놓고 gitignore파일에 등록하던가 해야하는데, 그러지 않고 그냥 커밋할때 키 파일 부분만 요리조리 피해서 커밋하는 방식으로 관리해왔는데
- 이게 개발하다가 컨디션이 안좋은 날 + 점심먹고와서 피곤한 상태가 중첩되서 이런 일이 벌어졌습니다.. 
- clientSecret 같은 외부에 노출되어서는 안되는 Key값이 `암호화 되지 않은` 상태로  `git add` 혹은 `git commit` 가 가능한 범위인것 자체가 문제있는 상태라는걸 이제서야 배웠습니다.
- 스스로 "알아서 잘하겠지" 라는 생각으로 요리조리 피해서 커밋하는 방식은 마치 `우리팀은 수비가 튼튼하니 골키퍼도 공격에 참여하도록` 작전을 세우는 축구감독과 같습니다. ㅠㅠ..!



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
