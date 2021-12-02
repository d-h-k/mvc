package com.example.SpringBootPlaygound.core_spring.order.discountpolicy;


import com.example.SpringBootPlaygound.core_spring.member.entity.Grade;
import com.example.SpringBootPlaygound.core_spring.member.entity.Member;
import org.springframework.stereotype.Component;

//@Component
public class FixDiscountPolicy implements DiscountPolicy {
    static private final int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int originalPrice) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return originalPrice;
    }
}
