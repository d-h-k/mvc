package helloadvanced.advenced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    @DisplayName("전략패턴 적용 전 원시상태")
    public void contextV1Test() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    @DisplayName(" ")
    public void test() {
        String strategyPattern = "\n" +
                "탬플릿 메서드 패턴은 부모 클래스에 변하지 않는 템플릿을 두고, 변하는 부분을 자식 클래스에 두어서\n" +
                "\n" +
                "상속을 사용해서 문제를 해결했다.\n" +
                "\n" +
                "전략 패턴은 변하지 않는 부분을 Context 라는 곳에 두고, 변하는 부분을 Strategy 라는 인터페이스를\n" +
                "\n" +
                "만들고 해당 인터페이스를 구현하도록 해서 문제를 해결한다. 상속이 아니라 위임으로 문제를 해결하는\n" +
                "\n" +
                "것이다.\n" +
                "\n" +
                "전략 패턴에서 Context 는 변하지 않는 템플릿 역할을 하고, Strategy 는 변하는 알고리즘 역할을 한다.\n" +
                "\n" +
                "GOF 디자인 패턴에서 정의한 전략 패턴의 의도는 다음과 같다.\n" +
                "\n" +
                "\\> 알고리즘 제품군을 정의하고 각각을 캡슐화하여 상호 교환 가능하게 만들자. 전략을 사용하면 알고리즘을\n" +
                "\n" +
                "사용하는 클라이언트와 독립적으로 알고리즘을 변경할 수 있다.\n" +
                "\n";

        //given

        //when

        //then
        throw new AssertionError();

    }


}
