package hello.springmvc.basic.requestmapping;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/header")
    public String headers(HttpServletRequest request, HttpServletResponse response,
                          HttpMethod httpMethod, Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {
        log.info("request={} \n\t - {}", request,"서블릿 리퀘스트");
        log.info("response={} \n\t - {}", response,"서블릿 리스폰스");
        log.info("httpMethod={} \n\t - {}", httpMethod,"Http 메서드 조회");
        log.info("locale={} \n\t - {}", locale,"Locale 정보를 조회한다");
        log.info("headerMap={} \n\t - {}", headerMap,"모든 HTTP 헤더를 MultiValueMap 형식으로 조회");
        log.info("header host={} \n\t - {}", host,"특정한 헤더만을 따로 검색조회할수가 있다");
        log.info("myCookie={} \n\t - {}", cookie,"특정한 쿠키를 검색조회 할 수 있다");

        log.info("\n\nMultiValueMap\n" +
                "MAP 자료구조로써 HashMap과의 차이는, 하나의 키에 여러 값을 받을 수 있다. 다시말해 중복을 허용하는 맵\n" +
                "HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용");

        log.info("\n\nSpring Manual 참고하기\n" +
                "> @Conroller 의 사용 가능한 Request - Input Parameter(Argument) 목록은 다음 공식 메뉴얼에서 확인할 수 있다\n" +
                "> https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments\n" +
                "> \n\n" +
                "> @Conroller 의 사용 가능한 응답 값 목록은 다음 공식 메뉴얼에서 확인할 수 있다\n" +
                "> https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-return-types\n");

        return "OK : RequestHeaderController";
    }


}
