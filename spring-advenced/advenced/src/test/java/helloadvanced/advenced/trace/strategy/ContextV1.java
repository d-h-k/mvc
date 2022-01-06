package helloadvanced.advenced.trace.strategy;

import helloadvanced.advenced.trace.strategy.code.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();

        //비지니스 로직 실행
        strategy.call();//위임
        //비지니스 로직 종료

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime = {}", result);

        strategyPatternToolTip(true);
    }

    private void strategyPatternToolTip(boolean onDisplay) {
        if(!onDisplay) {
            return;
        }
        log.info("ContextV1 은 변하지 않는 로직을 가지고 있는 템플릿 역할을 하는 코드이다. 전략 패턴에서는 이것을\n" +
                         "컨텍스트(문맥)이라 한다.\n" +
                         "쉽게 이야기해서 컨텍스트(문맥)는 크게 변하지 않지만, 그 문맥 속에서 strategy 를 통해 일부 전략이\n" +
                         "변경된다 생각하면 된다.\n" +
                         "Context 는 내부에 Strategy strategy 필드를 가지고 있다. 이 필드에 변하는 부분인 Strategy 의\n" +
                         "구현체를 주입하면 된다.\n" +
                         "전략 패턴의 핵심은 Context 는 Strategy 인터페이스에만 의존한다는 점이다. 덕분에 Strategy 의\n" +
                         "구현체를 변경하거나 새로 만들어도 Context 코드에는 영향을 주지 않는다.\n" +
                         "어디서 많이 본 코드 같지 않은가? 그렇다. 바로 스프링에서 의존관계 주입에서 사용하는 방식이 바로 전략\n" +
                         "패턴");
    }


    @Test
    @DisplayName("strategyTestV3 - 전략 패턴을 익명내부클래스로")
    public void strategyTestV3() {

        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직 1 실행");
            }
        });
        context1.execute();


        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직 2 실행");
            }
        });
        context2.execute();

    }

    @Test
    @DisplayName("strategyTestV4 - 전략패턴을 람다로 주입")
    public void test() {
        ContextV1 contextV11 = new ContextV1(() -> log.info("비지니스 로직 1 실행"));
        contextV11.execute();

        ContextV1 contextV12 = new ContextV1(() -> log.info("비지니스 로직 2 실행"));
        contextV12.execute();

        /* 합본교얀 95페이지 정리

        ## 전략패턴을 사용해서, 코드를 개선해봤음
        - 이번 전략패턴에서는 템플릿 메서드 패턴의 장점인 변하는것과 변화하지 않는걸 분리해서 중복을 제거하면서도 상속을 사용하지 않아서 깔끔하다
          - 상속을 사용하지 않아 깔끔 - 클래스를 안만들어도 되고,
        - 전략패턴은 상속이아니라 인터페이스를 사용하는데, 변하지 않는 부분을 Context 에 두고 변하는 부분을 Strategy 를 구현해서 만든다
        - 내부 필드의 인터페이스 Strategy 에 어떤 전략을 사용할지 주입해서 사용


        ## 스프링프레임워크도 전략패턴을 사용하는데,ㄹ


         */
    }

}
