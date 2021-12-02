package com.example.SpringBootPlaygound.core_spring.singleton;

public class StatefulService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;// 값을 저장하므로 여기서 문제가 발생
    }

    public int getPrice() {
        return this.price;
    }

}
