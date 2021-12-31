package helloadvanced.advenced.app.v2;

import helloadvanced.advenced.trace.TraceId;
import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;
        log.info("TraceId 를 파라미터로 전달하기 위해 orderRepository.save() 의 파라미터(메서드 시그니쳐)에 TraceId 를 추가");
        if (itemId.equals("ex")) {
            status = trace.beginSync(traceId, "OrderRepositoryV2.save()메서드 호출");
            throw new IllegalArgumentException("예외 발생!");
        }
        MySleep(1000);
    }

    private void MySleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
