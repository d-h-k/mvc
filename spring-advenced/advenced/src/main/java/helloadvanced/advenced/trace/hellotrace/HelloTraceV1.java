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

    private void complete(TraceStatus status, Object o) {
        //todo
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }
}
