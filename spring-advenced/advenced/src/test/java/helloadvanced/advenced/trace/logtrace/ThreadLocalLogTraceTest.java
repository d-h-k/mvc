package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    @DisplayName("LocalThread impl - (로그트레이스 로컬스레드 구현체)정상흐름")
    public void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }


    @Test
    @DisplayName("LocalThread impl - depth2 예외상황")
    public void begin_exception_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
    }


    @Test
    @DisplayName("LocalThread impl - 여러번 호출하는 테스트")
    public void manyTest() {
        //given
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        TraceStatus status3 = trace.begin("hello3");
        TraceStatus status4 = trace.begin("hello4");
        TraceStatus status5 = trace.begin("hello5");
        TraceStatus status6 = trace.begin("hello6");
        //when
        //then
        trace.end(status6);
        trace.end(status5);
        trace.end(status4);
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);
        log.info("이게 같은 스레드에서 도는거라 계단식 피라미드로 나오는게 맞음");

    }

}

