package com.example.SpringBootPlaygound.core_spring.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, Integer itemPrice);
}
