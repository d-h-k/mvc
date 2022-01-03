package helloadvanced.advenced.app.v2;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;

    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try {
            //여기서 요청이 시작됨
            status = trace.begin("OrderControllerV2-request() BEGIN");

            orderService.orderItem(status.getTraceId(), itemId); //v2 에서 : status.getTraceId() 추가함
            trace.end(status);
            return "OK";
        } catch (Exception e) { // 예외시 로직
            trace.exception(status, e);
            throw e;
        }
        /* @todo W2 : v2 의미
        1) 요구사항중 메서드 호출의 깊이를 표현하고, 같은 HTTP 요청은 같은 ID 를 남기도록
          - 직전 로그의 depth 와 ID를 받아야 가능하다
          - 로그별로 ID & Depth Level 을 전달하도록 수정하자
        2) 일반적으로 이런 ID & Depth Level 가능하게 하는건 Context 라고 한다
        3) v2에서는 ID & Depth Level 를 유지시키고 연동하는 Context 를 구현할꺼임

         */
    }

    @GetMapping("/v2/help")
    public ResponseEntity<?> helper() {
        log.info("여기까지 해서 요구사항을 만족했는데 앞으로 어떤걸 해야하냐면");
        String note = "예제만들기 챕터의 끝 : 만들면서 문제점 정리 \n" +
                "  - TraceId 의 동기화를 위해서 관련 메서드의 모든 메서드 시그니쳐를 수정해야 했다\n" +
                "  - 만약에 중간에 인터페이스를 끼워넣었다면 인터페이스까지 고쳐야하니가 작업량이 상당히 많음\n" +
                "  - 클라이언트, 라이브러리 사용자가 실수하면 정상적으로 동작하지 않는데 \n" +
                "    - 처음에 한번만 begin() 을 호출하고\n" +
                "    - 나머지는 모두 beginSync() 를 호출해야 함 꼭!! 안그러면 삑사리 오류동작\n" +
                "  - 만약에 컨트롤러를 통해서 서비스를 호출하는 것이 아니라면 문제가 생기는데\n" +
                "    - 다른 곳에서 서비스를 처음으로 호출하는 상황 - 넘길 TraceId 가 없다\n" +
                "  - 따라서 이 코드는 OCP 원칙을 심각하게 위반한다. 기능추가를 위해서 기존 코드를 죄다 뜯어고쳐야 하는격 나쁜 방식이다\n" +
                "    - 자동차로 따지면 자동차에 새로운 타이어를 장착하기 위해서 거기에 연결된 차축 엔진 변속기까지 죄다 뜯어서 갈아끼워야하는 상황인거\n" +
                "  - 모든것의 원흉은 traceId 같은걸 넘기는 행동은.. 상당히 지저분하다";

        log.info(note);
        return ResponseEntity.ok(note);
    }

}
