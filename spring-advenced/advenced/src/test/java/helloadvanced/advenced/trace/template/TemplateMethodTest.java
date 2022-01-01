package helloadvanced.advenced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();

    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("비지니스 로직 1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }


    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("비지니스 로직 2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

}
