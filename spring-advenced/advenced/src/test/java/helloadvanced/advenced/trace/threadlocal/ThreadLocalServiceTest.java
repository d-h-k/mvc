package helloadvanced.advenced.trace.threadlocal;

import helloadvanced.advenced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalServiceTest {

    @Test
    @DisplayName("ThreadLocal 의 사용법과 조심해야할 부분")
    public void test() {

        log.info("저장 : ThreadLocal.set(@)");
        log.info("조회 : ThreadLocal.get()");
        log.info("제거 : ThreadLocal.remove()");

        log.info("Tip! : [주의사항] 스레드로컬을 사용하고 제거를 안하면 꼭 값을 제거(remove) 해야한다. 안그럼 메모리 누수가 난다");
    }


    private final FieldService fieldService = new FieldService();

    @Test
    @DisplayName("동시성 이슈 없는 코드")
    void field() {

        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start(); //A실행
        sleep(2000); //동시성 문제 발생X
        // sleep(100); //동시성 문제 발생O
        threadB.start(); //B실행

        //메인 쓰레드 종료 대기
        sleep(3000);
        log.info("main exit");
        log.info("스래드가 끝나고 종료되게 만듬, 순서대로 실행됨");
    }

    @Test
    @DisplayName("동시성 이슈가 발생을 해결한 코드 - 그럼에도 불구하고 ThreadLocal은 동시성 문제가 발생하지 않는다")
    void concurrencyProblemOccur() {

        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start(); //A실행
        //sleep(2000); //동시성 문제 발생X
        sleep(100); //동시성 문제 발생O
        threadB.start(); //B실행

        //메인 쓰레드 종료 대기
        sleep(3000);
        log.info("main exit");
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
