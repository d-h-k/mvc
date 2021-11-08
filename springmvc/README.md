# MVC

- hi there

## mvc1편 느낀점
- 고민을 정말 많이 하신거같다. 어떻게 접근해야 다들 공감하고 잘 이해할수 있을지에 대한 고민이 녹아있는강의라 감동이고 너무 좋았다.
- 자료구조를 처음 공부할때 C언어로 직접 자료구조를 구현하면서 공부했는데, 딱 처음 자료구조 공부하던 방식으로 배우니까 SpringMVC 가 이렇게 구현될수밖에 없는 이유와 특징들이 자연스럽게 느낄수 

### 이미 알고계신거고, 충분히 고려하셨겠지만 
- http파일로 만들어봤는데, postman 쓰거나 브라우저에 들어가지 않을 수 있어서 뭐랄까.. 내 머리속의 Context가 Switching 되지 않고 강의 흐름 쭉 따라갈 수 있어서 좋은거같아서 한번 말씀드려봅니다!
- 대략 내 깃헙주소


## 특징
최종 결과물을 대상으로, 이에 대한 설명이 아니라
왜 그런 결과물로 만들었는지 만들수밖어 없던 이유를 이해할수 있도록
발전과정과 함께 만들어가고, 그다음 최종 결과물의 편리한 기능까지 소개시켜주는 강의라 감동적이였습니다

## 일어날 일은 일어난다


----- 이번주

## 매핑 컨트롤러의 반환형식 (@RestController VS @Controller)
- @RestController 와 @Controller 차이
- @Controller : 반환 값이 String 이면 VIEW PAGE의 이름으로 인식된다. 
  - 그래서 뷰를 찾고 뷰가 랜더링 된다.
- @RestController 는 HTTP 메시지 바디에 바로 입력된다
  - 똑같이 String-Type 으로 반환해도 실행 결과로 ok 문자열을 받게된다
  - @ResponseBody 와 관련이 있는데 뒤에서 다룸
  


## SpringMVC에서 HttpServletRequest request 를 사용하는 방법
- 실패하는 방법 HttpServletRequest 클래스는 DI로 주입받는 방식이 실패했다
```java
@Autowired
HttpServletRequest request; //안된다 .. NullPointerException
```
- 메서드의 입력파라미터로 자동으로 사용할 수 있었다
```java
@RequestMapping("/hello-basic")
public String helloBasic(HttpServletRequest request) {
    log.info("뒤에 슬래쉬 붙던 안붙던 상관하지 않고 둘다 허용 : /hello-basic, /hello-basic/");
    log.info("모든 HTTP 메서드를 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE 등emd");
    return "OK /hello-basic :: " + request.getMethod();
}
```

- 참고 : 입력매개변수 뭘 받아오는걸 생각해보면 
  - 함수 시그니쳐가 달라지는거인데.. 생각해보면 Pageable도 이걸로 개발했어요 그때는 누가 마법같이 만들어주는거지 신기해 했음
  - 이런걸 보면 스프링MVC는 OCP 원칙을 철저하게 준수해서 개발할 수 있는 프레임워크
  - 키워드 : HandlerMethodArgumentResolver, https://starkying.tistory.com/entry/Spring-MVC-%E2%80%94-HandlerMethodArgumentResolver-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0