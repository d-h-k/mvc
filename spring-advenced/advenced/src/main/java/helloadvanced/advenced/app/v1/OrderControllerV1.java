package helloadvanced.advenced.app.v1;

import helloadvanced.advenced.app.v0.OrderServiceV0;
import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;

    private final HelloTraceV1 trace; //콤포넌트, 싱글톤, 주입받음

    @GetMapping("/v1/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try { // 정상로직
            status = trace.begin("OrderControllerV1-request()!");
            orderService.orderItem(itemId);
            trace.end(status);
            return "OK";
        }
        catch (Exception e) { // 예외시 로직
            trace.exception(status,e);
            throw e;
        }
        /* @todo : 스터디때 발표할것
        이 코드의 문제
        1) 일단 try-catch 문이 예외를 먹어버리는 문제가 있어서 >> 예외를 다시 던저줘야 한다
            - 요구사항 중에 어플리케이션 흐름이 바뀌는 문제가 있다
        2) 보일러플레이트 코드가 잔뜩 껴버린다 요구조건에 맞게 구현 하다보니까 상당히 지저분해진다
            - 스테이터스를 받기위해서 넓게 가져갸야되니까 null 대입으로 생성해야 하는등..
        3) 문제의식 : 하나라도 예외처리를 안하면 버그가 생긴다 >> 로그 사용법을 정확하게 숙지해야만 로거 동작에 버그가 안생긴다
        4) 결론 : 문제의식을 가지기 위해서 이 단계를 밟는중임,
            - 로거가 일단 돌아가게 만들고, 개선하는 과정에서 리팩토링을 하고 스프링 고급 기능을 배움
         */
    }
}
