package com.example.SpringBootPlaygound.core_spring.order.discountpolicy;

import com.example.SpringBootPlaygound.core_spring.member.entity.Grade;
import com.example.SpringBootPlaygound.core_spring.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercentage = 10;

    @Override
    public int discount(Member member, int originalPrice) {
        if(member.getGrade() == Grade.VIP) {
            return originalPrice * discountPercentage / 100;
        }
        return 0;
    }
}
