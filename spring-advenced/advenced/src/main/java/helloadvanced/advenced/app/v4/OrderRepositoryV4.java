package helloadvanced.advenced.app.v4;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("v3 Repository.save()");
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생!");
            }
            MySleep(1);
        } catch (Exception  e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void MySleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
