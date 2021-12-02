package com.example.SpringBootPlaygound.core_spring.singleton;

public class SingletonService {

    //static 영역에 객체 instance를 미리 하나 생성해서 올려둔다
    private static final SingletonService instance = new SingletonService();
    // 클래스로더가 클래스를 메모리에 로딩하는 순간에 바로 메모리에 짱박힌다!



    private SingletonService() {
        //생성자를 프라이빗으로 선언해서 외부에서 new키워드로 실체화를 하지 못하도록!
        // 결과물 : 오직 하나의 인스턴스만 존재하게 된다
    }

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
