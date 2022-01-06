package helloadvanced.advenced.trace.strategy;

import helloadvanced.advenced.trace.strategy.code.Strategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {
    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        strategy.call();//위임 받음

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime = [{}]", resultTime);
    }
}
