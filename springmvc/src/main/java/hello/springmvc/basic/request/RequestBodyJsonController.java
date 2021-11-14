package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("\n requestBodyJsonV1 : 과거의 유물같은 HttpServletRequest/Response 객체를 사용해서 처음부터 끝가지 다 직접한다");
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        HelloData helloData = mapper.readValue(messageBody, HelloData.class);
        log.info("helloData, username={}, age={}", helloData.getUsername(), helloData.getAge());
        response.getWriter().write("OK : requestBodyJsonV1");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("\n requestBodyJsonV2 : 서블릿 객체를 직접 접근하지 않아도 스프링에서 @RequestBody 를 통해 바디문자열을 받아올 수 있다");
        log.info("messageBody={}", messageBody);
        HelloData helloData = mapper.readValue(messageBody, HelloData.class);
        log.info("helloData, username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK : requestBodyJsonV2";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("\n requestBodyJsonV3 : MappingJackson2Conv 라는 애가 스프링 내부적으로 돌아서 변환해준다");
        log.info(" @RequestBody 는 생략이 불가능한데, 이유는 해석하는 방식이 모델에트리뷰트가 되버리기 때문에 >> 아규먼트 리졸버로 지정하던지 해야함");
        log.info("helloData, username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK : requestBodyJsonV3";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> helloDataHttpEntity)  {
        log.info("\n requestBodyJsonV4 : HttpEntity 로도 쓸 수 있다");
        HelloData helloData = helloDataHttpEntity.getBody();
        log.info("helloData, username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK : requestBodyJsonV4";
    }


    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData)  {
        log.info("\n requestBodyJsonV5 : Json ->> Obj ,  Obj ->> Json : 직렬화 역직렬화가 알아서 이루어짐");
        log.info("@ResponseBody 사용하면 Object (객체)를 Http messageBody에 직접 넣어줄 수 있다");
        log.info("helloData, username={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }

    //주의 : 컨텐츠타입이 꼭 app/json 이어야 함!!


}
