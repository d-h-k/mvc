package helloadvanced.advenced.trace.threadlocal;

import helloadvanced.advenced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class FieldServiceTest {

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
    @DisplayName("동시성 이슈가 발생하는 코드")
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
        log.info("스래드가 끝나기 전에 쓰레드 하나 더 실행되서 동시성 문제가 발생함\n" +
                         "  - 스레드 A 입장에서는 황당한데 \n" +
                         "    - 여러 스래드가 같은 인스턴스의 필드에 접근하기 때문에\n" +
                         "    - 서비스에서는 트래픽이 적을때는 발생하지 않다가\n" +
                         "    - 장사가 잘되서 트래픽이 많아지면 점점 발생빈도가 증가하는 무시무시한 버그\n" +
                         "    - 특히 스프링에서 치명적인데, 스프링은 싱글톤등록이 기본이기 때문에, 싱글톤객체의 필드를 변경할때 동시성 문제가 발생한다\n" +
                         "    - 지역변수에서는 문제가 발생하지 않는데, 그 이유는 스레드마다 각각 다른 메모리 영역이 할당되기 떄문이고" +
                         "    - 동시성 문제가 발생하는 곳은 주로 (공용필드/static + 읽기쓰기가 빈번한곳) = 싱글톤 빈의 인스턴스 필드에서 자주 발생");
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Runnable 모르는 사람이 없게 해주세요")
    public void studyJava() {
        Runnable sample = new Runnable() {
            @Override
            public void run() {
                //자바 기본이니까 알제??
                fieldService.logic("sample");
                log.info("모르면 자바 기본서좀 봐라 두번봐라 세번도 좋다");
            }
        };
    }
}

