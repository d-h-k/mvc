package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        log.info("과거 JSP 시절에나 쓰던 방식, HttpServletRequest 에서 제공하는 방식으로 요청 파라미터를 조회");
        response.getWriter().write("OK : /request-param-v1");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("@RequestParam : 파라미터 이름으로 바인딩");
        log.info("@ResponseBody : View 조회를 무시하고, HTTP message body 에 문자열 담아서 보낸다");
        log.info("username={}, age={}", memberName, memberAge);
        return "OK : /request-param-v2";
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam 어노테이션에 넣어줘야하는 파라미터를 생략할수 있다\n" +
                "이정도 형태가 Best Practice");

        log.info("username={}, age={}", username, age);
        return "OK : /request-param-v3";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능하다\n" +
                "너무 과도하게 생략되있어서 코드 명확성/가독성이 떨어질수도 있어서 비추천");

        log.info("username={}, age={}", username, age);
        return "OK : /request-param-v3";
    }
    //@RequestParam 애노테이션을 생략하면 스프링 MVC는 내부에서 required=false 를 적용

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        log.info("/request-param 요청이 들어오면 queryString \"username\" 없을때 400-Bad Req 예외가 발생한다.");
        return "OK : /request-param-required";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) { //특이하게 숫자인데 기본값은 문자열로

        log.info("username={}, age={}", username, age);
        return "OK : /request-param-default";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "OK : /request-param-map";
    }


    @ResponseBody
    @RequestMapping("/request-multi-param-map")
    public String requestMultiParamMap(@RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age").get(0));
        return "OK : /request-multi-param-map";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());

        log.info("스프링MVC는 @ModelAttribute 가 있으면 다음을 실행한다.\n" +
                "  - HelloData 객체를 생성한다.\n" +
                "  - 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를\n" +
                "  - 호출해서 파라미터의 값을 입력(바인딩) 한다.\n" +
                "  - 예시) 파라미터 이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.");
        return "OK - /model-attribute-v1";
    }
}
