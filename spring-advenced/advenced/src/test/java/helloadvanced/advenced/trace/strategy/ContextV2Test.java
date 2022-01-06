package helloadvanced.advenced.trace.strategy;

import helloadvanced.advenced.trace.strategy.code.StrategyLogic1;
import helloadvanced.advenced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ContextV2Test {
    /*
    전략 패턴을 사용
     */
    @Test
    @DisplayName("두고보자 내가 더 잘해서 이겨준다")
    public void test() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());

    }

}
