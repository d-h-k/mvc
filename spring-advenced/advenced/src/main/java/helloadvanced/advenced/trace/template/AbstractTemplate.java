package helloadvanced.advenced.trace.template;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.logtrace.LogTrace;

//@ todo W3 : 템플릿 메서드 사용 설명
// 부모클래스, 템플릿 역할의 클래스
// <T> 제네릭 사용, 반환타입을 정의
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);

            //로직 호출
            T result = call();
            // 나머지는 변화하지 않는 템플릿
            // call() 로 재정의 할수 있는 부분이 변화하는 부분

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    // 나머지는 템플릿이고, 이 작은 call() 메서드를 재정의해서 일부 내용만 다르게 동작할수 있음
    // call 메서드를 상속으로 구현해 하위 구체클레스를 정의해 사용 >> 변화하는 부분
    protected abstract T call();
}
