package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    //HttpServletRequest request;

    @RequestMapping("/hello-basic")
    public String hellobasic(HttpServletRequest request) {
        log.info("뒤에 슬래쉬 붙던 안붙던 상관하지 않고 둘다 허용 : /hello-basic, /hello-basic/");
        log.info("모든 HTTP 메서드를 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE 등emd");
        return "OK /hello-basic :: " + request.getMethod();
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        log.info("method 특정 HTTP 메서드 요청만 허용");
        log.info("맞지않은 Method 로 요청을 받으면 HTTP 405 상태코드(Method Not Allowed)를 반환");
        return "OK : /mapping-get-v1";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2 : 위에꺼의 축약형 >> @RequestMapping 말고 @GetMapping 을 쓰는게 더욱 직관적");
        return "OK : /mapping-get-v2";
    }


    @GetMapping(value = "/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mapping userId : {}",data);
        log.info("최근 HTTP API 는 mapping-Path 방식 (리소스 경로에 식별자를 넣는 스타일)을 선호한다\n" +
                "과거에는 qeury-String 을 선호했었다." +
                "    - 내 의견이지만 서버가 JSP는 qs가 코딩하기 편했고, mvc는 잘게 쪼갤수있어서 그런게 아닐까?\n" +
                "@RequestMapping 은 URL 경로를 템플릿화 할 수 있다\n" +
                "@PathVariable 을 사용하면 매칭 되는 부분을 편하게 조회할 수 있다\n" +
                "@PathVariable 의 이름과 파라미터 이름이 같으면 생략가능\n" +
                "전 : {}\n" +
                "후 : {}\n","@PathVariable(\"data\") String data","@PathVariable String data");
        return "OK : /mapping/" + data;
    }

    @GetMapping(value = "/mapping/users/{userId}/orders/{orderId}")
    public String mappingPathV2(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPathV2 : userId = {} , orderId = {}",userId,orderId);
        return "OK : mappingPathV2," + userId + "," + orderId;
    }



    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParma() {
        log.info("mappingParma");
        return "OK : mappingParma";
    }









}
// url
// 매서드
// 헤더
// 컨텐츠타입
