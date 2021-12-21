package helloadvanced.advenced.trace;

import lombok.Getter;

@Getter
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    /*
    TraceStatus 는 로그를 시작할 때의 상태 정보를 가지고 있다. 이 상태 정보는 로그를 종료할 때 사용된다.

    - traceId : 내부에 트랜잭션ID와 level을 가지고 있다.
    - startTimeMs : 로그 시작시간 종료시 수행시간을 구하기 위해
    - message : 시작시 사용한 메시지이다. 이후 로그 종료시에도 이 메시지를 사용해서 출력

    TraceId , TraceStatus 를 사용해서 실제 로그를 생성하고, 처리하는 기능을 개발해보자.
     */
}
