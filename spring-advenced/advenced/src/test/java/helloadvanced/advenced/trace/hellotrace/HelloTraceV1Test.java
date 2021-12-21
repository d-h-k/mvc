package helloadvanced.advenced.trace.hellotrace;

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
    @DisplayName(" ")
    public void test() {
        //given

        //when

        //then
        throw new AssertionError();

    }

}
