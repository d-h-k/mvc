package com.example.SpringBootPlaygound.core_spring.order.discountpolicy;

import com.example.SpringBootPlaygound.core_spring.member.entity.Member;

public interface DiscountPolicy {
    int discount(Member member, int originalPrice);
}
