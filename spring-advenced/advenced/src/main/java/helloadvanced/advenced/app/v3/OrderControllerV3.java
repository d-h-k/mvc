package helloadvanced.advenced.app.v3;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV1;
import helloadvanced.advenced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace; //콤포넌트, 싱글톤, 주입받음

    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try { // 정상로직
            status = trace.begin("OrderControllerV3-request()!");
            orderService.orderItem(itemId);
            trace.end(status);
            return "OK";
        }
        catch (Exception e) { // 예외시 로직
            trace.exception(status,e);
            throw e;
        }
    }
}
