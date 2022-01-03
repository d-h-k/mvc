package helloadvanced.advenced.app.v4;

import helloadvanced.advenced.trace.logtrace.LogTrace;
import helloadvanced.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {
        log.info("OrderRepositoryV4.orderItem() 호출");
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                //저장로직
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외발생오예");
                }
                MySleep(1000);
                return null;
            }
        };

        //이거 안적으면 동작안함
        template.execute("OrderRepositoryV4.orderItem() 호출");
    }

    private void MySleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
