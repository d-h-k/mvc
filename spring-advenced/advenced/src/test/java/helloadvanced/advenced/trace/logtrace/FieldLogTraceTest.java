package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {
    FieldLogTrace trace = new FieldLogTrace();

    @Test
    @DisplayName("레벨2에서 종료하는 테스트")
    public void begin_end_level2() {
        //given
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("world2");
        //when
        //then
        trace.end(status2);
        trace.end(status1);
        /*
        [ed72b67d] hello1
        [ed72b67d] |-->hello2
        [ed72b67d] |<--hello2 time=2ms
        [ed72b67d] hello1 time=6ms
         */
    }

    @Test
    @DisplayName("레벨2에서 예외를 터트리는 테스트")
    public void begin_exception_level2() {
        //given
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("world2");
        //when
        //then
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
        /*
        [59770788] hello
        [59770788] |-->hello2
        [59770788] |<X-hello2 time=3ms ex=java.lang.IllegalStateException
        [59770788] hello time=8ms ex=java.lang.IllegalStateException
         */
    }
}
