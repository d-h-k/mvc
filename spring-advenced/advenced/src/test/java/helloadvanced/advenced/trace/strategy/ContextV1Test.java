package helloadvanced.advenced.trace.strategy;

import helloadvanced.advenced.trace.strategy.code.Strategy;
import helloadvanced.advenced.trace.strategy.code.StrategyLogic1;
import helloadvanced.advenced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    @DisplayName("strategyV0 - 전략패턴 적용 전 원시상태")
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
    @DisplayName("도움말")
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


    @Test
    @DisplayName("strategyV1 - 전략패턴을 적용한 코드")
    public void strategyV1() {

        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();

        log.info("클라이언트 코드 변경 없이 외부에서 주입받는 객체에 따라 구현체의 동작이 변경됨. 어떻게 동작할지 전략을 주입받는다는 의미에서 전략패턴이라고 함");
    }


    @Test
    @DisplayName("strategyV2 - 전략 패턴 익명 내부 클래스1")
    void strategyV2() {

        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("SV2 - 비즈니스 로직1 실행");
            }
        };

        log.info("SV2 - strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();


        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("SV2 - 비즈니스 로직2 실행");
            }};
        log.info("SV2 - strategyLogic2={}", strategyLogic2.getClass());
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

}
