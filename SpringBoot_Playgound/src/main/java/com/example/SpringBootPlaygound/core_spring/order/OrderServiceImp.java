package com.example.SpringBootPlaygound.core_spring.order;

import com.example.SpringBootPlaygound.core_spring.member.entity.Member;
import com.example.SpringBootPlaygound.core_spring.member.repo.MemberRepository;
import com.example.SpringBootPlaygound.core_spring.order.discountpolicy.DiscountPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImp implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //테스트 용도로 잠깐 추가, 77p
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    // 생성자주입이 핵심이군!
    @Autowired
    public OrderServiceImp(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, Integer itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discount);
    }
}
