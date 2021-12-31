package helloadvanced.advenced.trace.threadlocal.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {

    private String nameStore; // 필드변수에 저장

    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore);
        nameStore = name;//로직이 실행되면 필드변수에 저장한다
        sleep(1000);
        log.info("조회 nameStore={}", nameStore);
        return nameStore;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
