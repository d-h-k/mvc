package helloadvanced.advenced.trace.hellotrace;

import helloadvanced.advenced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {


    @Test
    void begin_end_level2() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }


    @Test
    void begin_exception_level2() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

    @Test
    @DisplayName("이렇게 동작해야 합니다")
    public void showHowToWork() {
        //given
        HelloTraceV2 trace = new HelloTraceV2();

        //when
        //then
        TraceStatus t1 = trace.begin("hi1");
        TraceStatus t2 = trace.beginSync(t1.getTraceId(),"hi2");
        TraceStatus t3 = trace.beginSync(t2.getTraceId(),"hi3");
        TraceStatus t4 = trace.beginSync(t3.getTraceId(),"hi4");
        TraceStatus t5 = trace.beginSync(t4.getTraceId(),"hi5");
        TraceStatus t6 = trace.beginSync(t5.getTraceId(),"hi6");
        trace.end(t6);
        trace.end(t5);
        trace.end(t4);
        trace.end(t3);
        trace.end(t2);
        trace.end(t1);




    }
}
