package helloadvanced.advenced.app.v4;

import helloadvanced.advenced.trace.logtrace.LogTrace;
import helloadvanced.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace; //콤포넌트, 싱글톤, 주입받음

    public static final String lessonV4_templateMethodTip = "\n" +
            "OrderServiceV0 : 핵심 기능만 있다 (하지만 로깅 기능이 없다)\n" +
            "OrderServiceV3 : 핵심 기능과 부가 기능이 함께 섞여 있다 (코드가 상당히 지저분하고 배보다 배꼽이 크다)\n" +
            "OrderServiceV4 : 핵심 기능과 템플릿을 호출하는 코드가 섞여있다 (V3보다는 훨씬 개선됬는데, 여전히 매 클라이언트 마다마다 추가할 코드는 있다\n" +
            "의미 : V4는 템플릿 메서드 패턴을 사용해서 [핵심 기능] // [부가기능] 을 두가지를 조금이나 분리 해낼 수 있고  >> 핵심기능에 집중할 수 있다";

    @GetMapping("/v4/request")
    public String request(String itemId) {

        // 변수에 <String> 꼭 써줘야함
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return null;
            }
        };

        return template.execute("OrderControllerV4.request()");
    }

    //@ todo W3 : 템플릿 메서드 && 좋은 설계에 대한 정리
    @GetMapping("/v4/template-method")
    public ResponseEntity<?> templateMethod() {
        log.info(lessonV4_templateMethodTip);
        return ResponseEntity.ok(lessonV4_templateMethodTip);
    }


    @GetMapping("/v4/cleanArch")
    public ResponseEntity<?> cleanArch() {
        String cleanArch = "\n" +
                "- 좋은 설계는??  :   요구사항 변경(기능추가)이 일어났을때 비로소 알게된다\n" +
                "    - 기존 V3 까지는 TraceLog Logic 에 변경이 발생하면 모든 Tier(repo,service,con) 에 찾아 돌아다니면서 일일이 수정해야한다면\n" +
                "    - V4 는 Template-Method 패턴을 사용해서 한군데만 고쳐도 모든 부분에 수정된 TraceLog Logic 가 적용된다\n" +
                "    - 한마디로 단일 책임 원칙(SRP) 지킴! : 변경 지점을 하나로 모아서 변경에 쉽게 대처할수 있는 구조\n" +
                "        - 뒤집어서 말하면, TraceLog Logic 에 대한 책임이 사용처에 분산되어 있는게 아니라 TraceLog 그 자체에 집중되어 있다\n" +
                "\n" +
                "- 템플릿 메서드 패턴 \n" +
                "    - 설명 : 템플릿을 만들어서 99%정도 코드(대부분/골격)를 만들어 놓고 일부단계만 하위 클래스한테 위임(연기) 함\n" +
                "    - 어떻게 만들지? \n" +
                "        - 1% 차이가 나는 단계를 동일한 시그너처를 가진 메소드로 만들어서 분리하고 > 메서드를 재정의 해서 차이나는 부분만 구현\n" +
                "        - 99% 동일한 부분은 수퍼클래스로 올린다\n" +
                "    - 정리 : 99%공통부분 상위(템플릿)으로 올리고, 1%차이는 하위클래스로 내려서, 변하는것과 변하지 않는것의 분리가 핵심 \n" +
                "        - 상속을 통해서 99% 공통점/중복 제거\n" +
                "\n\n";
        log.info(cleanArch);
        return ResponseEntity.ok(cleanArch);
        //https://www.whiteship.me/form-template-method/
    }



    }

