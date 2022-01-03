package helloadvanced.advenced.trace.template;

import helloadvanced.advenced.trace.template.code.AbstractTemplate;
import helloadvanced.advenced.trace.template.code.SubClassLogic1;
import helloadvanced.advenced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TemplateMethodTest {

    @Test
    @DisplayName("V0 - templateMethod 적용하기 전 함수로 작성한 코드")
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


    // ==============================================================
    // ==============================================================


    @Test
    @DisplayName("V1 - templateMethod 메서드 패턴을 적용")
    public void templateMethodV1() {
        //given
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();

        //@todo W3 발표
        String lesson = "\n\n  - AbstractTemplate(Abs 클래스) 의 SubClassLogic1,2 에서 execute 를 호출하면 벌어지는일 설명 \n" +
                "  - 자식클래스의 execute 중간에서 call() 메서드를 호출하는데, 딱 요 부분만 오버라이딩 했다\n" +
                "  - 따라서 인스턴스 마다 S.C.Logic1 인지 2인지 : 인스턴스의 Logic1.call() 메서드가 호출될지, Logic2.call() 될지는 실체화된 인스턴스 종류에 따라 다르다\n" +
                "      - AbstractTemplate 에서 메서드의 거의 대부분을 구현해놓고(Template)\n" +
                "      - call() 메서드의 일부만 Override 해서 사용함으로 99% 재사용과 1% 변경점을 효과적으로 코딩할수 있는 디자인패턴을 템플릿 메서드 패턴이라고 함\n" +
                "  - 템플릿 메서드 패턴은 이렇게 사용할때의 효과는\n" +
                "      - 변하는 부분과 변하지 않는 부분을 분리해내서, 변하는 부분만 따로 재정의하는 방식이므로\n" +
                "      - LSP(리스코프 치환원칙)를 지켜서 작성했다면 상위타입(Abs Tem) 변수에 어떤 하위타입 (S.C.Logic1 or 2) 을 대입해도 정상 동작할테고\n" +
                "      - 결과적으로 기능을 확장하는데 기존 코드를 수정하지 않을수 있게 된다 (But 지금은 직접 new 키워드로 실체화 하니까 100% 완벽한 OCP 는 아님)\n\n";

        log.info(lesson);
    }

}
