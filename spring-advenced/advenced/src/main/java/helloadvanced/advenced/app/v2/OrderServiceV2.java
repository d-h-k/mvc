package helloadvanced.advenced.app.v2;

import helloadvanced.advenced.trace.TraceId;
import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            //v2 에서 변경되는 부분 .begin() >> .beginSync() 로 바꿔줌
            // 그래서 traceId 가 필요하므로 메서드에 traceId 를 입력받도록 메서드 시그니쳐 추가함
            status = trace.beginSync(traceId,getClass().getName() +
                                         "." + "OrderServiceV2.orderItem()메서드임");
            log.info("beginSync() 는 내부에서 다음 traceId 를 생성하면서 transactionID 유지 && level 은 하나 증가");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
