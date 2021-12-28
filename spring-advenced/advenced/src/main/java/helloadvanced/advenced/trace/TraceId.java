package helloadvanced.advenced.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {
    /* =======================
    - 로그 추적기는 트랜잭션 ID && Depth/깊이 두가지를 동시에 표현해야한다
    - 이 두가지를 묶어서 관리하기 위한 객체 TraceId 를 만들었다
     */


    private String id;
    private int level;

    //public Constructor
    public TraceId() {
        this.id = createdId();
        this.level = 0;
    }

    //private Constructor
    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createdId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

}
