package helloadvanced.advenced.trace.hellotrace;

import helloadvanced.advenced.trace.TraceId;
import helloadvanced.advenced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    // level 깊어짐에 따라 길어짐, 테스트참조
    private static String addSpace(String prefix, int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return builder.toString();
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    private void complete(TraceStatus status, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        long duringTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                     traceId.getId(),
                     addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                     status.getMessage(),
                     duringTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}",
                     traceId.getId(),
                     addSpace(EX_PREFIX, traceId.getLevel()),
                     status.getMessage(),
                     duringTimeMs,
                     e.toString());
        }

    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }
}
