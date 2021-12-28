package helloadvanced.advenced.trace.hellotrace;

import helloadvanced.advenced.trace.TraceId;
import helloadvanced.advenced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {


    @Test
    @DisplayName("트레이싱이 되는지 동작확인")
    public void beginEnd() {
        //given
        HelloTraceV1 trace = new HelloTraceV1();

        //when
        TraceStatus status = trace.begin("hello");

        //then
        trace.end(status);
    }

    @Test
    @DisplayName("예외가 터지는지 확인한다")
    public void beginException() {
        //given
        HelloTraceV1 trace = new HelloTraceV1();
        //when
        TraceStatus status = trace.begin("hello there");

        //then
        trace.exception(status, new IllegalArgumentException());
    }

    @Test
    @DisplayName("학습용 테스트")
    public void test() {
        //given
        HelloTraceV1 trace = new HelloTraceV1();

        //when
        TraceStatus t1 = trace.begin("hi1");
        TraceStatus t2 = trace.begin("hi2");
        TraceStatus t3 = trace.begin("hi3");
        TraceStatus t4 = trace.begin("hi4");
        TraceStatus t5 = trace.begin("hi5");
        trace.end(t5);
        trace.end(t4);
        trace.end(t3);
        trace.end(t2);
        trace.end(t1);

        //then


    }

}
