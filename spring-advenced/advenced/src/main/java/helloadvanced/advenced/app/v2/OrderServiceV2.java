package helloadvanced.advenced.app.v2;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    /* before : V0 버전
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
    // @todo : 스터디 시간에 설명할 코드 2
    - 엄청나게 지저분해진다

     */

    /* after : V1 버전 */
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin(getClass().getName() +
                                         "." + "OrderServiceV1.orderItem()메서드임");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
