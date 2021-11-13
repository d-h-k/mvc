package hello.springmvc.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.out;
import static org.assertj.core.api.Assertions.assertThat;


class LogTestControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String[] prefix = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] postfix = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    private static final int REPEAT = 1000;


    //@Test
    @RepeatedTest(10)
    @DisplayName("trace : 출력하지 않는경우, {} vs [+]")
    void logBenchmarkV1(RepetitionInfo info) {
        out.println(info.getCurrentRepetition());

        //given
        long start = 0L;
        long end = 0L;


        //when
        start = System.nanoTime();
        stringPlusTrace();
        end = System.nanoTime();
        long stringDuration = end - start;

        start = System.nanoTime();
        lazyLogTrace();
        end = System.nanoTime();
        long lazyDuration = end - start;


        //then
        assertThat(lazyDuration).isLessThan(stringDuration);
        log.info("String Plus 방식 : {}ns", stringDuration);
        log.info("Lazy Replace 방식: {}ns", lazyDuration);
        log.info("결론 : Replace 가 {}ns 빠름", stringDuration - lazyDuration);
        log.info("결론 : Replace 가 {}배 빠름", (double) stringDuration / (double) lazyDuration);
    }

    private void lazyLogTrace() {
        for (int i = 0; i < REPEAT; i++) {
            log.trace("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.trace("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.trace("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.trace("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.trace("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
        }
    }

    private void stringPlusTrace() {
        for (int i = 0; i < REPEAT; i++) {
            log.trace("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.trace("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.trace("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.trace("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.trace("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
        }
    }




    @Test
    @DisplayName("info : 출력하는 경우 , {} vs [+]")
    void logBenchmarkV2() {

        //given
        long start = 0L;
        long end = 0L;


        //when
        start = System.nanoTime();
        stringPlus();
        end = System.nanoTime();
        long stringDuration = end - start;

        start = System.nanoTime();
        lazyLog();
        end = System.nanoTime();
        long lazyDuration = end - start;


        //then
        assertThat(lazyDuration).isLessThan(stringDuration);
        log.info("String Plus 방식 : {}ns", stringDuration);
        log.info("Lazy Replace 방식: {}ns", lazyDuration);
        log.info("결론 : Replace 가 {}ns 빠름", stringDuration - lazyDuration);
        log.info("결론 : Replace 가 {}배 빠름", (double) stringDuration / (double) lazyDuration);
    }

    private void lazyLog() {
        for (int i = 0; i < REPEAT; i++) {
            log.info("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.info("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.info("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.info("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
            log.info("lazyLog : {}{}", prefix[(int) (Math.random() * 10)], postfix[(int) (Math.random() * 10)]);
        }
    }

    private void stringPlus() {
        for (int i = 0; i < REPEAT; i++) {
            log.info("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.info("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.info("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.info("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            log.info("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
        }
    }


    @RepeatedTest(10)
    @DisplayName("print vs logger 속도차이")
    public void test(RepetitionInfo info) {
        //given
        long start = 0L;
        long end = 0L;


        //when
        start = System.nanoTime();
        printer();
        end = System.nanoTime();
        long printer = end - start;

        start = System.nanoTime();
        lazyLog();
        end = System.nanoTime();
        long lazyDuration = end - start;


        //then
        assertThat(lazyDuration).isLessThan(printer);
        log.info("print 방식 : {}ns", printer);
        log.info("logger 방식: {}ns", lazyDuration);
        log.info("결론 : logger 가 {}ns 빠름", printer - lazyDuration);
        log.info("결론 : logger 가 {}배 빠름", (double) printer / (double) lazyDuration);
    }

    private void printer() {
        for (int i = 0; i < REPEAT; i++) {
            out.println("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            out.println("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            out.println("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            out.println("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
            out.println("stringPlus : " + prefix[(int) (Math.random() * 10)] + postfix[(int) (Math.random() * 10)]);
        }
        //String.format("Hello %s(%s)",
    }


    @Test
    @DisplayName("단순 문자열 출력 : log VS sout")
    public void tesffdfdRt() {
        //given
        out.println("드릉드릉하다");
        //whenR
        log.info("드릉드릉하다");
        //then

    }
}