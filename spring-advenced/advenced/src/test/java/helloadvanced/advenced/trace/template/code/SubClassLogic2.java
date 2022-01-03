package helloadvanced.advenced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplate {
    @Override
    protected void call() {
        log.info("비지니스 로직2 동작");
        log.debug("킹영한께서 가라사대 : 변하는 부분이라 따로 분리하셨다고 하셨는데, 아직 이해가 안된다");
    }
}
