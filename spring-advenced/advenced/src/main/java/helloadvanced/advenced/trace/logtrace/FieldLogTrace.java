package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceId;
import helloadvanced.advenced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    //todo W2 : 여기가 중요 : 트레이스아이디를 필드변수로 저장
    private TraceId traceHolder; // 동기화를 위해 필드변수를 사용하는데, 동시성 이슈가 발생한다
    //심각한 동시성 문제가 있음

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    // 로그가 시작할때 : 여기가 중요한 로직
    /*
    syncTraceId() : TraceId 를 새로 만들거나 앞선 로그의 TraceId 를 참고해서 동기화하고, level 도 증가한다.
    최초 호출이면 TraceId 를 새로 만든다. 직전 로그가 있으면 해당 로그의 TraceId 를 참고해서 동기화하고, level 도 하나 증가한다.
     */
    private void syncTraceId() {
        if(traceHolder ==null) {
            traceHolder = new TraceId();
        }
        else {
            traceHolder = traceHolder.createNextId();
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();

        TraceId traceId = status.getTraceId();

        //정상종료
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                     addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                     resultTimeMs);
        } else { //비정상 종료
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                     addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                     e.toString());
        }
        releaseTraceId();
    }


    //트레이스가 끝나면 마지막 >> 로그가 끝나는 곳
    private void releaseTraceId() {
        if(traceHolder.isFirstLevel()) {
            traceHolder = null; // 파괴의 의미
        }
        else {
            traceHolder= traceHolder.createPreviousId();
        }
    }
    /*
    releaseTraceId() : 메서드를 추가로 호출할 때는 level 이 하나 증가해야 하지만, 메서드 호출이 끝나면 level 이 하나
    감소해야 한다. releaseTraceId() 는 level 을 하나 감소한다. 만약 최초 호출( level==0 )이면 내부에서 관리하는 traceId 를 제거한다
     */


    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
    }
}
