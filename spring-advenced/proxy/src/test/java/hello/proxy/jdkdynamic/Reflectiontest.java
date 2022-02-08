package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class Reflectiontest {

    @Test
    @DisplayName("reflection1")
    void reflection1() throws Exception {

        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.Reflectiontest$Hello");
        Hello target = new Hello();

        Method methodA = classHello.getMethod("callA");
        Object result1 = methodA.invoke(target);
        log.info("result1={}", result1);


        Method methodB = classHello.getMethod("callA");
        Object result2 = methodB.invoke(target);
        log.info("result2={}", result2);

    }

    @Test
    @DisplayName("reflection0")
    public void reflection0() {
        Hello target = new Hello();


        log.info("start 1");
        String result1 = target.callA();
        log.info("result={}", result1);

        log.info("start 2");
        String result2 = target.callB();
        log.info("result={}", result2);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
