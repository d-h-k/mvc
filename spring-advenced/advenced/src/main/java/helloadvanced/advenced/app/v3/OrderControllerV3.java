package helloadvanced.advenced.app.v3;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV1;
import helloadvanced.advenced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        } catch (Exception e) { // 예외시 로직
            trace.exception(status, e);
            throw e;
        }
    }


    @GetMapping("/v3/why")
    public ResponseEntity<?> whyIamLearnThis() {

        log.info("whyIamLearnThis 메서드 실행");
        String note = "앞선 v2 버전에서의 문제를 개선하고자 함\n" +
                "  - v2 문제점 \n" +
                "    - traceID 를 죄다 넘겨주는 코드로 작성해서, 모든 티어(service,repo,controller)마다 수정이 필요하고\n" +
                "    - begin/beginSync 메서드를 정확하게 사용해야 하고, 쓰기 불편하면서\n" +
                "    - 컨트롤러 시작이 아닌 트레이스는 추적할수 없다\n" +
                "    - OCP 위반 : 기능추가를 위해서 기존코드를 변경해야 하니까\n" +
                "    - SRP 위반 : 모든 tier (service+repo+controller) 가 begin/beginSync 메서드를 정확하게 쓰고, 메서드도 추가로 넘겨줘야 하는 책임이 추가됨\n" +
                "  - v3 목표\n" +
                "    - TraceId 를 파라미터로 넘기지 않고 기능구현하기\n" +
                "    - LogTrace 인터페이스를 먼저 만들고 구현\n";

        log.info(note);

        return ResponseEntity.ok(note);
    }
}
