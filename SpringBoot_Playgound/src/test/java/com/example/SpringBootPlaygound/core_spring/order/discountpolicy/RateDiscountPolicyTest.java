package com.example.SpringBootPlaygound.core_spring.order.discountpolicy;

import com.example.SpringBootPlaygound.core_spring.member.entity.Grade;
import com.example.SpringBootPlaygound.core_spring.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();


    static public Member member1;
    static public Member member2;

    static {
        member1 = Member.builder()
                .id(1L)
                .name("VIP동훈")
                .grade(Grade.VIP)
                .build();

        member2 = Member.builder()
                .id(2L)
                .name("제네시스")
                .grade(Grade.BASIC)
                .build();
    }


    @Test
    @DisplayName("vip는 10% 할인이 적용되어야 함")
    void vip10discount() {

        //given
        int discount = rateDiscountPolicy.discount(member1, 50000);


        //then
        assertThat(discount).isEqualTo(5000);
    }

    @Test
    @DisplayName("VIP 가 아니라면 할인이 적용되지 않아야함")
    void basicNoDiscount() {
        //given
        int discount = rateDiscountPolicy.discount(member2, 10000);


        //then
        assertThat(discount).isEqualTo(0);
    }
}
