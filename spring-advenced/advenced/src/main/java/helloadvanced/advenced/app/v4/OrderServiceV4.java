package helloadvanced.advenced.app.v4;

import helloadvanced.advenced.trace.logtrace.LogTrace;
import helloadvanced.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
    }
}

//@ todo W3 : 면접질문거리 Void 클래스
/*
package java.lang;
public final
class Void {~~}

- void 가 아니라 >> Void 클래스 라는게 있습니다
  - (번역기) Void 클래스는 보관할 인스턴스화할 수 없는 자리 표시자 클래스입니다. Java 키워드를 나타내는 {@code Class} 객체에 대한 참조
  - Void 클래스는 플레이스홀더 클래스 (=자리만 차지하라고 만들어주는 클래스)
     - Java 예약어 void 를 나타대는 Class 객체에 대한 참조를 유지하기 위해서
     - 생성할 수 없는 플레이스 홀더 클래스
  - void 키워드에 해당하는 pseudo-type 을 나타내는 Class 객체
    - 실체화할수 없음 (아래 생성자 코드 참고)
    ```
    public static final Class<Void> TYPE = (Class<Void>) Class.getPrimitiveClass("void");
    ```
- 요런거 어디다 쓰냐??? 두가지가 있슴당
  - 1) 자바 리플렉션 에서 반환값을 판별하기 위해 사용되고
    -
  - 2) 제네릭 클래스를 구현할 때 사용됩니다
*/
