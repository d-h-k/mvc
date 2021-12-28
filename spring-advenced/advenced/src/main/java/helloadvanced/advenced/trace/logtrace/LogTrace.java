package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
    /*
    LogTrace 인터페이스에는 로그 추적기를 위한 최소한의 기능
    begin() , end() , exception() 정의했다.
    이제 파라미터를 넘기지 않고 TraceId 를 동기화 할 수 있는 FieldLogTrace 구현체를 만들어보자
     */
}
