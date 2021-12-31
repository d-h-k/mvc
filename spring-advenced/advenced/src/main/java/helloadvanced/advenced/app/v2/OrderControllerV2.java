package helloadvanced.advenced.app.v2;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        /* @todo : v2 의미
        1) 요구사항중 메서드 호출의 깊이를 표현하고, 같은 HTTP 요청은 같은 ID 를 남기도록
          - 직전 로그의 depth 와 ID를 받아야 가능하다
          - 로그별로 ID & Depth Level 을 전달하도록 수정하자
        2) 일반적으로 이런 ID & Depth Level 가능하게 하는건 Context 라고 한다
        3) v2에서는 ID & Depth Level 를 유지시키고 연동하는 Context 를 구현할꺼임

         */


    }
}
