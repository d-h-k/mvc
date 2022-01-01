package helloadvanced.advenced.app.v3;

import helloadvanced.advenced.trace.TraceStatus;
import helloadvanced.advenced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace; //콤포넌트, 싱글톤, 주입받음

    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try { // 정상로직
            status = trace.begin("OrderControllerV3-request()!");
            orderService.orderItem(itemId);
            trace.end(status);
            return "OK";
        } catch (Exception e) { // 예외시 로직
            trace.exception(status, e);
            throw e;
        }
    }


    @GetMapping("/v3/why")
    public ResponseEntity<?> whyIamLearnThis() {

        log.info("whyIamLearnThis 메서드 실행");
        String note = "앞선 v2 버전에서의 문제를 개선하고자 함\n\n" +
                "  - v2 문제점 \n\n" +
                "    - traceID 를 죄다 넘겨주는 코드로 작성해서, 모든 티어(service,repo,controller)마다 수정이 필요하고\n\n" +
                "    - begin/beginSync 메서드를 정확하게 사용해야 하고, 쓰기 불편하면서\n\n" +
                "    - 컨트롤러 시작이 아닌 트레이스는 추적할수 없다\n\n" +
                "    - OCP 위반 : 기능추가를 위해서 기존코드를 변경해야 하니까\n\n" +
                "    - SRP 위반 : 모든 tier (service+repo+controller) 가 begin/beginSync 메서드를 정확하게 쓰고, 메서드도 추가로 넘겨줘야 하는 책임이 추가됨\n\n" +
                "  - v3 목표\n\n" +
                "    - TraceId 를 파라미터로 넘기지 않고 기능구현하기\n\n" +
                "    - LogTrace 인터페이스를 먼저 만들고 구현\n";

        log.info(note);
        return ResponseEntity.ok(note);
    }


    @GetMapping("/v3/threadLocal")
    public ResponseEntity<?> threadLocalTip() {
        String threadLocalTip = "\n" +
                "1) ThreadLocal 을 사용하고 제거하지 않은체로, 냅두면 (Thread pool)쓰레드풀을 사용하는 APP 의 경우 심각한 문제가 발생\n" +
                "2) 대표적으로 WAS APP 들 (Tomcat 같은 구현체들) 이 Thread pool 을 사용하는데, 이경우 큰 문제가 발생한다\n" +
                "3) 스프링 프레임웍의 경우 기본이 내장 임베디드 톰캣이 탑제되어 있기 때문에 주의해야한다\n" +
                "4) Thread pool 을 사용하는 WAS 에서 ThreadLocal 을 remove 하지 않으면 발생하는 문제를 설명해보면\n" +
                "    - UserA 요청이 들어오면 >> 쓰레드로컬이 전용 공간에 저장 >> 사용이 끝나면 쓰레드풀에 반납\n" +
                "    - 쓰래드들은 생성/제거 비용이 비싸기 때문에 쓰레드풀에다가 여러개를 만들어놓고 재사용하기 때문\n" +
                "    - 여기서 문제가 생기는데, 쓰레드풀에 의해 쓰레드가 살아있기 때문에, 쓰레드로컬에 짱박아놓은 데이터를 제거하지 않으면, 쓰레드로컬의 데이터도 살아있다\n" +
                "    - UserB 가 이 시점에서 조회요청을 날리는데 쓰레드로컬의 데이터를 조회하게 되면 UserA의 데이터를 받는다 \n" +
                "    - 결국 UserB 에게 UserA 의 정보가 노출되는 심각한 문제가 발생\n" +
                "    - 이러한 문제들을 예방하고, 방지하기 위헤서는 >> Http 요청이 끝나는 시점에 >> 꼭 쓰레드로컬을 제거해야만 한다!\n" +
                "    - 웹서버/WAS 에서는 쓰레드풀을 무조건 사용해야한다 성능때문에 따라서 꼭 쓰레드풀을 깔끔하게 제거하는게 좋고\n" +
                "    - 혹시라도 쓰레드풀을 깔끔하게 제거하기 원한다면 Filter 나 Intercepter 를 사용하면 깔끔한 처리가 가능하다\n" +
                "5) 쓰레드로컬 단원 정리\n" +
                "    - 5.1 필드변수를 통해 동기화 >> 동시성 문제 발생 : 이 문제는 멀티(여러개의 요청) 쓰레드가 동시에 같은 인스턴스(싱글턴)에 접근할때 발생\n" +
                "    - 5.2 쓰레드로컬을 사용하면 같은 인스턴스(싱글턴)에서 사용해도 쓰레드마다의 각자 전용공간에 접근하기 때문에 동시성 문제가 해결된다\n" +
                "    - 5.3 기존 방식인 필드변수 기반 구현 >> 쓰레드로컬 기반 구현체 로 변경해서 사용하기 위해서 스프링의 빈등록시 갈아치우면 OCP 위반하지않고 구현체 변경적용이 가능\n" +
                "    - 5.4 쓰레드로컬도 완벽한건 아니고 꼭 주의해야할점이 있는데, 바로 개발자가 꼭 remove 를 신경써줘야한다는 부분임\n" +
                "    - 5.5 개발자에게 remove 호출이 강제되는, 비지니스 로직과 상관없는 코드가 필요한건 여전하므로 Filter나 Intercepter 등을 활용하면 처리가 가능하고, 다음단윈인 템플릿 메서드 패턴은 왜 쓰이고 어떻게 쓰이는지는 위의 관점에서 생각하고 접근해보자\n";
        log.info(threadLocalTip);
        return ResponseEntity.ok(threadLocalTip);
    }
}

