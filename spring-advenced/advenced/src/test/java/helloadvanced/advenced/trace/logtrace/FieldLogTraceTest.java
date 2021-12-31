package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//테스트 이동or 작성 cmd+shitf+T
@Slf4j
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

    @Test
    @DisplayName("여러번 호출하는 테스트")
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

        /* 실행결과
        22:57:50.199 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] hello1
        22:57:50.207 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] |-->hello2
        22:57:50.207 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | |-->hello3
        22:57:50.208 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | | |-->hello4
        22:57:50.208 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | | | |-->hello5
        22:57:50.209 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | | | | |-->hello6
        22:57:50.209 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | | | | |<--hello6 time=0ms
        22:57:50.209 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | | | |<--hello5 time=1ms
        22:57:50.209 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | | |<--hello4 time=1ms
        22:57:50.209 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] | |<--hello3 time=2ms
        22:57:50.209 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] |<--hello2 time=6ms
        22:57:50.210 [Test worker] INFO helloadvanced.advenced.trace.logtrace.FieldLogTrace - [8b3b21ea] hello1 time=13ms
         */
        log.info("서로다른 호출 hello1, 2, 3 .... 6 이 나갔는데 아이디가 같은게 되버려서 구분이 안됨");
        log.info("동시성 문제가 발생함 짧은시간에 여러번 호출했더니 별도의 요청이 뒤죽박죽된다 >> 1년에 한번은 이런 문제가 발생");
    }

}
