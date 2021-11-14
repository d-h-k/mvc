package hello.springmvc.basic.response;

import hello.springmvc.basic.request.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@RestController // == @ResponseBody(클래스레벨에 붙이는것도 가능) + @Controller
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        log.info("\nresponseBodyV1");
        log.info("과거의 JSP-Servlet 시절처럼 사용하는 방식");
        response.getWriter().write("OK : responseBodyV1");
    }


    @GetMapping("/response-body-string-v2")
    public ResponseEntity<?> responseBodyV2(HttpServletResponse response) throws IOException {
        log.info("\nresponseBodyV2");
        log.info("HTTP 응답상태코드를 지정할 수 있다");
        return new ResponseEntity<>("OK : responseBodyV2", HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        log.info("\nresponseBodyV3");
        log.info("@ResponseBody 어노테이션을 이용하면, VIEW 를 사용하지 않고도, 바디에다가 메시지를 입력할 수 있다");
        log.info("v2와 차이점이라면, 리턴타입이 스트링이냐, ResponseEntity 차이");
        return "OK : responseBodyV3";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<?> responseBodyJsonV1() {
        log.info("\nresponseBodyJsonV1");
        HelloData hi = new HelloData();
        hi.setAge(99);
        hi.setUsername("cafe-latte : responseBodyJsonV1111");
        log.info("JSON 형식으로 객체가 Convert된다. 거기다가 http 응답코드 추가");
        return new ResponseEntity<>(hi, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        log.info("\nresponseBodyJsonV2");
        HelloData hi = new HelloData();
        hi.setAge(99);
        hi.setUsername("cafe-latte : responseBodyJsonV22222");
        log.info("위에 v1과 다른점이라면, @ResponseStatus 어노테이션을 사용해서 고정되게 출력하는데, 이경우 프로그래밍적으로 HTTP 응답코드를 변경하기 힘들다");
        return hi;
    }

}
