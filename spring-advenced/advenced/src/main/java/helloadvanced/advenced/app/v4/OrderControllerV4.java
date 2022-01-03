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


    @GetMapping("/v4/why")
    public ResponseEntity<?> tt() {

        log.info("tt 메서드 실행");
        String note = "";

        log.info(note);
        return ResponseEntity.ok(note);
    }


    @GetMapping("/v3/template-method")
    public ResponseEntity<?> templateMethod() {
        String templateMethodTip = "\n";

        log.info(templateMethodTip);
        return ResponseEntity.ok(templateMethodTip);
    }
}

